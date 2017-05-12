package com.android.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 08-May-17.
 */

public class ManUpload {

    @SerializedName("title")
    private String b_name;
    @SerializedName("copies")
    private int copies;
    private String username;

    public ManUpload(String b_name,int copies,String username) {
        this.b_name = b_name;
        this.copies = copies;
        this.username = username;
    }

}
