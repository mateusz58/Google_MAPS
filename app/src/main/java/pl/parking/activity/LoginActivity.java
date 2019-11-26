package pl.parking.activity;

/** A login screen that offers login via email/password. */
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import pl.parking.app.MainActivity;
import pl.parking.controller.api.ApiStaticData;
import pl.parking.controller.api.ApiController;
import pl.parking.controller.reponses.successfulResponses.ResponseLogin;
import pl.parking.model.User;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    private static final int REQUEST_READ_CONTACTS = 0;
    public static String token = "none";
    public final String TAG = "LoginActivity";

    @InjectView(R.id.email)
    EditText emailtext;

    @InjectView(R.id.password)
    EditText passwordtext;

    private User myUser = new User();
    private UserLoginTask mAuthTask = null;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                        if (id == R.id.login || id == EditorInfo.IME_NULL) {
                            attemptLogin();

                            return true;
                        }
                        return false;
                    }
                });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button mChangepasswordbutton = (Button) findViewById(R.id.btn_reset_password);

        mEmailSignInButton.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        attemptLogin();
                    }
                });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(
                            android.R.string.ok,
                            new View.OnClickListener() {
                                @Override
                                @TargetApi(Build.VERSION_CODES.M)
                                public void onClick(View v) {
                                    requestPermissions(
                                            new String[] {READ_CONTACTS}, REQUEST_READ_CONTACTS);
                                }
                            });
        } else {
            requestPermissions(new String[] {READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        } else {

            showProgress(true);
            mAuthTask = new UserLoginTask(email, password, this);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 8;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView
                    .animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 0 : 1)
                    .setListener(
                            new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                                }
                            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView
                    .animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(
                            new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                                }
                            });
        } else {

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(
                this,
                Uri.withAppendedPath(
                        ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,
                ContactsContract.Contacts.Data.MIMETYPE + " = ?",
                new String[] {ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE},
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {}

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    public void Switch_Paswword_Reset_activity(View view) {

        Intent myIntent = new Intent(this, PasswordChangeActivity.class);
        this.startActivity(myIntent);
    }

    public void Switch_SignupActivity(View view) {

        Intent myIntent = new Intent(this, SignupActivity.class);
        this.startActivity(myIntent);
    }

    public void Reset_password(View view) {
        Log.i(TAG, "Reset_password");
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
            ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final Context mContext;

        UserLoginTask(String email, String password, Context context) {
            mEmail = email;
            mPassword = password;
            mContext = context;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean result = false;
            Log.w(TAG, "doInBackground:Initiated ");

            Log.i(TAG, "doInBackground: login " + mEmail);
            Log.i(TAG, "doInBackground: password " + mPassword);
            String TXTEMAIL = mEmail.replaceAll("[\\s()]+", "");
            String TXTPASSWORD = mPassword.replaceAll("[\\s()]+", "");

            ApiController apiEndpoints =
                    ParkingService.getRetrofitInstance().create(ApiController.class);

            Call<ResponseLogin> call =
                    apiEndpoints.postUserLogin(TXTEMAIL, TXTEMAIL, TXTPASSWORD);
            try {

                Response<ResponseLogin> response = call.execute();
                ResponseLogin authResponse = response.body();

                if (response.isSuccessful()) {
                    SharedPreferences prefs = getSharedPreferences("Token.txt", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Email", authResponse.getEmail());
                    editor.putString("Token", "Token " + authResponse.getToken());
                    editor.putString("User_id", authResponse.getUserId().toString());
                    editor.commit();
                    ApiStaticData.is_Token = true;

                    result = true;
                    return result;
                }
                if (!response.isSuccessful()) {

                    result = false;
                    return result;
                }

            } catch (IOException e) {

                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                return result;
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {

                Log.i("RESPONSE", "Successful login");
                finish();
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(myIntent);
            } else {
                Log.i("RESPONSE", "Unsuccessful login");
                DialogInterface.OnClickListener dialogClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        {
                                            Intent myIntent =
                                                    new Intent(
                                                            LoginActivity.this,
                                                            SignupActivity.class);
                                            LoginActivity.this.startActivity(myIntent);
                                        }
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        Intent myIntent =
                                                new Intent(LoginActivity.this, LoginActivity.class);
                                        LoginActivity.this.startActivity(myIntent);

                                        break;
                                }
                            }
                        };
                AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
                builder.setMessage(R.string.confirm_registry)
                        .setPositiveButton(R.string.yes, dialogClickListener)
                        .setNegativeButton(R.string.no, dialogClickListener)
                        .show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
