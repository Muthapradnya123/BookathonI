package com.android.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 09-May-17.
 */

public class ItemList {
    @SerializedName("book_name")
    @Expose
    private String bname;

    @SerializedName("isbn")
    @Expose
    private String isbn;

    @SerializedName("imagelink")
    @Expose
    private String imglink;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("copies")
    @Expose
    private String copies;

    @SerializedName("author")
    @Expose
    private String author;



    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCopies() {
        return copies;
    }

    public void setCopies(String copies) {
        this.copies = copies;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
