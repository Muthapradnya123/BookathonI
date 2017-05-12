package com.android.bookathon;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.Models.ItemList;
import com.android.Models.ManUpload;
import com.android.Models.Upload_Post;
import com.android.Rest.ApiInterface;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upload extends Fragment {

    EditText isbn_edit;
    EditText copies;
    EditText mcopies;
    EditText mname;
    ImageButton camera;
    Button upload_scan,upload_man;

    private String isbnCode,toast;
    ArrayList<ItemList> up_post1;

    public Upload() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upload,container,false);

        isbn_edit = (EditText) v.findViewById(R.id.isbn_edit);
        copies = (EditText) v.findViewById(R.id.copy_edit);
        mname = (EditText) v.findViewById(R.id.mname_edittext);
        mcopies = (EditText) v.findViewById(R.id.mcopy_edit);

        final String username = AppHelper.getCurrUser();
        upload_scan = (Button) v.findViewById(R.id.upload);
        upload_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isbn_edit.getText().toString().length()==0 || copies.getText().toString().length()==0)
                {
                    toast = "Please scan the isbn code and also enter the copies";
                    displayToast();
                }
                else
                {
                    Upload_Post up_post = new Upload_Post(isbn_edit.getText().toString(),
                            Integer.parseInt(copies.getText().toString()),username);

                    sendNetworkRequest(up_post);

                }

            }
        });

        upload_man = (Button) v.findViewById(R.id.man_upload);
        upload_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mname.getText().toString().length()==0 || mcopies.getText().toString().length()==0)
                {
                    toast = "Please write book name and also enter the copies";
                    displayToast();
                }
                else
                {
                   ManUpload man_post = new ManUpload(mname.getText().toString(),
                            Integer.parseInt(mcopies.getText().toString()),username);

                    sendNetworkRequest1(man_post);

                }

            }
        });


        camera = (ImageButton) v.findViewById(R.id.isbn_scan);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanFromFragment();
            }
        });



        return v;
    }

    private void sendNetworkRequest1(ManUpload man_post) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://5if76yvnq0.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client and call object for the request
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ManUpload> call1 = apiInterface.ManualUpload(man_post);

        call1.enqueue(new Callback<ManUpload>() {
            @Override
            public void onResponse(Call<ManUpload> call, Response<ManUpload> response) {
                Toast.makeText(getActivity(), "Yes uploading was successful ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),Upload_Result.class);
                startActivity(i);

            }

            @Override
            public void onFailure(Call<ManUpload> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong! ", Toast.LENGTH_SHORT).show();
                Log.d("The ", "Exception is", t);
            }
        });
    }

    private void sendNetworkRequest(final Upload_Post up_post) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://5if76yvnq0.execute-api.us-west-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        //get client and call object for the request
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Upload_Post> call = apiInterface.UploadRequest(up_post);

        call.enqueue(new Callback<Upload_Post>() {
            @Override
            public void onResponse(Call<Upload_Post> call, Response<Upload_Post> response) {
                Toast.makeText(getActivity(), "Yes uploading was successful ", Toast.LENGTH_SHORT).show();
                up_post1 = response.body().getItems();
                Intent i = new Intent(getActivity(),Upload_Result.class);
                i.putExtra("Objects",up_post1);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<Upload_Post> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong! ", Toast.LENGTH_SHORT).show();
                Log.d("The ", "Exception is", t);
            }
        });
    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }

    private void displayToast() {
        if(getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Scan cancelled !";
            } else {
                isbnCode = result.getContents();
                toast = "Scan successful";
                isbn_edit.setText(isbnCode);

            }
            displayToast();

            // At this point we may or may not have a reference to the activity
        }
    }
}
