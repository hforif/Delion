package com.forif.park.delion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by RYAN on 11/11/2017.
 */

public class Convenient_Detail_JSONData implements Serializable{
    @SerializedName("id")
    @Expose
    private String mConvId;

    @SerializedName("name")
    @Expose
    private String mConvName;

    @SerializedName("branch")
    @Expose
    private String mConvDetail;

    @SerializedName("opentime")
    @Expose
    private String mConvOpenTime;

    @SerializedName("phone")
    @Expose
    private String mConvPNum;

    @SerializedName("life_add")
    @Expose
    private String mConvAdd;

    @SerializedName("state")
    @Expose
    private String mConvState;

    public void setmConvId(String mConvId) {
        this.mConvId = mConvId;
    }

    public void setmConvName(String mConvName) {
        this.mConvName = mConvName;
    }

    public void setmConvDetail(String mConvDetail) {
        this.mConvDetail = mConvDetail;
    }

    public void setmConvOpenTime(String mConvOpenTime) {
        this.mConvOpenTime = mConvOpenTime;
    }

    public void setmConvPNum(String mConvPNum) {
        this.mConvPNum = mConvPNum;
    }

    public void setmConvAdd(String mConvAdd) {
        this.mConvAdd = mConvAdd;
    }

    public void setmConvState(String mConvState) {
        this.mConvState = mConvState;
    }

    public String getmConvId() {

        return mConvId;
    }

    public String getmConvName() {
        return mConvName;
    }

    public String getmConvDetail() {
        return mConvDetail;
    }

    public String getmConvOpenTime() {
        return mConvOpenTime;
    }

    public String getmConvPNum() {
        return mConvPNum;
    }

    public String getmConvAdd() {
        return mConvAdd;
    }

    public String getmConvState() {
        return mConvState;
    }
}
