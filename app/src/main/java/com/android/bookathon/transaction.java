package com.android.bookathon;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.Models.ActivityTrans;
import com.android.Models.BItems;
import com.android.Models.BookList;
import com.android.Rest.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class transaction extends Fragment {

    private ListView listView;
    private View parentView;

    private ArrayList<BItems> itemsList;
    private MyBookAdapter adapter;
    private String uname;


    public transaction() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://5if76yvnq0.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client and call object for the request
        final ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        itemsList = new ArrayList<>();

        parentView = v.findViewById(R.id.parentLayout);

        /**
         * Getting List and Setting List Adapter
         */
        listView = (ListView) v.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Snackbar.make(parentView, itemsList.get(position).getBookName() + " => " + itemsList.get(position).getBookId(), Snackbar.LENGTH_LONG).show();
                final int book_id=itemsList.get(position).getBookId();
                Log.i("BookId"," "+itemsList.get(position).getBookId());
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Light_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                final AlertDialog show = builder.setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityTrans atd = new ActivityTrans(book_id);
                                Call<ActivityTrans> call = apiInterface.ActivityDelete(atd);
                                call.enqueue(new Callback<ActivityTrans>() {
                                    @Override
                                    public void onResponse(Call<ActivityTrans> call, Response<ActivityTrans> response) {
                                        Log.i("Delete ","Book successful"+response.message());
                                        Toast toast = Toast.makeText(getActivity(),"Book deleted successfully", Toast.LENGTH_LONG);
                                        toast.setGravity(Gravity.CENTER, 0, 0);
                                        toast.show();
                                    }

                                    @Override
                                    public void onFailure(Call<ActivityTrans> call, Throwable t) {

                                    }
                                });
                            }


                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


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
            uname = AppHelper.getCurrUser();

            ActivityTrans at = new ActivityTrans(uname);

            Call<BookList> call = apiInterface.Activity(at);

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

                        Log.i("Transaction",""+response.body());
                        itemsList = (ArrayList<BItems>) response.body().getItems();
                        /**
                         * Binding that List to Adapter
                         */
                        adapter = new MyBookAdapter(getActivity(), itemsList);
                        listView.setAdapter(adapter);
                        Log.i("Transaction",""+response.code());

                    } else {
                        Log.i("Transaction","failed");
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

        return v;
    }

}
