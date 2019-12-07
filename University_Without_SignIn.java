package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class University_Without_SignIn extends AppCompatActivity
       implements NavigationView.OnNavigationItemSelectedListener,TextToSpeech.OnInitListener{


    Spinner DhakaSp;
    private Spinner SylhetSp;
    private Spinner ChittagongSp;
    private Spinner RajshahiSp;
    private Spinner KhulnaSp;
    private Spinner BarisalSp;
    private Spinner RangpurSp;
    private Spinner MymensinghSp;
    private TextView textvarsity;
    private TextToSpeech toSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_without_signin);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DhakaSp = (Spinner) findViewById(R.id.Dhaka1);
        SylhetSp = (Spinner) findViewById(R.id.Sylhet);
        ChittagongSp = (Spinner) findViewById(R.id.Chittagong);
        RajshahiSp = (Spinner) findViewById(R.id.Rajshahi);
        KhulnaSp = (Spinner) findViewById(R.id.Khulna);
        BarisalSp = (Spinner) findViewById(R.id.Barisal);
        RangpurSp = (Spinner) findViewById(R.id.Rangpur);
        MymensinghSp = (Spinner) findViewById(R.id.Mymensingh);
        textvarsity = (TextView) findViewById(R.id.textvarsity);

        toSpeech = new TextToSpeech(this,this);

        DhakaSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent du= new Intent(University_Without_SignIn.this,Dhaka_varsity.class);
                        startActivity(du);
                        toSpeech.speak("Welcome to Dhaka University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent buet= new Intent(University_Without_SignIn.this,Buet.class);
                        startActivity(buet);
                        toSpeech.speak("Welcome to Bangladesh University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 3:
                        Intent duet= new Intent(University_Without_SignIn.this,Duet.class);
                        startActivity(duet);
                        toSpeech.speak("Welcome to Dhaka University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 4:
                        Intent ju= new Intent(University_Without_SignIn.this,Jahangirnagar.class);
                        startActivity(ju);
                        toSpeech.speak("Welcome to Jahangirnagar University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 5:
                        Intent jnu= new Intent(University_Without_SignIn.this,Jagannath.class);
                        startActivity(jnu);
                        toSpeech.speak("Welcome to Jagannath University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 6:
                        Intent butex= new Intent(University_Without_SignIn.this,BUTex.class);
                        startActivity(butex);
                        toSpeech.speak("Welcome to Bangladesh University of Textile Engineering", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 7:
                        Intent sher_e_Bangla= new Intent(University_Without_SignIn.this,sher_e_bangla.class);
                        startActivity(sher_e_Bangla);
                        toSpeech.speak("Welcome to Sher-e-Bangla Agriculture University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 8:
                        Intent BUP= new Intent(University_Without_SignIn.this,BUP.class);
                        startActivity(BUP);
                        toSpeech.speak("Welcome to Bangladesh University of Professionals", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 9:
                        Intent mist= new Intent(University_Without_SignIn.this,MIST.class);
                        startActivity(mist);
                        toSpeech.speak("Welcome to Military Institute of Science and Technology ", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 10:
                        Intent mawlana= new Intent(University_Without_SignIn.this,Mawlana_varsity.class);
                        startActivity(mawlana);
                        toSpeech.speak("Welcome to Mawlana Bhashani Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 11:
                        Intent B_Science= new Intent(University_Without_SignIn.this,Bangabandhu_science.class);
                        startActivity(B_Science);
                        toSpeech.speak("Welcome to Bangabandhu Sheikh Mujibur Rahman Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 12:
                        Intent B_agri= new Intent(University_Without_SignIn.this,Bangabandhu_agri.class);
                        startActivity(B_agri);
                        toSpeech.speak("Welcome to Bangabandhu Sheikh Mujibur Rahman Agriculture University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 13:
                        Intent B_medical= new Intent(University_Without_SignIn.this,Bangabandhu_medical.class);
                        startActivity(B_medical);
                        toSpeech.speak("Welcome to Bangabandhu Sheikh Mujib Medical University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        SylhetSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent sust= new Intent(University_Without_SignIn.this,SUST.class);
                        startActivity(sust);
                        toSpeech.speak("Welcome to Shahjalal University of Science and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent sau= new Intent(University_Without_SignIn.this,Sylt_agri.class);
                        startActivity(sau);
                        toSpeech.speak("Welcome to Sylhet Agriculture University ", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ChittagongSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent cu= new Intent(University_Without_SignIn.this,Chittagong.class);
                        startActivity(cu);
                        toSpeech.speak("Welcome to Chittagong University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent cuet= new Intent(University_Without_SignIn.this,Cuet.class);
                        startActivity(cuet);
                        toSpeech.speak("Welcome to Chittagong University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 3:
                        Intent cu_veterinay= new Intent(University_Without_SignIn.this,Chittagong_veterinary.class);
                        startActivity(cu_veterinay);
                        toSpeech.speak("Welcome to Chittagong Veterinary and Animal Sciences University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 4:
                        Intent comilla= new Intent(University_Without_SignIn.this,Comilla.class);
                        startActivity(comilla);
                        toSpeech.speak("Welcome to Comilla University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 5:
                        Intent noakhali= new Intent(University_Without_SignIn.this,Noakhali.class);
                        startActivity(noakhali);
                        toSpeech.speak("Welcome to Noakhali Science and Technology University ", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 6:
                        Intent rangamati= new Intent(University_Without_SignIn.this,Rangamati.class);
                        startActivity(rangamati);
                        toSpeech.speak("Welcome to Rangamati Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        RajshahiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent rajshahi= new Intent(University_Without_SignIn.this,Rajshahi.class);
                        startActivity(rajshahi);
                        toSpeech.speak("Welcome to Rajshahi University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent ruet= new Intent(University_Without_SignIn.this,Ruet.class);
                        startActivity(ruet);
                        toSpeech.speak("Welcome to Rajshahi University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 3:
                        Intent pabna= new Intent(University_Without_SignIn.this,Pabna.class);
                        startActivity(pabna);
                        toSpeech.speak("Welcome to Pabna University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        KhulnaSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent khulna= new Intent(University_Without_SignIn.this,Khulna.class);
                        startActivity(khulna);
                        toSpeech.speak("Welcome to Khulna University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent kuet= new Intent(University_Without_SignIn.this,Kuet.class);
                        startActivity(kuet);
                        toSpeech.speak("Welcome to Khulna University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 3:
                        Intent jessore= new Intent(University_Without_SignIn.this,Jessore.class);
                        startActivity(jessore);
                        toSpeech.speak("Welcome to Jessore University of Science and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 4:
                        Intent islami = new Intent(University_Without_SignIn.this,Islami.class);
                        startActivity(islami);
                        toSpeech.speak("Welcome to Islami University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        BarisalSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent Barisal= new Intent(University_Without_SignIn.this,Barishal.class);
                        startActivity(Barisal);
                        toSpeech.speak("Welcome to Barisal University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent PatuaKhali= new Intent(University_Without_SignIn.this,Patuakhali.class);
                        startActivity(PatuaKhali);
                        toSpeech.speak("Welcome to Patuakhali University of Science and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        RangpurSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent Rukeya= new Intent(University_Without_SignIn.this,Rokeya.class);
                        startActivity(Rukeya);
                        toSpeech.speak("Welcome to Begum Rokeya University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent danesh= new Intent(University_Without_SignIn.this,Hajee_Danesh.class);
                        startActivity(danesh);
                        toSpeech.speak("Welcome to Hajee Mohammad Danesh Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        MymensinghSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent BangladeshAgri= new Intent(University_Without_SignIn.this,Bd_Agriculture.class);
                        startActivity(BangladeshAgri);
                        toSpeech.speak("Welcome to Bangladesh Agricultural University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                    case 2:
                        Intent kobiKazi= new Intent(University_Without_SignIn.this,Kobi_kazi.class);
                        startActivity(kobiKazi);
                        toSpeech.speak("Welcome to Jatiya Kobi Kazi Nazrul Islam University", TextToSpeech.QUEUE_FLUSH, null, null);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h1= new Intent(University_Without_SignIn.this,Home_Without_SignIn.class);
                startActivity(h1);
                break;
            case R.id.nav_account:
                Intent g1= new Intent(University_Without_SignIn.this,LogIn.class);
                startActivity(g1);
                break;
            case R.id.nav_university:
                Intent s1= new Intent(University_Without_SignIn.this,University_Without_SignIn.class);
                startActivity(s1);
                break;
//            case R.id.nav_settings:
//                Intent t = new Intent(University_Without_SignIn.this,Settings_Without_SignIn.class);
//                startActivity(t);
//                break;
//            case R.id.nav_rate:
//                Intent r = new Intent(University_Without_SignIn.this, LogIn.class);
//                startActivity(r);
//                break;
            case R.id.nav_login:
                Intent t1= new Intent(University_Without_SignIn.this,LogIn.class);
                startActivity(t1);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            toSpeech.setLanguage(Locale.UK);
        }
    }
}
