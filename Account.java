package com.example.bijoya.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private AlertDialog.Builder alertDialogBuilder;

    public ImageView hscScience;
    public ImageView hscHumanities;
    public ImageView hscCommerce;

    private static final String TAG = "SignOut";

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
//
//    private ProgressBar mProgressBar;
//    private TextView tvSignout, tvSigningOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //tvSignout = (TextView) findViewById(R.id.tvConfirmSignout);
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
       // tvSigningOut = (TextView) findViewById(R.id.tvSigningOut);

       // mProgressBar.setVisibility(View.GONE);
        //tvSigningOut.setVisibility(View.GONE);

        setupFirebaseAuth();

        hscScience = (ImageView) findViewById(R.id.hscScience);
        hscHumanities = (ImageView) findViewById(R.id.hscHuamities);
        hscCommerce = (ImageView) findViewById(R.id.hscCommerce);


        hscScience.setOnClickListener(this);
        hscHumanities.setOnClickListener(this);
        hscCommerce.setOnClickListener(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
 /*
    .................this block is for right side menu(....)...................
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout_ok) {

            Log.d(TAG, "onClick: attempting to sign out.");
//            mProgressBar.setVisibility(View.VISIBLE);
//            tvSigningOut.setVisibility(View.VISIBLE);

//            mAuth.signOut();
//            finish();

                alertDialogBuilder = new AlertDialog.Builder(Account.this);
                alertDialogBuilder.setMessage("Are you sure want to logout?");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Log.d(TAG, "onClick: attempting to sign out.");
                       // mProgressBar.setVisibility(View.VISIBLE);
                       // tvSigningOut.setVisibility(View.VISIBLE);

                        mAuth.signOut();
                        finish();

                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                    Log.d(TAG, "onAuthStateChanged: navigating back to login screen.");
                    Intent intent = new Intent(Account.this,LogIn.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                // ...
            }
        };
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id = item.getItemId();
        switch (id) {

            case R.id.nav_home:
                Intent h = new Intent(Account.this, Home.class);
                startActivity(h);
                break;
            case R.id.nav_account:
                Intent g = new Intent(Account.this, Account.class);
                startActivity(g);
                break;
            case R.id.nav_university:
                Intent s = new Intent(Account.this, University.class);
                startActivity(s);
                break;
//            case R.id.nav_settings:
//                Intent t = new Intent(Account.this, Settings.class);
//                startActivity(t);
//                break;
//            case R.id.nav_rate:
//                Intent r = new Intent(Account.this, Rate.class);
//                startActivity(r);
//                break;
           // case R.id.nav_logout:
               // Intent l = new Intent(Account.this, Logout.class);
                //startActivity(l);
               // break;
            // this is done, now let us go and intialise the home page.
            // after this lets start copying the above.
            // FOLLOW MEEEEE>>>
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.hscScience) {
            Intent h_science = new Intent(Account.this, hsc_science.class);
            startActivity(h_science);
        }
        if (v.getId() == R.id.hscHuamities) {
            Intent h_humanities = new Intent(Account.this, hsc_humanities.class);
            startActivity(h_humanities);
        }
        if (v.getId() == R.id.hscCommerce) {
            Intent h_commerce = new Intent(Account.this, hsc_commerce.class);
            startActivity(h_commerce);
        }


    }
}


           /* hscGpa = (EditText) findViewById(R.id.hscGpa);
            hscMath = (EditText) findViewById(R.id.hscMath);
            hscBiology = (EditText) findViewById(R.id.hscBiology);
            hscScienceBangla = (EditText) findViewById(R.id.hscScienceBangla);
            hscPhysics = (EditText) findViewById(R.id.hscPhysics);
            hscChemistry = (EditText) findViewById(R.id.hscChemistry);
            hscScienceEnglish = (EditText) findViewById(R.id.hscScienceEnglish);
            //hscHuamitiesBangla= (EditText) findViewById(R.id.hscHuamitiesBangla);
            //hscHuamitiesEnglish= (EditText) findViewById(R.id.hscHuamitiesEnglish);
            //hscCommerceBangla= (EditText) findViewById(R.id.hscCommerceBangla);
            // hscCommerceEnglish= (EditText) findViewById(R.id.hscCommerceEnglish);
            String group2 = "Science";
            String num2 = hscGpa.getText().toString();
            double gpa2 = Double.parseDouble(num2);
            String BanglaNum = hscScienceBangla.getText().toString();
            double BanglaGpa = Double.parseDouble(BanglaNum);
            String EnglishNum = hscScienceEnglish.getText().toString();
            double EnglishGpa = Double.parseDouble(EnglishNum);
            String PhysicsNum = hscPhysics.getText().toString();
            double PhysicsGpa = Double.parseDouble(PhysicsNum);
            String ChemistryNum = hscChemistry.getText().toString();
            double ChemistryGpa = Double.parseDouble(ChemistryNum);
            String BiologyNum = hscBiology.getText().toString();
            double BiologyGpa = Double.parseDouble(BiologyNum);
            String MathNum = hscMath.getText().toString();
            double MathGpa = Double.parseDouble(MathNum);*/

       // }


            /*int i = 1;
            if (v.getId() == R.id.buttonCheck) {
                setContentView(R.layout.activity_eligibility);
                available_varsity = (TextView) findViewById(R.id.available_varsity);
                try {

                    if ((gpa1 + gpa2) >= 8.00 && group1 == "Science" && group2 == "Science") {
                        available_varsity.append(i++ + ". Dhaka University, KA unit\n\n");
                    }
                    if ((gpa1 + gpa2) >= 7.00) {
                        available_varsity.append(i++ + ". Dhaka University, KHA unit\n\n");
                    }
                    if ((gpa1 + gpa2) >= 7.50 && group2 == "Commerce") {
                        available_varsity.append(i++ + ". Dhaka University, GA unit\n\n");
                    }
                    if (((gpa1 + gpa2) >= 7.00 && group2 == "Humanities")
                            || ((gpa1 + gpa2) >= 7.50 && group2 == "Commerce")
                            || ((gpa1 + gpa2) >= 7.00 && group1 == "Science" && group2 == "Science")) {
                        available_varsity.append(i++ + ". Dhaka University, GHA unit\n\n");
                    }
                    if ((gpa1 + gpa2) >= 6.50 && gpa1 >= 3.00 && gpa2 >= 3.00) {
                        available_varsity.append(i++ + ". Dhaka University, CHA unit\n\n");
                    }
                    if (gpa1 >= 4.00 && MathGpa == 5 && PhysicsGpa == 5 && ChemistryGpa == 5 && BanglaGpa >= 4 && EnglishGpa >= 4) {
                        available_varsity.append(i++ + ". Buet\n\n");
                    }
                } catch (Exception e) {
                    Toast.makeText(Account.this, "Please enter valid email and password", Toast.LENGTH_SHORT).show();
                }
            }*/




