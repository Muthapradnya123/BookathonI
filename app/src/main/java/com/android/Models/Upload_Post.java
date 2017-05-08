package com.android.Models;

import com.google.gson.annotations.SerializedName;

public class Upload_Post {
    @SerializedName("author")
    private String author;
    @SerializedName("book_name")
    private String b_name;
    @SerializedName("copies")
    private int copies;
    @SerializedName("imagelink")
    private String imglink;
    @SerializedName("isbn")
    private String isbn;
    @SerializedName("category")
    private String category;
    private String username;

    public Upload_Post(String isbn,int copies,String username) {
        this.isbn = isbn;
        this.copies = copies;
        this.username = username;
    }

    public String getAuthor() {
        return author;
    }

    public String getB_name() {
        return b_name;
    }

    public String getImglink() {
        return imglink;
    }

    public String getCategory() {
        return category;
    }


}
