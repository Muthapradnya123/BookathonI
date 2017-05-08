package com.android.bookathon;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfile extends Fragment {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    Toolbar toolbar;

    Button btnn;
    private static final int PICK_IMAGE = 100;
    Uri imageuri;
    private Context context;
    Activity activity;


    public UserProfile() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        Button button = (Button) view.findViewById(R.id.profilebutton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openGallery();
            }

            private void openGallery() {
                Intent gallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);
            }


        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==PICK_IMAGE)
        {
            ImageView img =(ImageView) getView().findViewById(R.id.profileview);

            imageuri=data.getData();

            img.setImageURI(imageuri);

        }
    }






}