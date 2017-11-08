package com.forif.park.delion;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Park on 2015-11-22.
 */
public class Tab_Bar_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;
    ServerUrl Value = new ServerUrl();
    private ViewPager mViewPager;
    AlertDialog.Builder dialog;
    /////////////////
    private ListView mListView = null; //메인리스트뷰를 받을 자바상의 리스트뷰 변수 선언
    private Delivery_ListViewAdapter mAdapter = null; //메인리스트뷰의 어뎁터로 쓰일 어뎁터 변수 선언
    /////////////////
    private final long   FINSH_INTERVAL_TIME    = 2000;
    private long      backPressedTime        = 0;
    /////////////////
//    Activity act = this;
    private GridView gridView = null;
    HashMap<String, Bitmap> picArr = new HashMap<String, Bitmap>();
    ArrayList<String> textArr = new ArrayList<String>();
    private gridadapter cAdapter = null;
    //    ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    ////////////////
    private dgridadapter dAdapter = null;
    ArrayList<String> dTextArr = new ArrayList<String>();
    HashMap<String, Bitmap>dPicArr = new HashMap<String, Bitmap>();
    private GridView dGridView = null;

    @Override
    public void onBackPressed() {
        long tempTime        = System.currentTimeMillis();
        long intervalTime    = tempTime - backPressedTime;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else if ( 0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime ) {
            super.onBackPressed();
        }
        else {
            backPressedTime = tempTime;


            Toast.makeText(getApplicationContext(),"'뒤로'버튼을 한 번 더 누르면 종료.", Toast.LENGTH_SHORT).show();
        }
    }
    private static final int MY_REQUEST_PERMISSION_CODE = 2;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_REQUEST_PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "권한을 획득했습니다.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Tab_Bar_Main.this, "권한이 없습니다. 앱을 종료합니다.",Toast.LENGTH_LONG);
                    finish();
                }

                return;
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("홈");

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED
                ||ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                ||ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CALL_PHONE}, MY_REQUEST_PERMISSION_CODE);

            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.CALL_PHONE},
                        MY_REQUEST_PERMISSION_CODE);
            }



        }





        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationView navigationViewtop = (NavigationView) findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        navigationViewtop.setNavigationItemSelectedListener(this);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



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
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
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
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
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

        if(id == R.id.action_inquire){
            Intent intent = new Intent(getApplicationContext(), Inquire_Main.class);
            startActivity(intent);
        }

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.intro_app){
            Intent intent = new Intent(Tab_Bar_Main.this, More_intro_app.class);
            startActivity(intent);
        }
        else if(id == R.id.intro_dev){
            Intent intent = new Intent(Tab_Bar_Main.this, More_intro_dev.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @SuppressLint("validFragment")
    public  class Fragment_Delivery extends Fragment{

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.delivery_activity, container, false);
            final Activity activity = getActivity();

//            mAdapter = new Delivery_ListViewAdapter(getApplicationContext());
//            mListView = (ListView) view.findViewById(R.id.List_View_Main);
//            mListView.setAdapter(mAdapter);
//
//            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getApplicationContext(), Store_Main.class);
//                    intent.putExtra("position", position + 1);
//                    startActivity(intent);
//                    overridePendingTransition(0, 0);
//                }
//            });
            dAdapter = new dgridadapter(getApplicationContext());
            dGridView = (GridView)view.findViewById(R.id.delivery_grid_view);
            dGridView.setAdapter(dAdapter);


            String base_url = Value.url;
            String m =  "main/basiclist/";

            String url = base_url + m + "0";
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
                        final Bitmap dbm1 = BitmapFactory.decodeResource(getResources(), R.drawable.chicken_menu);
                        final Bitmap dbm2 = BitmapFactory.decodeResource(getResources(), R.drawable.jungsik_menu);
                        final Bitmap dbm3 = BitmapFactory.decodeResource(getResources(), R.drawable.pizza_menu);
                        final Bitmap dbm4 = BitmapFactory.decodeResource(getResources(), R.drawable.hansik_menu);
                        final Bitmap dbm5 = BitmapFactory.decodeResource(getResources(), R.drawable.bunsik_menu);
                        final Bitmap dbm6 = BitmapFactory.decodeResource(getResources(), R.drawable.fastfood_menu);
                        final Bitmap dbm7 = BitmapFactory.decodeResource(getResources(), R.drawable.jokbal_menu);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject json = jsonArray.getJSONObject(i);

                            String t = json.getString("id");
                            String t1 = json.getString("listname");
//                        String t2 = json.getString("img");

//                            Delivery_ListData u = new Delivery_ListData(null, t1);
//                            mAdapter.addItem(u);
//                            mAdapter.notifyDataSetChanged();

                            dTextArr.add(t1);
                        }

                        dPicArr.put("8",dbm1);
                        dPicArr.put("9",dbm2);
                        dPicArr.put("10",dbm3);
                        dPicArr.put("11",dbm4);
                        dPicArr.put("12",dbm5);
                        dPicArr.put("13",dbm6);
                        dPicArr.put("14",dbm7);

                        dAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    Toast.makeText(activity, "인터넷 연결상태가 좋지 않습니다.", Toast.LENGTH_SHORT).show();
                }


            });


            dGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), Store_Main.class);
                    intent.putExtra("position", position +1);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            });

            return view;
        }

    }
    @SuppressLint("validFragment")
    public class Fragment_Convenient extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.convenient_main, container, false);
            final Activity activity = getActivity();

            cAdapter = new gridadapter(getApplicationContext());
            gridView = (GridView) view.findViewById(R.id.convenient_grid_view);
            gridView.setAdapter(cAdapter);



            String base_url = Value.url;
            String m =  "main/basiclist/";

            String url = base_url + m + "1";
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


                        final Bitmap bm11 = BitmapFactory.decodeResource(getResources(), R.drawable.hospital_menu);
                        final Bitmap bm10 = BitmapFactory.decodeResource(getResources(), R.drawable.drug_store_menu);
                        final Bitmap bm14 = BitmapFactory.decodeResource(getResources(), R.drawable.bank_menu);
                        final Bitmap bm9 = BitmapFactory.decodeResource(getResources(), R.drawable.convenience_store_menu);
                        final Bitmap bm12 = BitmapFactory.decodeResource(getResources(), R.drawable.print);
                        final Bitmap bm8 = BitmapFactory.decodeResource(getResources(), R.drawable.laundry_menu);
                        final Bitmap bmdf = BitmapFactory.decodeResource(getResources(), R.drawable.capb);
                        final Bitmap bm13 = BitmapFactory.decodeResource(getResources(), R.drawable.mungu_menu);
                        final Bitmap bm15 = BitmapFactory.decodeResource(getResources(), R.drawable.susun_menu);






                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject json = jsonArray.getJSONObject(i);

                            String t = json.getString("id");
                            String t1 = json.getString("listname");
//                        String t2 = json.getString("img");
//                            switch (t) {
//                                case "8":
//                                    picArr.put(t, bm8);
//                                    break;
//                                case "9":
//                                    picArr.put(t, bm9);
//                                    break;
//                                case "10":
//                                    picArr.put(t, bm10);
//                                    break;
//                                case "11":
//                                    picArr.put(t, bm11);
//                                    break;
//                                case "12":
//                                    picArr.put(t, bm12);
//                                    break;
//                                case "13":
//                                    picArr.put(t, bm13);
//                                    break;
//                                case "14":
//                                    picArr.put(t, bm14);
//                                    break;
//                                case "15":
//                                    picArr.put(t, bm15);
//                                    break;
//                                default:
//                                    picArr.put(t, bmdf);
//                                    break;
//                            }

                            textArr.add(t1);

                        }

                        picArr.put("8", bm8);
                        picArr.put("9", bm9);
                        picArr.put("10", bm10);
                        picArr.put("11", bm11);
                        picArr.put("12", bm12);
                        picArr.put("13", bm13);
                        picArr.put("14", bm14);
                        picArr.put("15", bm15);

                        cAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }
            });


            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), Convenient_Store_Main.class);
                    intent.putExtra("position", position + 8);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            });

            return view;
        }

    }

    public class gridadapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context = null;

        public gridadapter(Context context){
            super();
            this.context = context;
        }

        @Override
        public int getCount() {
            return picArr.size();
        }

        @Override
        public Object getItem(int position) {
            return picArr.get(Integer.toString(position+8));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.convenient_grid_item, parent, false);
            }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.grid_image);
            TextView textView = (TextView)convertView.findViewById(R.id.grid_text);
            imageView.setImageBitmap(picArr.get(Integer.toString(position + 8)));
            textView.setText(textArr.get(position));




            return convertView;
        }
    }

    public class dgridadapter extends BaseAdapter {
        LayoutInflater inflater;
        Context context = null;

        public dgridadapter(Context context){
            super();
            this.context = context;
        }

        @Override
        public int getCount() {
            return dPicArr.size();
        }

        @Override
        public Object getItem(int position) {
            return dPicArr.get(Integer.toString(position+8));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null){
                convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.convenient_grid_item, parent, false);
            }
            ImageView imageView = (ImageView)convertView.findViewById(R.id.grid_image);
            TextView textView = (TextView)convertView.findViewById(R.id.grid_text);
            imageView.setImageBitmap(dPicArr.get(Integer.toString(position + 8)));
            textView.setText(dTextArr.get(position));


            return convertView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position ==0){
                return new Fragment_Delivery();
            }
            if(position ==1){
                return new Fragment_Convenient();
            }
            else{
                return PlaceholderFragment.newInstance(position);
            }
        }

        @Override
        public int getCount() {

            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "배달";
                case 1:
                    return "생활정보";
            }
            return null;
        }
    }


    public static class PlaceholderFragment extends Fragment {


        private static final String ARG_SECTION_NUMBER = "section_number";


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.delivery_activity, container, false);

//            Button button_test = (Button) rootView.findViewById(R.id.button_test);
            final Activity root = getActivity();
//            button_test.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(root, "dd", Toast.LENGTH_SHORT).show();
//
//                }
//            });


            return rootView;
        }
    }

}
