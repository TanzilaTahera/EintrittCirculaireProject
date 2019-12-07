package com.example.bijoya.main;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class hsc_commerce extends AppCompatActivity implements View.OnClickListener {

    String[] group;
    public Spinner sscGroup;
    public EditText sscGpa;
    public EditText hscGpa;
    public EditText hscCommerceBangla;
    public EditText hscCommerceEnglish;
    public EditText hscAccouting;
    public EditText hscFinance;
    public EditText hscProduction;
    public EditText hscBusinessManagement;
    public Button buttonCheck;
    public TextView available_varsity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsc_commerce);

        getSupportActionBar().setTitle("Eligibility");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        group = getResources().getStringArray(R.array.group);

        sscGroup = (Spinner) findViewById(R.id.sscGroup);
        sscGpa = (EditText) findViewById(R.id.sscGpa);
        hscGpa = (EditText) findViewById(R.id.hscGpa);
        hscCommerceBangla = (EditText) findViewById(R.id.hscCommerceBangla);
        hscCommerceEnglish = (EditText) findViewById(R.id.hscCommerceEnglish);
        hscAccouting = (EditText) findViewById(R.id.hscAccounting);
        hscFinance = (EditText) findViewById(R.id.hscFinance);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);

        buttonCheck.setOnClickListener(this);

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

        // String group1 = sscGroup.getSelectedItem().toString();


        String group1 = "Science";

        try {

            String sscGrade = sscGpa.getText().toString();
            double ssc_gpa = Double.parseDouble(sscGrade);

            String hscGrade = hscGpa.getText().toString();
            double hsc_gpa = Double.parseDouble(hscGrade);

            String BanglaGrade = hscCommerceBangla.getText().toString();
            double BanglaGpa = Double.parseDouble(BanglaGrade);

            String EnglishGrade = hscCommerceEnglish.getText().toString();
            double EnglishGpa = Double.parseDouble(EnglishGrade);

            String AccountingGrade = hscAccouting.getText().toString();
            double AccountingGpa = Double.parseDouble(AccountingGrade);

            String FinanceGrade = hscFinance.getText().toString();
            double FinanceGpa = Double.parseDouble(FinanceGrade);


            int i = 1;
            if (view.getId() == R.id.buttonCheck) {

                setContentView(R.layout.activity_eligibility);
                available_varsity = (TextView) findViewById(R.id.available_varsity);


                if (ssc_gpa <= 5.00 && hsc_gpa <= 5.00 && BanglaGpa <= 5.00 && EnglishGpa <= 5.00 && AccountingGpa<=5.00 &&
                        FinanceGpa<=5.00){// && BusinessManagementGpa<=5.00 && ProductionGpa<=5.00) {

                    if ((ssc_gpa + hsc_gpa) >= 7.00) {
                        available_varsity.append(i++ + ". Dhaka University, KHA unit\n\n");
                    }
                    if ((ssc_gpa + hsc_gpa) >= 7.50) { //without forth
                        available_varsity.append(i++ + ". Dhaka University, GA unit\n\n");
                    }
                    if ((ssc_gpa + hsc_gpa) >= 7.50 && BanglaGpa >= 3.00 && EnglishGpa >= 3.00 && AccountingGpa >= 3.00
                            && FinanceGpa >= 3.00){// && ProductionGpa >= 3.00 && BusinessManagementGpa >= 3.00) { //economics
                        available_varsity.append(i++ + ". Dhaka University, GHA unit\n\n");
                    }
                    if ((ssc_gpa + hsc_gpa) >= 6.50 && ssc_gpa >= 3.00 && hsc_gpa >= 3.00) {
                        available_varsity.append(i++ + ". Dhaka University, CA unit\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 7.50 && BanglaGpa >= 4.00 && EnglishGpa >= 3.50) {
                        //&& Economics>=4.00 && Computer>=4.00 && Statistics>=4.00 && Economics and banajjik vugol>=4.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, B Unit -> Economics \n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 7.00 && EnglishGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, B Unit ->  Nogor o Anjol Porikolpona \n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 7.50 && BanglaGpa >= 3.50 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, B Unit -> Anthropology\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 7.00 && BanglaGpa >= 3.50 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, B Unit -> sorkar o rajniti\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 7.00 && EnglishGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, B Unit -> luk proshason\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 7.00 && EnglishGpa >= 3.00 && BanglaGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, B Unit -> vugol o poribesh\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 7.50 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> antorjatik somporko\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 8.00 && EnglishGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> English\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 7.50 && EnglishGpa >= 3.00 && BanglaGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> History\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.00 && EnglishGpa >= 3.00 && BanglaGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> Philosophy\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50 && BanglaGpa >= 3.00 && (EnglishGpa >= 3.00 ||
                            AccountingGpa >= 3.00 || FinanceGpa >= 3.00 )//|| ProductionGpa >= 3.00 || BusinessManagementGpa >= 3.00)
                            ) { // bangla soho onno akti subject e B
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> Natok o Nattototto\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 7.50 && EnglishGpa >= 3.00 && BanglaGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> Protnototto\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 8.00 && EnglishGpa >= 3.00 && BanglaGpa >= 4.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> Bangla\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 7.50 && EnglishGpa >= 3.00 && BanglaGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> Jarnalism & Media Studies\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 7.00 && EnglishGpa >= 3.00 && BanglaGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, C Unit -> Charukola\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 7.50 && AccountingGpa >= 3.00) {//economics B, basbay niti o proyug B
                        available_varsity.append(i++ + ". JahangirNagar University, E Unit -> Finance & Banking\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 8.50 && AccountingGpa >= 4.00 && EnglishGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, E Unit -> Marketing\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 8.50 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, E Unit -> Accounting & Information System\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 8.50 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". JahangirNagar University, E Unit -> Management Studies\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 7.50 && EnglishGpa >= 3.00 && BanglaGpa >= 3.50) {
                        available_varsity.append(i++ + ". JahangirNagar University, F Unit -> Ain O Bichar\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 8.00 && EnglishGpa >= 3.50 && AccountingGpa >= 4.00) {
                        //economics B, basbay niti o proyug B
                        available_varsity.append(i++ + ". JahangirNagar University, G Unit -> BBA\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 8.00) {
                        available_varsity.append(i++ + ". Jagannath University, A Unit\n\n");
                        available_varsity.append(i++ + ". Jagannath University, B Unit\n\n");
                        available_varsity.append(i++ + ". Jagannath University, C Unit\n\n");
                        available_varsity.append(i++ + ". Jagannath University, D Unit\n\n");
                    }
                    if (ssc_gpa >= 2.50 && hsc_gpa >= 2.50 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Jagannath University, E Unit\n\n");
                    }
                    if (ssc_gpa >= 4.50 && hsc_gpa >= 4.25) {
                        available_varsity.append(i++ + ". Bangladesh University of Professionals\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        //without 4th subject
                        available_varsity.append(i++ + ". Mawlana Bhashani Science and Technology University, D Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Bangabandhu Sheikh Mujibur Rahman Science and Technology University, D Unit\n\n");
                        available_varsity.append(i++ + ". Bangabandhu Sheikh Mujibur Rahman Science and Technology University, E Unit\n\n");
                        available_varsity.append(i++ + ". Bangabandhu Sheikh Mujibur Rahman Science and Technology University, F Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Jatiya Kobi Kazi Nazrul Islam University, A Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". Jatiya Kobi Kazi Nazrul Islam University, A Unit -> English \n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Jatiya Kobi Kazi Nazrul Islam University, C Unit\n\n");
                        available_varsity.append(i++ + ". Jatiya Kobi Kazi Nazrul Islam University, D Unit\n\n");
                    }
                    if (ssc_gpa >= 2.50 && hsc_gpa >= 2.50) {
                        available_varsity.append(i++ + ". Jatiya Kobi Kazi Nazrul Islam University, E Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Shahjalal University of Science and Technology, A Unit\n\n");
                    }
                    if (ssc_gpa >= 2.75 && hsc_gpa >= 2.50 && (ssc_gpa + hsc_gpa) >= 5.75) {
                        available_varsity.append(i++ + ". Chittagong University, B1 Unit\n\n");
                    }
                    if (ssc_gpa >= 2.50 && hsc_gpa >= 2.25 && (ssc_gpa + hsc_gpa) >= 5.25) {
                        available_varsity.append(i++ + ". Chittagong University, B2 Unit\n\n");
                    }
                    //b3,b4,b5,b6 condition needed
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 2.75 && (ssc_gpa + hsc_gpa) >= 6.25) {
                        available_varsity.append(i++ + ". Chittagong University, B7 Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 2.75 && (ssc_gpa + hsc_gpa) >= 6.75) {
                        available_varsity.append(i++ + ". Chittagong University, C Unit\n\n");
                    }
                    if (ssc_gpa >= 2.75 && hsc_gpa >= 2.50 && (ssc_gpa + hsc_gpa) >= 5.75) {
                        available_varsity.append(i++ + ". Chittagong University, D Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 2.75 && (ssc_gpa + hsc_gpa) >= 6.75) {
                        available_varsity.append(i++ + ". Chittagong University, E Unit\n\n");
                    }
                    if (ssc_gpa >= 2.50 && hsc_gpa >= 2.25 && (ssc_gpa + hsc_gpa) >= 5.25) {
                        available_varsity.append(i++ + ". Chittagong University, H Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Comilla University, B Unit \n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) { // All subject must be greater than C
                        available_varsity.append(i++ + ". Noakhali Science and Technology University, A Unit \n\n");
                        available_varsity.append(i++ + ". Noakhali Science and Technology University, B Unit \n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Rangamati Science and Technology University -> Management \n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 8.00) {
                        available_varsity.append(i++ + ". Rajshahi University\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 7.00) {
                        available_varsity.append(i++ + ". Pabna University of Engineering and Technology, C Unit \n\n");
                    }
                    if (ssc_gpa >= 4.00 && hsc_gpa >= 4.00 && (ssc_gpa + hsc_gpa) >= 8.00) {
                        available_varsity.append(i++ + ". Khulna University, B Unit \n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50) {
                        available_varsity.append(i++ + ". Khulna University, A Unit \n\n");
                        available_varsity.append(i++ + ". Khulna University, F Unit \n\n");
                    }
                    if (ssc_gpa >= 4.00 && hsc_gpa >= 4.00 && (ssc_gpa + hsc_gpa) >= 8.00 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". Khulna University, S Unit \n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 6.75) {
                        available_varsity.append(i++ + ". Islami University, A Unit\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 6.75) {
                        available_varsity.append(i++ + ". Islami University, B Unit\n\n");
                        available_varsity.append(i++ + ". Islami University, C Unit\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 6.75) {
                        available_varsity.append(i++ + ". Islami University, G Unit\n\n");
                    }
                    if (ssc_gpa >= 3.25 && hsc_gpa >= 3.25 && (ssc_gpa + hsc_gpa) >= 6.75) {
                        available_varsity.append(i++ + ". Islami University, H Unit\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 7.00 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". Jessore University of Science and Technology, D Unit\n\n");
                    }
                    if (ssc_gpa >= 2.50 && hsc_gpa >= 2.50 && (ssc_gpa + hsc_gpa) >= 6.00 && EnglishGpa >= 3.00) {
                        available_varsity.append(i++ + ". Jessore University of Science and Technology, E Unit\n\n");
                    }
                    // Jessore uni F Unit condition needed

                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50 && AccountingGpa >= 3.00) {
                        available_varsity.append(i++ + ". Barisal University, C Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Barisal University, D Unit\n\n");
                    }
                    if (ssc_gpa >= 1.00 && hsc_gpa >= 1.00 && BanglaGpa >= 2.00 && EnglishGpa >= 2.00) {//All subject must be greater than C or C
                        available_varsity.append(i++ + ". Patuakhali University of Science and Technology, B Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Begum Rokeya University, A Unit\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.50 && (ssc_gpa + hsc_gpa) >= 7.50) {
                        available_varsity.append(i++ + ". Begum Rokeya University, B Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Begum Rokeya University, C Unit\n\n");
                    }
                    if (ssc_gpa >= 3.50 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 7.00) {
                        available_varsity.append(i++ + ". Begum Rokeya University, F Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50 && AccountingGpa >= 1.00) {
                        available_varsity.append(i++ + ". Hajee Mohammad Danesh Science and Technology University, C Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Hajee Mohammad Danesh Science and Technology University, E Unit\n\n");
                    }
                    if (ssc_gpa >= 3.00 && hsc_gpa >= 3.00 && (ssc_gpa + hsc_gpa) >= 6.50) {
                        available_varsity.append(i++ + ". Hajee Mohammad Danesh Science and Technology University, G Unit\n\n");
                    }
                    if (ssc_gpa <= 1.00 || hsc_gpa <= 1.00) {
                        available_varsity.append("No University is available for you");
                    }


                }

                else{
                    available_varsity.append("You entered invalid gpa of : \n");

                    if(ssc_gpa>5.00){
                        available_varsity.append("SSC\n");
                    }
                    if(hsc_gpa>5.00){
                        available_varsity.append(" HSC\n");
                    }
                    if(BanglaGpa>5.00){
                        available_varsity.append(" Bangla\n");
                    }
                    if(EnglishGpa>5.00){
                        available_varsity.append(" English\n");
                    }
                    if(AccountingGpa>5.00){
                        available_varsity.append(" Accounting\n");
                    }
                    if(FinanceGpa>5.00){
                        available_varsity.append(" Finance/Economics\n");
                    }


                }

            }
        }
        catch (Exception e){
            Toast.makeText(hsc_commerce.this, "Please enter GPA of all courses", Toast.LENGTH_SHORT).show();
        }

    }
}

