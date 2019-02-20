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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.ErrorUtils;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.API_Errors.Error_Response_Password_change;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.API_end_points;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.controller.api.Parking_Service;
import com.compscitutorials.basigarcia.navigationdrawervideotutorial.model.reponse.Response_Password_reset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.Assert.assertTrue;

public class Password_change_Activity extends AppCompatActivity {
    private static final String TAG = "Password_change_Activity";


    public static boolean success=true;
    @InjectView(R.id.txt_input_email) EditText _emailText;
    @InjectView(R.id.btn_reset_password) Button _btn_reset_password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        ButterKnife.inject(this);

        _btn_reset_password.setOnClickListener(new View.OnClickListener() {
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


    public void Switch_Login_activity(View view) {//////////Aktywnosc do tworzenia nowego okienka do

        Intent myIntent = new Intent(this,LoginActivity.class);
        this.startActivity(myIntent);//to jest wazne
    }



    public void resetpassword() {




        if (!validate()) {
            onResetPasswordFailed();
            return;
        }

        _btn_reset_password.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Password_change_Activity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Password reset...");
        progressDialog.show();

        final String email = _emailText.getText().toString();

        API_end_points apiEndpoints = Parking_Service.getRetrofitInstance().create(API_end_points.class);
        // Pobranie listy
        Call<Response_Password_reset> call = apiEndpoints.post_password_reset(email);
        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Response_Password_reset> response = call.execute();
            Response_Password_reset authResponse = response.body();
            Error_Response_Password_change error;

            if(response.isSuccessful())
            {
                Toast.makeText(Password_change_Activity.this,authResponse.getDetail(),Toast.LENGTH_SHORT);
            }
            else
            {
                error = ErrorUtils.parse_Error_Password_change(response);

                Toast.makeText(Password_change_Activity.this, error.toString(),Toast.LENGTH_SHORT);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success


                        onPasswordchangeSuccess();


                        progressDialog.dismiss();
                    }
                }, 3000);
    }
    private void switch_to_loginactivity(View v)
    {
        Intent myIntent = new Intent(this,LoginActivity.class);
        this.startActivity(myIntent);//to jest wazne
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