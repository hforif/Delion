package com.forif.park.delion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Search_Result extends ActionBarActivity {
    private ListView SListView = null;
    private Search_ListViewAdapter SAdapter = null;
    int index;
    String[] T , T1 , T2 , T3 , T4 , T5 , T6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);

        SAdapter = new Search_ListViewAdapter(getApplicationContext());
        SListView = (ListView) findViewById(R.id.search_list_body);
        SListView.setAdapter(SAdapter);

        SListView.setOnItemClickListener(new SearchListClickHandler());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("검색결과");



        ArrayList<String> search_arrname, search_arrdetail, search_arrid, search_arrpnum, search_arrlau , search_arrimg , search_arrpos;

        search_arrpos = getIntent().getStringArrayListExtra("category_id");
        search_arrimg = getIntent().getStringArrayListExtra("img");
        search_arrname = getIntent().getStringArrayListExtra("name");
        search_arrdetail = getIntent().getStringArrayListExtra("detail");
        search_arrid = getIntent().getStringArrayListExtra("id");
        search_arrpnum = getIntent().getStringArrayListExtra("pnum");
        search_arrlau = getIntent().getStringArrayListExtra("lau");

        T = new String[search_arrid.size()];
        T1 = new String[search_arrid.size()];
        T2 = new String[search_arrid.size()];
        T3 = new String[search_arrid.size()];
        T4 = new String[search_arrid.size()];
        T5 = new String[search_arrid.size()];
        T6 = new String[search_arrid.size()];

        for (index = 0; index < search_arrid.size(); index++) {
            T[index] = search_arrpos.get(index);
            T1[index] = search_arrimg.get(index);
            T2[index] = search_arrname.get(index);
            T3[index] = search_arrdetail.get(index);
            T4[index] = search_arrid.get(index);
            T5[index] = search_arrpnum.get(index);
            T6[index] = search_arrlau.get(index);

            switch(search_arrpos.get(index)){
                case "1":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.chicken_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "2":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.joongsik_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "3":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.pizza_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "4":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.hansik_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "5":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.bunsik_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "6":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.hamburger_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "7":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.jokbal_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "8":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.laundry_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "9":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.convenience_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "10":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.drug_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "11":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.hospital_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "12":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.print_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "13":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.mungu_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "14":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.bank_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
                                            }
                                        });
                                    }
                                }
                            }
                        }).start();
                        break;
                    }
                case "15":
                    if(search_arrimg.get(index).equals("null")){
                        Search_ListData S = new Search_ListData(getResources().getDrawable(R.drawable.susun_thumbnails), search_arrname.get(index), search_arrdetail.get(index), getResources().getDrawable(R.drawable.call_button), search_arrid.get(index), search_arrpnum.get(index), search_arrlau.get(index));
                        SAdapter.addItem(S);
                        SAdapter.notifyDataSetChanged();
                        break;
                    }else{
                        new Thread(new Runnable() {
                            int count = index;
                            public Drawable Search_Img;
                            Bitmap bitmapSample1;

                            public void run() {
                                try {
                                    bitmapSample1 = getBitmap(T1[count]);
                                } catch (Exception e) {

                                } finally {
                                    if (bitmapSample1 != null) {
                                        runOnUiThread(new Runnable() {
                                            @SuppressLint("NewApi")
                                            public void run() {
                                                Search_Img = new BitmapDrawable(getResources(), bitmapSample1);
                                                Search_ListData u_1 = new Search_ListData(Search_Img,  T2[count], T3[count], getResources().getDrawable(R.drawable.call_button), T4[count], T5[count], T6[count]);
                                                SAdapter.addItem(u_1);
                                                SAdapter.notifyDataSetChanged();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try{
            imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.setDoInput(true); //url로 input받는 flag 허용
            connection.connect(); //연결
            is = connection.getInputStream(); // get inputstream
            retBitmap = BitmapFactory.decodeStream(is);
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            if(connection!=null) {
                connection.disconnect();
            }
            return retBitmap;
        }
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

    public class SearchListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            TextView listText = (TextView) view.findViewById(R.id.search_id);
            final String text = listText.getText().toString();
            TextView urlText = (TextView) view.findViewById(R.id.search_lau);
            final String url = urlText.getText().toString();
            TextView nameText = (TextView) view.findViewById(R.id.search_text);
            final String name = nameText.getText().toString();

            if (Integer.valueOf(text) <= 32) {
                Intent intent = new Intent(Search_Result.this, Menu_Main.class);
                intent.putExtra("id", text);
                startActivity(intent);
                overridePendingTransition(0, 0);

            } else {
                Intent intent = new Intent(Search_Result.this, Convenient_Store_Detail.class);
                intent.putExtra("id", text);
                intent.putExtra("url", url);
                intent.putExtra("name", name);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        }

    }


}
