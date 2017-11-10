package com.forif.park.delion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by RYAN on 11/11/2017.
 */

public interface Convenient_Interface {
    public static final String ServerUrl = "http://222.239.250.218/delion/index.php/";

    @GET("lifeinfo/life_list/{id}")
    Call<List<Convenient_JSONData>> getConvList(@Path("id") int categoryId);

    @GET("lifeinfo/life_detail/{id}")
    Call<List<Convenient_Detail_JSONData>> getConvDetail(@Path("id") int storeId);




}
