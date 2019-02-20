package com.compscitutorials.basigarcia.navigationdrawervideotutorial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.ErrorUtils;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    public static boolean success=true;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;
    @InjectView(R.id.input_confirm_password) TextView _confirmpasswordtext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Log.i("SignUP", "onClick: ");
                switch_to_loginactivity();
                finish();
            }
        });
    }
    public void signup() {

        Log.d(TAG, "Signup");
        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


//region proces of POST data sending
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = Parking_Service.BASE_URL+"api/registration_custom/";
        StringRequest MyStringRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i(TAG,"Response is: "+response);
                Toast.makeText(SignupActivity.this, "User registration successfull", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SignupActivity.this, R.string.error_invalid_email, Toast.LENGTH_SHORT).show();
               // Toast.makeText(SignupActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
                onSignupFailed();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username",email);
                params.put("email",email);
                params.put("password1",password);
                params.put("password2",password);
                return params;
            }
        };
        MyRequestQueue.add(MyStringRequest);



//endregion proces of POST data sending

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success

                            Log.i(TAG, "run: "+success);
                            onSignupSuccess();


                        progressDialog.dismiss();
                    }
                }, 3000);
    }


private void switch_to_mainactivity()
{
    Intent myIntent = new Intent(this,MainActivity.class);
    this.startActivity(myIntent);//to jest wazne


}
    private void switch_to_loginactivity()
    {
        Intent myIntent = new Intent(this,LoginActivity.class);
        this.startActivity(myIntent);//to jest wazne


    }
    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        switch_to_loginactivity();
    }

    public void onSignupFailed() {

        _passwordText.setText("");
        _confirmpasswordtext.setText("");
        _signupButton.setEnabled(true);


    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirmpassword = _confirmpasswordtext.getText().toString();


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError(getResources().getString(R.string.error_invalid_email));
            valid = false;
        }
        if (!password.equals(confirmpassword)) {

            Log.w("Password", "validate: "+password+" "+confirmpassword);
            _passwordText.setError(getResources().getString(R.string.match_password));
            _confirmpasswordtext.setError(getResources().getString(R.string.match_password));
            valid = false;
        }
        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError(getResources().getString(R.string.error_invalid_password));
            valid = false;
        }

        return valid;
    }
}