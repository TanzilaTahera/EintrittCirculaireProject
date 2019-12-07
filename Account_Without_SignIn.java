

package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Account_Without_SignIn extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentTransaction f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h1= new Intent(Account_Without_SignIn.this,Home_Without_SignIn.class);
                startActivity(h1);
                break;
            case R.id.nav_account:
                Intent g1= new Intent(Account_Without_SignIn.this,LogIn.class);
                startActivity(g1);
                break;
            case R.id.nav_university:
                Intent s1= new Intent(Account_Without_SignIn.this,University_Without_SignIn.class);
                startActivity(s1);
                break;
//            case R.id.nav_settings:
//                Intent t = new Intent(Account_Without_SignIn.this,Settings_Without_SignIn.class);
//                startActivity(t);
//                break;
//            case R.id.nav_rate:
//                Intent r = new Intent(Account_Without_SignIn.this, LogIn.class);
//                startActivity(r);
//                break;
            case R.id.nav_login:
                Intent r1= new Intent(Account_Without_SignIn.this,LogIn.class);
                startActivity(r1);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
//package com.example.admin.entry;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AlertDialog;
//import android.view.View;
//import android.support.design.widget.NavigationView;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//public class Account_Without_SignIn extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
//
//
//
//    public TextView hscScience;
//    public TextView hscHumanities;
//    public TextView hscCommerce;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_account__without__sign_in);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//
//        hscScience = (TextView) findViewById(R.id.hscScience);
//        hscHumanities = (TextView) findViewById(R.id.hscHuamities);
//        hscCommerce = (TextView) findViewById(R.id.hscCommerce);
//
//
//        hscScience.setOnClickListener(this);
//        hscHumanities.setOnClickListener(this);
//        hscCommerce.setOnClickListener(this);
//
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        switch (id) {
//
//            case R.id.nav_home:
//                Intent h1 = new Intent(Account_Without_SignIn.this, Home_Without_SignIn.class);
//                startActivity(h1);
//                break;
//            case R.id.nav_account:
//                Intent g1 = new Intent(Account_Without_SignIn.this, Account_Without_SignIn.class);
//                startActivity(g1);
//                break;
//            case R.id.nav_university:
//                Intent s1 = new Intent(Account_Without_SignIn.this, University_Without_SignIn.class);
//                startActivity(s1);
//                break;
//            case R.id.nav_login:
//                Intent r1 = new Intent(Account_Without_SignIn.this, LogIn.class);
//                startActivity(r1);
//                break;
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        if (v.getId() == R.id.hscScience) {
//            Intent h_science = new Intent(Account_Without_SignIn.this, hsc_science.class);
//            startActivity(h_science);
//        }
//        if (v.getId() == R.id.hscHuamities) {
//            Intent h_humanities = new Intent(Account_Without_SignIn.this, hsc_humanities.class);
//            startActivity(h_humanities);
//        }
//        if (v.getId() == R.id.hscCommerce) {
//            Intent h_commerce = new Intent(Account_Without_SignIn.this, hsc_commerce.class);
//            startActivity(h_commerce);
//        }
//
//
//    }
//}
//
//
//
//
