package com.example.ryan.retrofittest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by RYAN on 10/11/2017.
 */

public class Store_JSONData implements Serializable {

    @SerializedName("id")
    @Expose
    private String mStoreId;

    @SerializedName("name")
    @Expose
    private String mStoreName;

    @SerializedName("branch")
    @Expose
    private String mStoreDetail;

    @SerializedName("img")
    @Expose
    private String mStoreImagePath;

    @SerializedName("phone")
    @Expose
    private String mStorePnum;

    @SerializedName("state")
    @Expose
    private String mStoreState;


    public String getmStoreId() {
        return mStoreId;
    }

    public String getmStoreName() {
        return mStoreName;
    }

    public String getmStoreDetail() {
        return mStoreDetail;
    }

    public String getmStoreImagePath() {
        return mStoreImagePath;
    }

    public String getmStorePnum() {
        return mStorePnum;
    }

    public String getmStoreState() {
        return mStoreState;
    }
}
