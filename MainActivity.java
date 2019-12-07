package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private QuestionLibrary mQuestionLibrary = new QuestionLibrary();

    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button quitButton;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        quitButton = (Button) findViewById(R.id.quit);


        updateQuestion();

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here


                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    Toast.makeText(MainActivity.this,getString(R.string.toast1), Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == 30){ openResult();}
                    updateQuestion();
                    //This line of code is optiona



                }else {
                    Toast.makeText(MainActivity.this, getString(R.string.toast2), Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == 30) { openResult(); }
                    updateQuestion();

                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);

                    //This line of code is optiona
                    Toast.makeText(MainActivity.this, getString(R.string.toast1), Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == 30) openResult();
                    updateQuestion();

                }else {
                    Toast.makeText(MainActivity.this, getString(R.string.toast2), Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == 30) openResult();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);

                    //This line of code is optiona
                    Toast.makeText(MainActivity.this, getString(R.string.toast1), Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == 30) openResult();
                    updateQuestion();

                }else {
                    Toast.makeText(MainActivity.this, getString(R.string.toast2), Toast.LENGTH_SHORT).show();
                    if(mQuestionNumber == 30) openResult();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button3
        quitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openResult();
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

    private void updateQuestion(){
        if( mQuestionNumber==30 ) openResult();
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

        mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }
    public void openResult(){
        Intent intent = new Intent(this,Result.class);
        startActivity(intent);
    }
}
