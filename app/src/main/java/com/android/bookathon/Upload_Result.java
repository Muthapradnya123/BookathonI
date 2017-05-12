package com.android.bookathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.Models.ItemList;

import java.util.ArrayList;

public class Upload_Result extends AppCompatActivity {
    ArrayList<ItemList> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__result);

        list=(ArrayList<ItemList>) getIntent().getSerializableExtra("value");


    }
}
