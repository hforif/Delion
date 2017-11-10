package com.forif.park.delion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Store_Main extends ActionBarActivity {
    private ListView sListView = null;
    private Store_ListViewAdapter sAdapter = null;
    ServerUrl Value = new ServerUrl();
    private String typeOfStore;
    String[] t, t1, t2, t3, t4, t5;
    int index;

    private Store_Interface interfaces;
    private Retrofit retrofit;
    public static List<Store_JSONData> storeJSONList;
    String[] tempId, tempName, tempDetail, tempImagePath, tempPnum, tempState;
//    @Override
//    protected void onResume(){
//        super.onResume();
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Main menu에서 특정메뉴를 골랐을때 실행이 된다 .
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sAdapter = new Store_ListViewAdapter(getApplicationContext());
        sListView = (ListView) findViewById(R.id.Store_List_View);
        sListView.setAdapter(sAdapter);
        sListView.setOnItemClickListener(new ListClickHandler());

        typeOfStore = String.valueOf(getIntent().getExtras().getInt("position"));
        switch (typeOfStore) {
            case "1":
                getSupportActionBar().setTitle("치킨");
                break;
            case "2":
                getSupportActionBar().setTitle("중국집");
                break;
            case "3":
                getSupportActionBar().setTitle("피자");
                break;
            case "4":
                getSupportActionBar().setTitle("한식");
                break;
            case "5":
                getSupportActionBar().setTitle("분식");
                break;
            case "6":
                getSupportActionBar().setTitle("패스트푸드");
                break;
            case "7":
                getSupportActionBar().setTitle("족발");
                break;
            default:
                getSupportActionBar().setTitle("no-data");
                break;
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(Store_Interface.ServerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interfaces = retrofit.create(Store_Interface.class);


        Call<List<Store_JSONData>> callStoreList = interfaces.getShopList(Integer.parseInt(typeOfStore));

        callStoreList.enqueue(new Callback<List<Store_JSONData>>() {
            @Override
            public void onResponse(Call<List<Store_JSONData>> call, Response<List<Store_JSONData>> response) {
                storeJSONList = response.body();
                tempId = new String[storeJSONList.size()];
                tempName = new String[storeJSONList.size()];
                tempDetail = new String[storeJSONList.size()];
                tempImagePath = new String[storeJSONList.size()];
                tempPnum = new String[storeJSONList.size()];
                tempState = new String[storeJSONList.size()];

                for (index = 0; index < storeJSONList.size(); index++) {
                    tempId[index] = storeJSONList.get(index).getmStoreId();
                    tempName[index] = storeJSONList.get(index).getmStoreName();
                    tempDetail[index] = storeJSONList.get(index).getmStoreDetail();
                    tempImagePath[index] = storeJSONList.get(index).getmStoreImagePath();
                    tempPnum[index] = storeJSONList.get(index).getmStorePnum();
                    tempState[index] = storeJSONList.get(index).getmStoreState();
                }

                for (index = 0; index < storeJSONList.size(); index++) {
                    if (!(tempImagePath[index].equals("null"))) { //가게 이미지가 있을때
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Store_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(tempImagePath[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Store_ListData data = new Store_ListData(Store_Img, tempName[count], tempDetail[count], getResources().getDrawable(R.drawable.call_button), tempId[count], tempPnum[count]);
                                                sAdapter.addItem(data);
                                                sAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();

                    } else { //가게 이미지가 없을때
                        switch (typeOfStore) {
                            case "1":
                                Store_ListData data_1 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.chicken_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_1);
                                sAdapter.notifyDataSetChanged();
                                break;

                            case "2":
                                Store_ListData data_2 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.joongsik_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_2);
                                sAdapter.notifyDataSetChanged();
                                break;

                            case "3":
                                Store_ListData data_3 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.pizza_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_3);
                                sAdapter.notifyDataSetChanged();
                                break;

                            case "4":
                                Store_ListData data_4 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.hansik_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_4);
                                sAdapter.notifyDataSetChanged();
                                break;

                            case "5":
                                Store_ListData data_5 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.bunsik_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_5);
                                sAdapter.notifyDataSetChanged();
                                break;

                            case "6":
                                Store_ListData data_6 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.hamburger_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_6);
                                sAdapter.notifyDataSetChanged();
                                break;

                            case "7":
                                Store_ListData data_7 = new Store_ListData(
                                        getResources().getDrawable(R.drawable.jokbal_thumbnails),
                                        tempName[index], tempDetail[index],
                                        getResources().getDrawable(R.drawable.call_button),
                                        tempId[index], tempPnum[index]);
                                sAdapter.addItem(data_7);
                                sAdapter.notifyDataSetChanged();
                                break;

                            default:
                                break;
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<List<Store_JSONData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
//        String base_url = Value.url;
//        String s = "shop/shop_list/";
//        int a = getIntent().getExtras().getInt("position");


//        final String url = base_url + s + typeOfStore; // url : http://222.239.250.218/delion/index.php/shop/shop_list/1 or 2 or 3 or ..
//
//        AsyncHttpClient client = new AsyncHttpClient();
//
//        client.get(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//
//                String content = null;
//                try {
//                    content = new String(responseBody, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    JSONArray jsonArray = new JSONArray(content);
//                    t = new String[jsonArray.length()];
//                    t1 = new String[jsonArray.length()];
//                    t2 = new String[jsonArray.length()];
//                    t3 = new String[jsonArray.length()];
//                    t4 = new String[jsonArray.length()];
//                    t5 = new String[jsonArray.length()];
//
//                    for (index = 0; index < jsonArray.length(); index++) {
//
//                        JSONObject json = jsonArray.getJSONObject(index);
//
//                        t[index] = json.getString("id");
//                        t1[index] = json.getString("name");
//                        t2[index] = json.getString("branch");
//                        t3[index] = json.getString("img");
//                        t4[index] = json.getString("phone");
//                        t5[index] = json.getString("state");
//                    }
//
//                    // TODO: 지금은 typeOfStore로 거르고 이미지가 없을때 와 있을때를 구분하는데 처음부터 이미지의 null을 고려하면 불필요한 comparsion을 줄일 수 있음
//                    for (index = 0; index < jsonArray.length(); index++) {
//                        switch (typeOfStore) {
//                            case "1":
//                                if (t3[index].equalsIgnoreCase("null")) { // 가게 이미지가 없을때 기본 썸네일
//                                    Store_ListData s_1 = new Store_ListData(getResources().getDrawable(R.drawable.chicken_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_1);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else { // 가게 이미지가 있을때
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_1 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_1);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            case "2":
//                                if (t3[index].equalsIgnoreCase("null")) {
//                                    Store_ListData s_2 = new Store_ListData(getResources().getDrawable(R.drawable.joongsik_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_2);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else {
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_2 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_2);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            case "3":
//                                if (t3[index].equalsIgnoreCase("null")) {
//                                    Store_ListData s_3 = new Store_ListData(getResources().getDrawable(R.drawable.pizza_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_3);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else {
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_3 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_3);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            case "4":
//                                if (t3[index].equalsIgnoreCase("null")) {
//                                    Store_ListData s_4 = new Store_ListData(getResources().getDrawable(R.drawable.hansik_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_4);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else {
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_4 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_4);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            case "5":
//                                if (t3[index].equalsIgnoreCase("null")) {
//                                    Store_ListData s_5 = new Store_ListData(getResources().getDrawable(R.drawable.bunsik_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_5);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else {
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_5 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_5);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            case "6":
//                                if (t3[index].equalsIgnoreCase("null")) {
//                                    Store_ListData s_6 = new Store_ListData(getResources().getDrawable(R.drawable.hamburger_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_6);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else {
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_6 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_6);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            case "7":
//                                if (t3[index].equalsIgnoreCase("null")) {
//                                    Store_ListData s_7 = new Store_ListData(getResources().getDrawable(R.drawable.jokbal_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index]);
//                                    sAdapter.addItem(s_7);
//                                    sAdapter.notifyDataSetChanged();
//                                    break;
//                                } else {
//                                    new Thread(new Runnable() {
//                                        int count = index;
//                                        public Drawable Store_Img;
//                                        Bitmap bitmapSample1;
//
//                                        public void run() {
//                                            try {
//                                                bitmapSample1 = getBitmap(t3[count]);
//                                            } catch (Exception e) {
//
//                                            } finally {
//                                                if (bitmapSample1 != null) {
//                                                    runOnUiThread(new Runnable() {
//                                                        @SuppressLint("NewApi")
//                                                        public void run() {
//                                                            Store_Img = new BitmapDrawable(getResources(), bitmapSample1);
//                                                            Store_ListData s_7 = new Store_ListData(Store_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count]);
//                                                            sAdapter.addItem(s_7);
//                                                            sAdapter.notifyDataSetChanged();
//                                                        }
//                                                    });
//                                                }
//                                            }
//                                        }
//                                    }).start();
//                                    break;
//                                }
//                            default:
//                                break;
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//    }

    public Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.setDoInput(true); //url로 input받는 flag 허용
            connection.connect(); //연결
            is = connection.getInputStream(); // get inputstream
            retBitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem SearchItem = menu.findItem(R.id.action_search);
        SearchView searchview = (SearchView) MenuItemCompat.getActionView(SearchItem);


        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchFunction(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.action_inquire) {
            Intent intent = new Intent(getApplicationContext(), Inquire_Main.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void SearchFunction(String message) {

        RequestParams params = new RequestParams();
        params.put("name", message);
//
        String url_search = Value.url + "search/contents";
        AsyncHttpClient client_search = new AsyncHttpClient();
//
        client_search.post(url_search, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String content = null;
                try {
                    content = new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray jsonArray = new JSONArray(content);

                    Intent intent = new Intent(getApplicationContext(), Search_Result.class);
                    ArrayList<String> arrpos = new ArrayList<String>();
                    ArrayList<String> arrimg = new ArrayList<String>();
                    ArrayList<String> arrname = new ArrayList<String>();
                    ArrayList<String> arrdetail = new ArrayList<String>();
                    ArrayList<String> arrid = new ArrayList<String>();
                    ArrayList<String> arrpnum = new ArrayList<String>();
                    ArrayList<String> arrlau = new ArrayList<String>();

                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject json = jsonArray.getJSONObject(j);

                        String t = json.getString("id");
                        String t1 = json.getString("name");
                        String t2 = json.getString("life_add_url");
                        String t3 = json.getString("phone");
                        String t4 = json.getString("branch");
                        String t5 = json.getString("img");
                        String t6 = json.getString("category_id");

                        arrid.add(t);
                        arrname.add(t1);
                        arrpnum.add(t3);
                        arrlau.add(t2);
                        arrdetail.add(t4);
                        arrimg.add(t5);
                        arrpos.add(t6);


                    }

                    if (arrid.size() == 0) {
                        Toast.makeText(getApplicationContext(), "검색결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        intent.putStringArrayListExtra("id", arrid);
                        intent.putStringArrayListExtra("name", arrname);
                        intent.putStringArrayListExtra("pnum", arrpnum);
                        intent.putStringArrayListExtra("lau", arrlau);
                        intent.putStringArrayListExtra("detail", arrdetail);
                        intent.putStringArrayListExtra("img", arrimg);
                        intent.putStringArrayListExtra("category_id", arrpos);
                        startActivity(intent);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            TextView listText = (TextView) view.findViewById(R.id.text_Store_Id);
            String text = listText.getText().toString();
//            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Store_Main.this, Menu_Main.class);
            intent.putExtra("id", text);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }

    }
}
