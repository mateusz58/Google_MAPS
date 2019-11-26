package pl.parking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.compscitutorials.basigarcia.parkingapp.R;

import pl.parking.app.MainActivity;

/** Created by Admin on 2016-03-18. */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread =
                new Thread() {
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                };
        timerThread.start();
    }

    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }
}
