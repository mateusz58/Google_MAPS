package pl.parking.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.compscitutorials.basigarcia.parkingapp.R;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.parking.controller.api.ApiController;
import pl.parking.controller.reponses.errorResponses.ErrorResponsePasswordChange;
import pl.parking.controller.reponses.errorResponses.ErrorUtils;
import pl.parking.controller.reponses.successfulResponses.ResponsePasswordReset;
import pl.parking.service.ParkingService;
import retrofit2.Call;
import retrofit2.Response;

public class PasswordChangeActivity extends AppCompatActivity {
    private static final String TAG = "PasswordChangeActivity";

    public static boolean success = true;

    @InjectView(R.id.txt_input_email)
    EditText _emailText;

    @InjectView(R.id.btn_reset_password)
    Button _btn_reset_password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        ButterKnife.inject(this);

        _btn_reset_password.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetpassword();
                    }
                });
    }

    public void onPasswordchangeSuccess() {
        _btn_reset_password.setEnabled(true);
        setResult(RESULT_OK, null);
    }

    public void Switch_Login_activity(View view) {

        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
    }

    public void resetpassword() {

        if (!validate()) {
            onResetPasswordFailed();
            return;
        }

        _btn_reset_password.setEnabled(false);

        final ProgressDialog progressDialog =
                new ProgressDialog(PasswordChangeActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Password reset...");
        progressDialog.show();

        final String email = _emailText.getText().toString();

        ApiController apiEndpoints =
                ParkingService.getRetrofitInstance().create(ApiController.class);

        Call<ResponsePasswordReset> call = apiEndpoints.postPasswordReset(email);
        try {

            Response<ResponsePasswordReset> response = call.execute();
            ResponsePasswordReset authResponse = response.body();
            ErrorResponsePasswordChange error;

            if (response.isSuccessful()) {
                Toast.makeText(
                        PasswordChangeActivity.this, authResponse.getDetail(), Toast.LENGTH_SHORT);
            } else {
                error = ErrorUtils.parse_Error_Password_change(response);

                Toast.makeText(PasswordChangeActivity.this, error.toString(), Toast.LENGTH_SHORT);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        new android.os.Handler()
                .postDelayed(
                        new Runnable() {
                            public void run() {

                                onPasswordchangeSuccess();

                                progressDialog.dismiss();
                            }
                        },
                        3000);
    }

    private void switch_to_loginactivity(View v) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getResources().getString(R.string.error_invalid_email));
            valid = false;
        }

        return valid;
    }

    public void onResetPasswordFailed() {
        _emailText.setText("");
    }
}
