package com.android.Rest;

import com.android.Models.ManUpload;
import com.android.Models.Request_post;
import com.android.Models.Upload_Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by LENOVO on 03-May-17.
 */

public interface ApiInterface {

    @POST("requestb/requestb")
    Call<Request_post> getPostRequest(@Body Request_post request_post);

    @POST("isbn/isbn")
    Call<Upload_Post> UploadRequest(@Body Upload_Post upload_post);

    @POST("manupload/manupload")
    Call<ManUpload> ManualUpload(@Body ManUpload man_post);
}
