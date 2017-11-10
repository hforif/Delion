package com.example.ryan.retrofittest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by RYAN on 10/11/2017.
 */

public class Convenient_JSONData implements Serializable {

    @SerializedName("id")
    @Expose
    private String mConvId;

    @SerializedName("name")
    @Expose
    private String mConvName;

    @SerializedName("img")
    @Expose
    private String mConvImagePath;

    @SerializedName("branch")
    @Expose
    private String mConvDetail;

    @SerializedName("life_add_url")
    @Expose
    private String mConvAddUrl;

    @SerializedName("phone")
    @Expose
    private String mConvPnum;

    @SerializedName("state")
    @Expose
    private String mConvState;

    public String getmConvId() {
        return mConvId;
    }

    public String getmConvName() {
        return mConvName;
    }

    public String getmConvImagePath() {
        return mConvImagePath;
    }

    public String getmConvDetail() {
        return mConvDetail;
    }

    public String getmConvAddUrl() {
        return mConvAddUrl;
    }

    public String getmConvPnum() {
        return mConvPnum;
    }

    public String getmConvState() {
        return mConvState;
    }

    public void setmConvId(String mConvId) {
        this.mConvId = mConvId;
    }

    public void setmConvName(String mConvName) {
        this.mConvName = mConvName;
    }

    public void setmConvImagePath(String mConvImagePath) {
        this.mConvImagePath = mConvImagePath;
    }

    public void setmConvDetail(String mConvDetail) {
        this.mConvDetail = mConvDetail;
    }

    public void setmConvAddUrl(String mConvAddUrl) {
        this.mConvAddUrl = mConvAddUrl;
    }

    public void setmConvPnum(String mConvPnum) {
        this.mConvPnum = mConvPnum;
    }

    public void setmConvState(String mConvState) {
        this.mConvState = mConvState;
    }
}
