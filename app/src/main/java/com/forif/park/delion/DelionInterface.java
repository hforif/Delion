package com.forif.park.delion;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by RYAN on 09/11/2017.
 */

public interface DelionInterface {

    public static final String ServerUrl = "http://222.239.250.218/delion/index.php/";

    @GET("shop/shop_list/{id}")
    Call<List<Store_JSONData>> getShopList(@Path("id") int categoryId);

    @GET("lifeinfo/life_list/{id}")
    Call<List<Convenient_JSONData>> getConvList(@Path("id") int categoryId);

}
