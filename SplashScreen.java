package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private int Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBarId);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                dowork();
                startApp();
            }
        });
        thread.start();

    }

    public void dowork() {

        for (Progress = 1; Progress <= 100; Progress++) {

            try {
                Thread.sleep(80);
                progressBar.setProgress(Progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void startApp() {
        Intent intent = new Intent(SplashScreen.this,Home_Without_SignIn.class);
        startActivity(intent);
        finish();
    }

}
