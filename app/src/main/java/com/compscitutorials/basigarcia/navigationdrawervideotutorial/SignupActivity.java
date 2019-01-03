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

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";


    public static boolean success=true;
    @InjectView(R.id.input_login) EditText _loginText;
    @InjectView(R.id.input_surname) EditText _surnameText;
    @InjectView(R.id.input_name) EditText _nameText;
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

        final String login=_loginText.getText().toString();
        final String surname = _surnameText.getText().toString();
        final  String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();


//region proces of POST data sending
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "https://polar-plains-14145.herokuapp.com/users/add";
        StringRequest MyStringRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i(TAG,"Response is: "+response);
                Toast.makeText(SignupActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SignupActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
               // Toast.makeText(SignupActivity.this, R.string.internet_error, Toast.LENGTH_SHORT).show();
                onSignupFailed();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("login",login);
                params.put("name",name);
                params.put("surname",surname);
                params.put("email",email);
                params.put("password",password);
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

        String login=_loginText.getText().toString();
        String surname = _surnameText.getText().toString();
        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String confirmpassword = _confirmpasswordtext.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 5 characters");
            valid = false;
        }
        if (login.isEmpty() || login.length() < 5) {
            _loginText.setError("at least 5 characters");
            valid = false;
        } else {
            _loginText.setError(null);
        }
        if (surname.isEmpty() || surname.length() < 3) {
            _surnameText.setError("at least 3 characters");
            valid = false;
        }
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