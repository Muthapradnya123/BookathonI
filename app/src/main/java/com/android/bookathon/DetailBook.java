package com.android.bookathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailBook extends AppCompatActivity {

    private TextView dbookname;
    private TextView author;
    private TextView Copies;
    private TextView Publisher;
    ImageView imglink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        dbookname = (TextView) findViewById(R.id.dbookname);
        author = (TextView) findViewById(R.id.dauthor);
        Copies = (TextView) findViewById(R.id.dcopies);
        imglink = (ImageView) findViewById(R.id.bookimage);
        Publisher =(TextView) findViewById(R.id.dpublish);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String bookname = extras.getString("Name");
        String pub = extras.getString("Publisher");
        String bookimg = extras.getString("Image");
        String Author = extras.getString("Author");
        Integer copies = extras.getInt("Copies",0);
        String username = extras.getString("Username");
        dbookname.setText(bookname);
        Publisher.setText(pub);
        Picasso.with(getApplicationContext()).load(bookimg).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imglink);
        author.setText(Author);
        Copies.setText(Integer.toString(copies));
    }
}
