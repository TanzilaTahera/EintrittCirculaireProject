package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    ImageView location;
    ImageView about;
    ImageView quiz;
    ImageView notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        location = (ImageView) findViewById(R.id.locationId);
        about = (ImageView) findViewById(R.id.AboutId);
        notice = (ImageView) findViewById(R.id.NoticeId);
        quiz = (ImageView) findViewById(R.id.QuizId);


        location.setOnClickListener(this);
        about.setOnClickListener(this);
        quiz.setOnClickListener(this);
        notice.setOnClickListener(this);


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
                Intent h= new Intent(Home.this,Home.class);
                startActivity(h);
                break;
            case R.id.nav_account:
                Intent g= new Intent(Home.this,Account.class);
                startActivity(g);
                break;
            case R.id.nav_university:
                Intent s= new Intent(Home.this,University.class);
                startActivity(s);
                break;
//            case R.id.nav_settings:
//                Intent t= new Intent(Home.this,Settings.class);
//                startActivity(t);
//                break;
//            case R.id.nav_rate:
//                Intent r= new Intent(Home.this,Rate.class);
//                startActivity(r);
//                break;
            //case R.id.nav_logout:
              //  Intent l= new Intent(Home.this,Logout.class);
               // startActivity(l);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.searchViewID) {
            Intent search = new Intent(Home.this, Search.class);
            startActivity(search);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()== R.id.locationId){

            Intent location1 = new Intent(Home.this, MapsActivity.class);
            startActivity(location1);

        }
        if(view.getId()== R.id.NoticeId){

            Intent about1 = new Intent(Home.this, PdfView.class);
            startActivity(about1);

        }
        if(view.getId()== R.id.QuizId){

            Intent about2 = new Intent(Home.this, Quiz.class);
            startActivity(about2);

        }
        if(view.getId()==R.id.AboutId){
            Intent about2 = new Intent(Home.this, About.class);
            startActivity(about2);
        }
    }
}
