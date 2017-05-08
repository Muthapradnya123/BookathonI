package com.android.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 05-May-17.
 */

public class Request_post {

    @SerializedName("req_id")
    private Integer id;
    @SerializedName("author")
    private String author;
    @SerializedName("copies")
    private int copies;
    @SerializedName("edition")
    private String edition;
    @SerializedName("name")
    private String name;
    private String username;

    public Request_post(String username,String name,String author,int copies, String edition) {
        this.username=username;
        this.author = author;
        this.copies = copies;
        this.edition = edition;
        this.name = name;
    }
    public Integer getId() {
        return id;
    }


}
