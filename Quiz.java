package com.example.bijoya.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity {
    private Button quiz1Button;
    private Button quiz2Button;
    private Button quiz3Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        quiz1Button = (Button) findViewById(R.id.startquizbutton);
        quiz2Button = (Button) findViewById(R.id.startquizbutton2);
        quiz3Button = (Button) findViewById(R.id.startquizbutton3);
        quiz1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openQuestion1();
            }

        });
        quiz2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openQuestion2();
            }

        });
        quiz3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openQuestion3();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void openQuestion1(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void openQuestion2(){
        Intent intent = new Intent(this,SetB.class);
        startActivity(intent);
    }
    public void openQuestion3(){
        Intent intent = new Intent(this,SetC.class);
        startActivity(intent);
    }
}

