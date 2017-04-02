package com.android.bookathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;

public class Register extends AppCompatActivity {
    Spinner category;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TabHost tab = (TabHost) findViewById(R.id.tabhost1);
        tab.setup();
       /* TabHost.TabSpec spec1 = tab.newTabSpec("Phone");
        spec1.setIndicator("Phone");
        spec1.setContent(R.id.phonereg);
        tab.addTab(spec1);*/

        TabHost.TabSpec spec2 = tab.newTabSpec("Email");
        spec2.setIndicator("Email");
        spec2.setContent(R.id.mailreg);
        tab.addTab(spec2);

        Button nextreg1 = (Button) findViewById(R.id.nextreg);
        nextreg1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Verify_otp.class);
                startActivity(i);
            }
        });

      /*  Button nextreg2 = (Button) findViewById(R.id.nextreg2);
        nextreg2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainPage.class);
                startActivity(i);
            }
        });
*/


        category = (Spinner)findViewById(R.id.category);
        adapter = ArrayAdapter.createFromResource(this,R.array.Categories,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
