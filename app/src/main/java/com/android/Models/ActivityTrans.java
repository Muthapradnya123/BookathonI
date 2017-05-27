package com.android.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 27-May-17.
 */

public class ActivityTrans {

    @SerializedName("username")
    private String uname;

    @SerializedName("book_id")
    private Integer b_id;

    public ActivityTrans(String uname) {
        this.uname = uname;
    }

    public ActivityTrans(Integer b_id)
    {
        this.b_id = b_id;
    }

}
