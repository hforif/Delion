package com.forif.park.delion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.forif.park.delion.R.id.frag_map;
//편의점,약국 등과 같은 list에서 하나를 눌렀을때 실행되는 activity


public class Convenient_Store_Detail extends ActionBarActivity implements OnMapReadyCallback {

//    Button button1;
//    Button button2;
//    Button button3;
//    Button button4;

    String urlm;
    String name;
    int a, b, c;


    String xx;
    String yy;

    private Convenient_Interface interfaces;
    private Retrofit retrofit;
    public static List<Convenient_Detail_JSONData> convDetailJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.convenient_store_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView fTextViewOpenTime, fTextViewPNum, fTextViewAdd;
        fTextViewOpenTime = (TextView) findViewById(R.id.text_opentime);
        fTextViewPNum = (TextView) findViewById(R.id.text_phone_num);
        fTextViewAdd = (TextView) findViewById(R.id.text_life_add);
        fTextViewAdd.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        fTextViewAdd.setSingleLine(true);
        fTextViewAdd.setSelected(true);


        String idOfTarget = getIntent().getStringExtra("id");

        retrofit = new Retrofit.Builder()
                .baseUrl(Convenient_Interface.ServerUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interfaces = retrofit.create(Convenient_Interface.class);

        Call<List<Convenient_Detail_JSONData>> callConvDetail = interfaces.getConvDetail(Integer.parseInt(idOfTarget));
        callConvDetail.enqueue(new Callback<List<Convenient_Detail_JSONData>>() {
            @Override
            public void onResponse(Call<List<Convenient_Detail_JSONData>> call, Response<List<Convenient_Detail_JSONData>> response) {
                convDetailJSON = response.body();

                fTextViewOpenTime.setText(convDetailJSON.get(0).getmConvOpenTime());
                fTextViewPNum.setText(convDetailJSON.get(0).getmConvPNum());
                fTextViewAdd.setText(convDetailJSON.get(0).getmConvAdd());
                String temp=convDetailJSON.get(0).getmConvName();
                if (!(convDetailJSON.get(0).getmConvDetail().equals("null\r"))) {
                    temp=temp+" "+convDetailJSON.get(0).getmConvDetail();
                }
                getSupportActionBar().setTitle(temp);
            }

            @Override
            public void onFailure(Call<List<Convenient_Detail_JSONData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(frag_map);
        mapFragment.getMapAsync(this);

        ImageView ConPhoneCall = (ImageView) findViewById(R.id.button_ConDial);

        ConPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ConPnumber = fTextViewPNum.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ConPnumber));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        urlm = getIntent().getStringExtra("url");
        name = getIntent().getStringExtra("name");

        a = urlm.indexOf("x=");
        b = urlm.indexOf("&y=");
        c = urlm.indexOf("&enc=");


        xx = urlm.substring(a + 2, b);
        yy = urlm.substring(b + 3, c);


        /*
        tv1 = (TextView)findViewById(R.id.opentime);
        tv2 = (TextView)findViewById(R.id.lifecall);
        tv3 = (TextView)findViewById(R.id.lifename);
        */
//        String url = base_url + lm + idOfTarget;

//        AsyncHttpClient client = new AsyncHttpClient();

//        client.get(url, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                String content = null;
//                try {
//                    content = new String(responseBody, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    JSONArray jsonArray = new JSONArray(content);
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject json = jsonArray.getJSONObject(i);
//
//                        String t = json.getString("id");
//                        String t1 = json.getString("name");
//                        String t2 = json.getString("opentime");
//                        String t3 = json.getString("phone");
//                        //                               String t4 = json.getString("img");
//                        String t5 = json.getString("life_add");
//                        String t7 = json.getString("state");
//
//                        fTextViewOpenTime.setText(t2);
//                        fTextViewPNum.setText(t3);
////                                tv3.setText(t1);
//                        fTextViewAdd.setText(t5);
////                                TextView textview = (TextView) findViewById(R.id.title);
////                                textview.setText(t1);
//                        getSupportActionBar().setTitle(t1);
////                                Toast.makeText(getApplicationContext(), t+t1+t2+t3+t4+t5+t6, Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
//            }
//        });

//        MapFragment mapFragment = (MapFragment) getFragmentManager()
//                .findFragmentById(frag_map);
//        mapFragment.getMapAsync(this);
//
//        ImageView ConPhoneCall = (ImageView) findViewById(R.id.button_ConDial);
//
//        ConPhoneCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String ConPnumber = fTextViewPNum.getText().toString();
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:" + ConPnumber));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);

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

    @Override
    public void onMapReady(GoogleMap map) {

        double y = Double.parseDouble(xx);
        double x = Double.parseDouble(yy);

        String title = name;
        LatLng location = new LatLng(x, y);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 17));

        map.addMarker(new MarkerOptions()
                .title(title)
                .position(location));
    }

}

