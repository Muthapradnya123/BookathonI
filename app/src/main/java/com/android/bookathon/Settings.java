package com.android.bookathon;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {
    Activity context;
    TextView accSettings;
    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       context=getActivity();
       return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    public void onStart()
    {
        super.onStart();
        accSettings = (TextView) context.findViewById(R.id.textView_account);
        accSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Account_Settings.class);
                startActivity(i);
            }
        });
    }

}
