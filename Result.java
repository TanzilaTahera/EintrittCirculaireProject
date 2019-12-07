package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Result extends AppCompatActivity {

    private Button tryAgainButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tryAgainButton = (Button) findViewById(R.id.tryAgain);
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openQuiz();
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
    private void openQuiz(){
        Intent intent = new Intent(this,Quiz.class);
        startActivity(intent);
    }
}
