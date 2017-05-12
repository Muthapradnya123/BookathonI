package com.android.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Upload_Post {

    @SerializedName("Items")
    @Expose
    private ArrayList<ItemList> items = new ArrayList<>();

    @SerializedName("copies")
    private int copies;
    @SerializedName("isbn")
    private String isbn;
    private String username;

    public ArrayList<ItemList> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemList> items) {
        this.items = items;
    }

    public Upload_Post(String isbn,int copies,String username) {
        this.isbn = isbn;
        this.copies = copies;
        this.username = username;
    }

}
