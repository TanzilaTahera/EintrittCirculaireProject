package com.example.bijoya.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Pabna extends AppCompatActivity implements View.OnClickListener {
    private Button VarsityButton;
    private Button NoticeButton;
    private Button locationButton;
    private Button webButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pabna);

        getSupportActionBar().setTitle("Pabna University of Engineering and Technology");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        locationButton = (Button)findViewById(R.id.UniversityLocation);
        webButton = (Button)findViewById(R.id.Website);
        VarsityButton = (Button)findViewById(R.id.AboutVarsityId);


        locationButton.setOnClickListener(this);
        webButton.setOnClickListener(this);
        VarsityButton.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Website){
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pust.ac.bd"));
            startActivity(browse);
        }
        if(view.getId()==R.id.UniversityLocation){
            Intent ba = new Intent(Pabna.this,maps_Pabna.class);
            startActivity(ba);
        }
        if(view.getId()==R.id.AboutVarsityId){

            Intent ba = new Intent(Pabna.this,pdfreader_pabna.class);
            startActivity(ba);
        }
    }
}
