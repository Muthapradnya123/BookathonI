package com.android.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 25-May-17.
 */

public class BookList {
    String uname;
    @SerializedName("Items")
    @Expose
    private ArrayList<BItems> items = new ArrayList<>();
    @SerializedName("Count")
    @Expose
    private Integer count;
    @SerializedName("ScannedCount")
    @Expose
    private Integer scannedCount;

    public BookList(String uname) {
        this.uname = uname;
    }


    public List<BItems> getItems() {
        return items;
    }

    public void setItems(ArrayList<BItems> items) {
        this.items = items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getScannedCount() {
        return scannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
    }
}