package com.forif.park.delion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class Retrofitmain extends AppCompatActivity {
    private DelionInterface interfaces;
    private Retrofit retrofit;
    public static List<Store_JSONData> storeJSONList;
    public static List<Convenient_JSONData> convJSONList;
    String[] tempId,tempName,tempDetail,tempImagePath,tempPnum,tempState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofitmain);


        retrofit = new Retrofit.Builder()
                .baseUrl(DelionInterface.ServerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interfaces = retrofit.create(DelionInterface.class);


        Call<List<Store_JSONData>> callStoreList = interfaces.getShopList(1);

        callStoreList.enqueue(new Callback<List<Store_JSONData>>() {
            @Override
            public void onResponse(Call<List<Store_JSONData>> call, Response<List<Store_JSONData>> response) {
                storeJSONList = response.body();

                tempId=new String[storeJSONList.size()];
                tempName=new String[storeJSONList.size()];
                tempDetail=new String[storeJSONList.size()];
                tempImagePath=new String[storeJSONList.size()];
                tempPnum=new String[storeJSONList.size()];
                tempState=new String[storeJSONList.size()];

                for (int index=0;index<storeJSONList.size();index++){
                    tempId[index]=storeJSONList.get(index).getmStoreId();
                    tempName[index]=storeJSONList.get(index).getmStoreName();
                    tempDetail[index]=storeJSONList.get(index).getmStoreDetail();
                    tempImagePath[index]=storeJSONList.get(index).getmStoreImagePath();
                    tempPnum[index]=storeJSONList.get(index).getmStorePnum();
                    tempState[index]=storeJSONList.get(index).getmStoreState();
                }

                for (int index=0;index<storeJSONList.size();index++){
                    Store_ListData Data_1=new Store_ListData(getResources().getDrawable(R.drawable.))
                }


            }

            @Override
            public void onFailure(Call<List<Store_JSONData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });

        Call<List<Convenient_JSONData>> callConvList = interfaces.getConvList(1);
        callConvList.enqueue(new Callback<List<Convenient_JSONData>>() {
            @Override
            public void onResponse(Call<List<Convenient_JSONData>> call, Response<List<Convenient_JSONData>> response) {
                convJSONList = response.body();

            }

            @Override
            public void onFailure(Call<List<Convenient_JSONData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
        String ne12=null;


    }
}
