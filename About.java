package com.example.bijoya.main;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class About extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {


    private TextView aboutText;
    private TextToSpeech toSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle("About Us");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        aboutText = (TextView) findViewById(R.id.aboutTextid);
        toSpeech = new TextToSpeech(this,this);

        aboutText.setOnClickListener(this);

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

        if(view.getId()==R.id.aboutTextid){

            toSpeech.speak("Eintritt Circulaire", TextToSpeech.QUEUE_FLUSH, null, null);
        }


    }

    @Override
    public void onInit(int i) {

        if (i == TextToSpeech.SUCCESS) {
            toSpeech.setLanguage(Locale.ENGLISH);
        }

    }
}
