package com.android.bookathon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.Models.Request_post;
import com.android.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class post_request extends Fragment {

    public post_request() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post_request, container, false);

        final EditText Name = (EditText) v.findViewById(R.id.pname_edittext);
        final EditText Author = (EditText) v.findViewById(R.id.pauthor_edit);
        final EditText Edition = (EditText) v.findViewById(R.id.pedition_edittext);
        final EditText Copies = (EditText) v.findViewById(R.id.pcopy_edit);


        Button post_request = (Button) v.findViewById(R.id.post_request_btn);
        post_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String b_name = Name.getText().toString();
                final String b_author = Author.getText().toString();
                final int b_copies = Integer.parseInt(Copies.getText().toString());
                final String username = AppHelper.getCurrUser();
                String b_edition = Edition.getText().toString();
                if(b_edition==null)
                {
                    b_edition.concat("    ");
                }

                Request_post request_post = new Request_post(username, b_name, b_author, b_copies, b_edition);

                sendNetworkRequest(request_post);
            }
        });
        return v;
    }

    private void sendNetworkRequest(Request_post request_post) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://5if76yvnq0.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client and call object for the request
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Request_post> call = apiInterface.getPostRequest(request_post);

        call.enqueue(new Callback<Request_post>() {
            @Override
            public void onResponse(Call<Request_post> call, Response<Request_post> response) {
                Toast.makeText(getActivity(), "Something went wrong  ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Request_post> call, Throwable t) {
                Toast.makeText(getActivity(), "Yes posting was successful", Toast.LENGTH_SHORT).show();
                Log.d("The ", "Exception is", t);
            }
        });
    }

}
