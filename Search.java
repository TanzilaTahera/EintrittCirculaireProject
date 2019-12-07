package com.example.bijoya.main;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Locale;

public class Search extends AppCompatActivity implements AdapterView.OnItemClickListener,TextToSpeech.OnInitListener {

    private String[] universityNames;
    private ListView listView;
    private SearchView searchView;
    private TextToSpeech toSpeech;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.ListViewID);
        searchView = (SearchView) findViewById(R.id.search2);
        universityNames = getResources().getStringArray(R.array.universityNames);

        toSpeech = new TextToSpeech(this, this);

        adapter = new ArrayAdapter<String>(Search.this, R.layout.sample_view, R.id.sampleID, universityNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        setupSearchView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });
    }


    private void setupSearchView()
    {
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search University");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        String value = adapter.getItem(i);

        if (value.equals("Dhaka University")) {
            Intent dhaka1= new Intent(Search.this,Dhaka_varsity.class);
            startActivity(dhaka1);
            toSpeech.speak("Welcome to Dhaka University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangladesh University of Engineering and Technology -> BUET")) {
            Intent buet1= new Intent(Search.this,Buet.class);
            startActivity(buet1);
            toSpeech.speak("Welcome to Bangladesh University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Dhaka University of Engineering and Technology -> DUET")) {
            Intent duet1= new Intent(Search.this,Duet.class);
            startActivity(duet1);
            toSpeech.speak("Welcome to Dhaka University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Jahangirnagar University")) {
            Intent ju1= new Intent(Search.this,Jahangirnagar.class);
            startActivity(ju1);
            toSpeech.speak("Welcome to Jahangirnagar University", TextToSpeech.QUEUE_FLUSH, null, null);

        } if (value.equals("Jagannath University")) {
            Intent jnu1= new Intent(Search.this,Jagannath.class);
            startActivity(jnu1);
            toSpeech.speak("Welcome to Jagannath University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangladesh University of Textile Engineering ->BUTex")) {
            Intent butex1= new Intent(Search.this,BUTex.class);
            startActivity(butex1);
            toSpeech.speak("Welcome to Bangladesh University of Textile Engineering", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Sher-e-Bangla Agriculture University")) {
            Intent sher1= new Intent(Search.this,sher_e_bangla.class);
            startActivity(sher1);
            toSpeech.speak("Welcome to Sher-e-Bangla Agriculture University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangladesh University of Professionals")) {
            Intent professionals1= new Intent(Search.this,BUP.class);
            startActivity(professionals1);
            toSpeech.speak("Welcome to Bangladesh University of Professionals", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Military Institute of Science and Technology -> MIST")) {
            Intent mist1= new Intent(Search.this,MIST.class);
            startActivity(mist1);
            toSpeech.speak("Welcome to Military Institute of Science and Technology ", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Mawlana Bhashani Science and Technology University")) {
            Intent mawlana1= new Intent(Search.this,Mawlana_varsity.class);
            startActivity(mawlana1);
            toSpeech.speak("Welcome to Mawlana Bhashani Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangabandhu Sheikh Mujibur Rahman Science and Technology University")) {
            Intent Bang_Science1= new Intent(Search.this,Bangabandhu_science.class);
            startActivity(Bang_Science1);
            toSpeech.speak("Welcome to Bangabandhu Sheikh Mujibur Rahman Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangabandhu Sheikh Mujibur Rahman Agriculture University")) {
            Intent Bang_Agri1= new Intent(Search.this,Bangabandhu_agri.class);
            startActivity(Bang_Agri1);
            toSpeech.speak("Welcome to Bangabandhu Sheikh Mujibur Rahman Agriculture University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangabandhu Sheikh Mujib Medical University")) {
            Intent Bang_Medical1= new Intent(Search.this,Bangabandhu_medical.class);
            startActivity(Bang_Medical1);
            toSpeech.speak("Welcome to Bangabandhu Sheikh Mujib Medical University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Shahjalal University of Science and Technology -> SUST")) {
            Intent sust1= new Intent(Search.this,SUST.class);
            startActivity(sust1);
            toSpeech.speak("Welcome to Shahjalal University of Science and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Sylhet Agriculture University")) {
            Intent sau1= new Intent(Search.this,Sylt_agri.class);
            startActivity(sau1);
            toSpeech.speak("Welcome to Sylhet Agriculture University ", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("University of Chittagong")) {
            Intent cu1= new Intent(Search.this,Chittagong.class);
            startActivity(cu1);
            toSpeech.speak("Welcome to University of Chittagong", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Chittagong University of Engineering and Technology -> CUET")) {
            Intent cuet1= new Intent(Search.this,Cuet.class);
            startActivity(cuet1);
            toSpeech.speak("Welcome to Chittagong University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }

        if (value.equals("Chittagong Veterinary and Animal Sciences University")) {
            Intent cu_veterinay1= new Intent(Search.this,Chittagong_veterinary.class);
            startActivity(cu_veterinay1);
            toSpeech.speak("Welcome to Chittagong Veterinary and Animal Sciences University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Comilla University")) {
            Intent comilla1= new Intent(Search.this,Comilla.class);
            startActivity(comilla1);
            toSpeech.speak("Welcome to Comilla University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Noakhali Science and Technology University")) {
            Intent noakhali1= new Intent(Search.this,Noakhali.class);
            startActivity(noakhali1);
            toSpeech.speak("Welcome to Noakhali Science and Technology University ", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Rangamati Science and Technology University")) {
            Intent rangamati1= new Intent(Search.this,Rangamati.class);
            startActivity(rangamati1);
            toSpeech.speak("Welcome to Rangamati Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);

        } if (value.equals("Rajshahi University")) {
            Intent rajshahi1= new Intent(Search.this,Rajshahi.class);
            startActivity(rajshahi1);
            toSpeech.speak("Welcome to Rajshahi University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Rajshahi University of Engineering and Technology -> RUET")) {
            Intent ruet1= new Intent(Search.this,Ruet.class);
            startActivity(ruet1);
            toSpeech.speak("Welcome to Rajshahi University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Pabna University of Engineering and Technology")) {
            Intent pabna1= new Intent(Search.this,Pabna.class);
            startActivity(pabna1);
            toSpeech.speak("Welcome to Pabna University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Khulna University")) {
            Intent khulna1= new Intent(Search.this,Khulna.class);
            startActivity(khulna1);
            toSpeech.speak("Welcome to Khulna University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Khulna University of Engineering and Technology -> KUET")) {
            Intent kuet1= new Intent(Search.this,Kuet.class);
            startActivity(kuet1);
            toSpeech.speak("Welcome to Khulna University of Engineering and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Jessore University of Science and Technology")) {
            Intent jessore1= new Intent(Search.this,Jessore.class);
            startActivity(jessore1);
            toSpeech.speak("Welcome to Jessore University of Science and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Islami University")) {
            Intent islami1 = new Intent(Search.this,Islami.class);
            startActivity(islami1);
            toSpeech.speak("Welcome to Islami University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Barisal University")) {
            Intent Barisal1= new Intent(Search.this,Barishal.class);
            startActivity(Barisal1);
            toSpeech.speak("Welcome to Barisal University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Patuakhali University of Science and Technology")) {
            Intent PatuaKhali1= new Intent(Search.this,Patuakhali.class);
            startActivity(PatuaKhali1);
            toSpeech.speak("Welcome to Patuakhali University of Science and Technology", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Begum Rokeya University")) {
            Intent Rukeya1= new Intent(Search.this,Rokeya.class);
            startActivity(Rukeya1);
            toSpeech.speak("Welcome to Begum Rokeya University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Hajee Mohammad Danesh Science and Technology University")) {
            Intent danesh1= new Intent(Search.this,Hajee_Danesh.class);
            startActivity(danesh1);
            toSpeech.speak("Welcome to Hajee Mohammad Danesh Science and Technology University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Bangladesh Agricultural University")) {
            Intent BangladeshAgri1= new Intent(Search.this,Bd_Agriculture.class);
            startActivity(BangladeshAgri1);
            toSpeech.speak("Welcome to Bangladesh Agricultural UniversityBangladesh Agricultural University", TextToSpeech.QUEUE_FLUSH, null, null);
        }
        if (value.equals("Jatiya Kobi Kazi Nazrul Islam University")) {
            Intent kobiKazi1= new Intent(Search.this,Kobi_kazi.class);
            startActivity(kobiKazi1);
            toSpeech.speak("Welcome to Jatiya Kobi Kazi Nazrul Islam University", TextToSpeech.QUEUE_FLUSH, null, null);
        }

    }



        @Override
        public boolean onOptionsItemSelected (MenuItem item){

            int id = item.getItemId();
            if (id == android.R.id.home) {
                this.finish();
            }
            return super.onOptionsItemSelected(item);
        }
        @Override
        public void onInit ( int i){
            if (i == TextToSpeech.SUCCESS) {
                toSpeech.setLanguage(Locale.ENGLISH);
            }
        }



}
