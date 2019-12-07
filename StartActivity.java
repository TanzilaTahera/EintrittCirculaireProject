package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button SignInAccount;
    private Button WithoutSignInAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SignInAccount = (Button) findViewById(R.id.buttonSigninAccount);
        WithoutSignInAccount = (Button) findViewById(R.id.buttonWithoutSigninAccount);

        SignInAccount.setOnClickListener(this);
        WithoutSignInAccount.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonSigninAccount) {
            Intent sign = new Intent(StartActivity.this, SignUp.class);
            startActivity(sign);
        }
        if (v.getId() == R.id.buttonWithoutSigninAccount) {
            Intent sign = new Intent(StartActivity.this,Home_Without_SignIn.class);
            startActivity(sign);
        }
    }
}
