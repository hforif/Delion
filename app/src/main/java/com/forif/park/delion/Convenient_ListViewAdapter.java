package com.forif.park.delion;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by park on 2015-08-06.
 */
public class Convenient_ListViewAdapter extends BaseAdapter {


    private Convenient_ListData cListCheck; //검사용으로 쓰이는 변수이기 때문에 Check
    private Context cContext; //Context변수 mContext 선언

    private ArrayList<Convenient_ListData> cListData; //커스터마이징 자료형을 어레이로 받는 어레이리스트 변수 mListData 선언

    public Convenient_ListViewAdapter(Context cContext){
        super();
        this.cContext = cContext;
        cListData = new ArrayList<Convenient_ListData>();
    }
    @Override
    public int getCount() {
        return cListData.size();
    }

    @Override
    public Object getItem(int position) {
        return cListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Convenient_ViewHolder holder;
        if(v == null){
            holder = new Convenient_ViewHolder();
            v= ((LayoutInflater)cContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.conveninet_store_listview_item, null);
            holder.cIcon = (ImageView)v.findViewById(R.id.image_convenient_store_icon);
            holder.cText = (TextView)v.findViewById(R.id.text_convenient_store);
            holder.cDetail = (TextView)v.findViewById(R.id.text_convenient_store_detail);
            holder.cCalling = (ImageView)v.findViewById(R.id.image_cCalling);
            holder.cId = (TextView)v.findViewById(R.id.text_convenient_store_id);
            holder.cUrl = (TextView)v.findViewById(R.id.text_convenient_store_url);
            holder.cPnumber = (TextView)v.findViewById(R.id.text_convenient_store_pnumber);
            v.setTag(holder);
        }else{
            holder = (Convenient_ViewHolder)v.getTag();
        }

        cListCheck = cListData.get(position);
        if (cListCheck.getcStoreDetail().equals("null")){ // 지점 정보가 없을때 Invisivle
            holder.cDetail.setVisibility(View.INVISIBLE);
        }else{
            holder.cDetail.setText(cListCheck.getcStoreDetail());
        }
        holder.cIcon.setImageDrawable(cListCheck.getcStoreIcon());
        holder.cText.setText(cListCheck.getcStoreText());
        holder.cDetail.setText(cListCheck.getcStoreDetail());
        holder.cCalling.setImageResource(R.drawable.call_button);
        holder.cId.setText(cListCheck.getcStoreId());
        holder.cUrl.setText(cListCheck.getcStoreUrl());
        holder.cPnumber.setText(cListCheck.getcStorePnumber());

        final TextView finalV2 = (TextView)v.findViewById(R.id.text_convenient_store_pnumber);

        holder.cCalling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pn = finalV2;
                String pnumber = pn.getText().toString();
                if(pnumber.equals("")) { // 가게 전화번호가 없을때 Toast
                    Toast toast = Toast.makeText(cContext , "전화번호가 없습니다." , Toast.LENGTH_LONG);
                    toast.show();
                } else { ///가게 전화번호가 있을때 다이얼로 넘어감
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + pnumber));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    cContext.startActivity(intent);
                }
            }
        });


        return v;
    }
    public void addItem(Convenient_ListData MenuData) {
        cListData.add(MenuData);
    }


    //온클릭 함수


}
