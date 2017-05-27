package com.android.Models;

import com.google.gson.annotations.SerializedName;

public class Upload_Post {

    @SerializedName("copies")
    private int copies;
    @SerializedName("isbn")
    private String isbn;
    private String username;

    public Upload_Post(String isbn,int copies,String username) {
        this.isbn = isbn;
        this.copies = copies;
        this.username = username;
    }

}
