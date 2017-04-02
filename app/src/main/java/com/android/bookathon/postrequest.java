package com.android.bookathon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by LENOVO on 17-02-2017.
 */
public class postrequest extends AppCompatActivity {
    Toolbar toolbar;
    Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postrequest);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("   Post Request");
    }
}
