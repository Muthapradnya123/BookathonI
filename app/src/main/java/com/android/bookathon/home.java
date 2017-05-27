package com.android.bookathon;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.Models.BItems;
import com.android.Models.BookList;
import com.android.Rest.ApiInterface;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class home extends Fragment {

    private ListView listView;
    private View parentView;

    private ArrayList<BItems> itemsList;
    private MyBookAdapter adapter;


    public home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_home, container, false);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://5if76yvnq0.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client and call object for the request
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
//=====================================================================================================
        itemsList = new ArrayList<>();

        parentView = v.findViewById(R.id.parentLayout);

        /**
         * Getting List and Setting List Adapter
         */
        listView = (ListView) v.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(),DetailBook.class);
                Bundle extras = new Bundle();
                extras.putString("Name",itemsList.get(position).getBookName());
                extras.putString("Author",itemsList.get(position).getAuthor());
                extras.putInt("Copies",itemsList.get(position).getCopies());
                extras.putString("Image",itemsList.get(position).getImagelink());
                extras.putString("Publisher",itemsList.get(position).getPublisher());
                extras.putString("Username",itemsList.get(position).getUsername());
                i.putExtras(extras);
                startActivity(i);
                //Snackbar.make(parentView, itemsList.get(position).getBookName() + " => " + itemsList.get(position).getIsbn(), Snackbar.LENGTH_LONG).show();
            }
        });

        /**
         * Just to know onClick and Printing Hello Toast in Center.
         */
        Toast toast = Toast.makeText(getActivity(),"Wait to load", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        /**
         * Checking Internet Connection
         */
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;


        if (connected == true) {
            final ProgressDialog dialog;
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(getActivity());

            dialog.setTitle("Please wait..");
            dialog.setMessage("Loading books..");
            dialog.show();

            /**
             * Calling JSON
             */

            Call<BookList> call = apiInterface.getMyJSON();

            /**
             * Enqueue Callback will be call when get response...
             */
            call.enqueue(new Callback<BookList>() {
                @Override
                public void onResponse(Call<BookList> call, Response<BookList> response) {
                    //Dismiss Dialog
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        /**
                         * Got Successfully
                         */
                        itemsList = (ArrayList<BItems>) response.body().getItems();
                        /**
                         * Binding that List to Adapter
                         */
                        adapter = new MyBookAdapter(getActivity(), itemsList);
                        listView.setAdapter(adapter);

                    } else {
                        Snackbar.make(parentView,"Something went wrong", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<BookList> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        } else {
            Snackbar.make(parentView,"Internet connection not available", Snackbar.LENGTH_LONG).show();
        }




//=====================================================================================================
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(getActivity(), ConversationActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
