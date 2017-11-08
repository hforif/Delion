package com.forif.park.delion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Convenient_Store_Main extends ActionBarActivity {
    private ListView cListView = null; //메인리스트뷰를 받을 자바상의 리스트뷰 변수 선언
    private Convenient_ListViewAdapter cAdapter = null; //메인리스트뷰의 어뎁터로 쓰일 어뎁터 변수 선언
    ServerUrl Value = new ServerUrl();

    private String aa;
    private String url;

    String[] t ,t1 , t2 , t3 , t4 , t5 , t6;
    int index;
    //탭바
//    Button button1;
//    Button button2;
//    Button button3;
//    Button button4;

    //---------

    EditText search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convenient_store_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //검색기능

        cAdapter = new Convenient_ListViewAdapter(getApplicationContext());
        cListView = (ListView)findViewById(R.id.convenient_store_list_view);
        cListView.setAdapter(cAdapter);
        cListView.setOnItemClickListener(new ListClickHandler());



        //

        String base_url = Value.url;
        String l = "lifeinfo/life_list/";

        //
        int a = getIntent().getExtras().getInt("position");
        aa = String.valueOf(a);
        url = base_url + l + aa;
        //
        switch (aa){
            case "8":
                getSupportActionBar().setTitle("세탁소");
                break;
            case "9":
                getSupportActionBar().setTitle("편의점");
                break;
            case "10":
                getSupportActionBar().setTitle("약국");
                break;
            case "11":
                getSupportActionBar().setTitle("병원");
                break;
            case "12":
                getSupportActionBar().setTitle("인쇄소");
                break;
            case "13":
                getSupportActionBar().setTitle("문구점");
                break;
            case "14":
                getSupportActionBar().setTitle("은행, ATM");
                break;
            case "15":
                getSupportActionBar().setTitle("의류수선");
                break;
            default:
                getSupportActionBar().setTitle("no-data");
                break;
        }

        //
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String content = null;
                try {
                    content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    JSONArray jsonArray = new JSONArray(content);
                    t = new String[jsonArray.length()];
                    t1 = new String[jsonArray.length()];
                    t2 = new String[jsonArray.length()];
                    t3 = new String[jsonArray.length()];
                    t4 = new String[jsonArray.length()];
                    t5 = new String[jsonArray.length()];
                    t6 = new String[jsonArray.length()];

                    for (index = 0 ; index < jsonArray.length(); index++) {

                        JSONObject json = jsonArray.getJSONObject(index);

                        t[index] = json.getString("id");
                        t1[index] = json.getString("name");
                        t2[index] = json.getString("branch");
                        t3[index] = json.getString("img");
                        t4[index] = json.getString("life_add_url");
                        t5[index] = json.getString("phone");
                        t6[index] = json.getString("state");
                    }
                    for (index = 0 ; index < jsonArray.length(); index++) {
                        switch (aa) {
                            case "8":
                                if (t3[index].equalsIgnoreCase("null")) { //가게 이미지가 없을때 기본 썸네일
                                    Convenient_ListData u_1 = new Convenient_ListData(getResources().getDrawable(R.drawable.laundry_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_1);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else { //가게 이미지가 있을때
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_1 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_1);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "9":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_2 = new Convenient_ListData(getResources().getDrawable(R.drawable.convenience_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_2);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_2 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_2);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "10":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_3 = new Convenient_ListData(getResources().getDrawable(R.drawable.drug_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_3);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_3 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_3);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "11":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_4 = new Convenient_ListData(getResources().getDrawable(R.drawable.hospital_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_4);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_4 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_4);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "12":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_5 = new Convenient_ListData(getResources().getDrawable(R.drawable.print_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_5);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_5 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_5);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "13":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_6 = new Convenient_ListData(getResources().getDrawable(R.drawable.mungu_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_6);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_6 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_6);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "14":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_7 = new Convenient_ListData(getResources().getDrawable(R.drawable.bank_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_7);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_7 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_7);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            case "15":
                                if (t3[index].equalsIgnoreCase("null")) {
                                    Convenient_ListData u_8 = new Convenient_ListData(getResources().getDrawable(R.drawable.susun_thumbnails), t1[index], t2[index], getResources().getDrawable(R.drawable.call_button), t[index], t4[index], t5[index]);
                                    cAdapter.addItem(u_8);
                                    cAdapter.notifyDataSetChanged();
                                    break;
                                } else {
                                    new Thread(new Runnable() {
                                        int count = index;
                                        public Drawable Convenient_Img;
                                        Bitmap bitmapSample1;

                                        public void run() {
                                            try {
                                                bitmapSample1 = getBitmap(t3[count]);
                                            } catch (Exception e) {

                                            } finally {
                                                if (bitmapSample1 != null) {
                                                    runOnUiThread(new Runnable() {
                                                        @SuppressLint("NewApi")
                                                        public void run() {
                                                            Convenient_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                            Convenient_ListData u_8 = new Convenient_ListData(Convenient_Img, t1[count], t2[count], getResources().getDrawable(R.drawable.call_button), t[count], t4[count], t5[count]);
                                                            cAdapter.addItem(u_8);
                                                            cAdapter.notifyDataSetChanged();
                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }).start();
                                    break;
                                }
                            default:
                                break;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

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

    public void SearchFunction(String message){

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

                    if (arrid.size() == 0){
                        Toast.makeText(getApplicationContext(), "검색결과가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        intent.putStringArrayListExtra("id", arrid);
                        intent.putStringArrayListExtra("name", arrname);
                        intent.putStringArrayListExtra("pnum", arrpnum);
                        intent.putStringArrayListExtra("lau", arrlau);
                        intent.putStringArrayListExtra("detail" , arrdetail);
                        intent.putStringArrayListExtra("img" , arrimg);
                        intent.putStringArrayListExtra("category_id" , arrpos);
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
        }

        else if(id == R.id.action_inquire){
            Intent intent = new Intent(getApplicationContext(), Inquire_Main.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.text_convenient_store_id);
            TextView urlText = (TextView) view.findViewById(R.id.text_convenient_store_url);
            TextView nameText = (TextView) view.findViewById(R.id.text_convenient_store);
            String url = urlText.getText().toString();
            String text = listText.getText().toString();
            String name = nameText.getText().toString();
//            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Convenient_Store_Main.this, Convenient_Store_Detail.class);
            intent.putExtra("id", text);
            intent.putExtra("url", url);
            intent.putExtra("name", name);
            startActivity(intent);
            overridePendingTransition(0,0);
        }

    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//    }
}
