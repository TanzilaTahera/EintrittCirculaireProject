package com.example.bijoya.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private static final String TAG ="SignUp";

    private Context mContext;
    private String email, username, password;
    private EditText mEmail, mPassword, mUsername;
    //private TextView loadingPleaseWait;
    private Button btnSignRegister;
    //private ProgressBar mProgressBar;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseMethods firebaseMethods;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String append = "";


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = SignUp.this;
        firebaseMethods = new FirebaseMethods(mContext);
        Log.d(TAG, "onCreate: started.");

        initWidgets();
        setupFirebaseAuth();
        init();


    }
    private void initWidgets(){

        Log.d(TAG, "initWidgets: Initializing Widgets.");
        mEmail = (EditText) findViewById(R.id.editTextSignEmail);
        mUsername = (EditText) findViewById(R.id.editTextSignName);
        btnSignRegister = (Button) findViewById(R.id.buttonSignRegister);
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        //loadingPleaseWait = (TextView) findViewById(R.id.pleaseWait);
        mPassword = (EditText) findViewById(R.id.editTextSignPassword);
        mContext = SignUp.this;
        // mProgressBar.setVisibility(View.GONE);
        //loadingPleaseWait.setVisibility(View.GONE);

    }

    private void init(){


        btnSignRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if (checkInputs(email, username, password)) {
                    //mProgressBar.setVisibility(View.VISIBLE);
                    //loadingPleaseWait.setVisibility(View.VISIBLE);

                    firebaseMethods.registerNewEmail(email, password, username);
                }
            }
        });


    }

    private boolean checkInputs(String email, String username, String password){

        Log.d(TAG, "checkInputs: checking inputs for null values.");
        if (email.equals("") || username.equals("") || password.equals("")) {
            Toast.makeText(mContext, "All fields must be filled out.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    /**
     * Initialize the activity widgets
     */


    private boolean isStringNull(String string){

        Log.d(TAG, "isStringNull: checking string if null.");

        if (string.equals("")) {
            return true;
        } else {
            return false;
        }



    }

     /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        try {
            Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

            mAuth = FirebaseAuth.getInstance();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();

            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    if (user != null) {
                        // User is signed in
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                //1st check: Make sure the username is not already in use
                                if (firebaseMethods.checkIfUsernameExists(username, dataSnapshot)) {
                                    append = myRef.push().getKey().substring(3, 10);
                                    Log.d(TAG, "onDataChange: username already exists. Appending random string to name: " + append);
                                }
                                username = username + append;

                                //add new user to the database
                                firebaseMethods.addNewUser(email, username, "", "", "");

                                Toast.makeText(mContext, "Signup successful. Sending verification email.", Toast.LENGTH_SHORT).show();

                                mAuth.signOut();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        finish();

                    } else {
                        // User is signed out
                        Log.d(TAG, "onAuthStateChanged:signed_out");
                    }
                    // ...
                }
            };
        }
        catch (Exception e){
            Toast.makeText(SignUp.this, "error", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class SignUp extends AppCompatActivity {
//    private EditText usernameET,emailET, passwordET;
//    private Button registerButton;
//    private TextView login,signup;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        usernameET = (EditText) findViewById(R.id.editTextSignName);
//        emailET = (EditText) findViewById(R.id.editTextEmail);
//        passwordET = (EditText) findViewById(R.id.editTextPassword);
//        registerButton = (Button) findViewById(R.id.buttonSignRegister);
//        login= (TextView) findViewById(R.id.textViewLogin);
//        //signup = (TextView) findViewById(R.id);
//
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    String username= usernameET.getText().toString();
//                    String email= emailET.getText().toString();
//                    String password= passwordET.getText().toString();
//                    int passlen = password.length();
//                    int emaillen = email.length();
//
//                    if(v.getId()==R.id.buttonSignRegister) {
//                        Intent signup = new Intent(SignUp.this, Home.class);
//                        startActivity(signup);
//
//                    } else {
//                        Toast.makeText(SignUp.this,"Please enter valid email and password",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                catch (Exception e){
//                    System.out.println("Exeception");
//                }
//
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                    if(v.getId()==R.id.textViewLogin) {
//                        Intent signup = new Intent(SignUp.this, LogIn.class);
//                        startActivity(signup);
//
//                    }
//
//            }
//        });
//
//
//
//
//
//
//    }
//}