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

public class Store_ListViewAdapter extends BaseAdapter {

    private Store_ListData sListCheck;
    private Context sContext;
    private ArrayList<Store_ListData> sListData;

    public Store_ListViewAdapter(Context sContext) {
        super();
        this.sContext = sContext;
        sListData = new ArrayList<Store_ListData>();

    }

    @Override
    public int getCount() {
        return sListData.size();
    }

    @Override
    public Object getItem(int position) {
        return sListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Store_ViewHolder holder;
        if (v == null) {
            holder = new Store_ViewHolder();
            v = ((LayoutInflater) sContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.store_listview_item, null);
            holder.sIcon = (ImageView) v.findViewById(R.id.Store_Label_Icon);
            holder.sName = (TextView) v.findViewById(R.id.Store_Name_Text);
            holder.sDetail = (TextView) v.findViewById(R.id.Store_Name_Detail);
            holder.sCalling = (ImageView) v.findViewById(R.id.sCalling);
            holder.sId = (TextView) v.findViewById(R.id.Store_Id);
            holder.sPnumber = (TextView) v.findViewById(R.id.Store_Pnumber);
            v.setTag(holder);
        } else {
            holder = (Store_ViewHolder) v.getTag();
        }

        sListCheck = sListData.get(position);
       if(sListCheck.getmStoreDetail().equals("null")) {
            holder.sDetail.setVisibility(View.INVISIBLE);
        } else {
            holder.sDetail.setText(sListCheck.getmStoreDetail());
        }

        holder.sIcon.setImageDrawable(sListCheck.getmStoreIcon());
        holder.sName.setText(sListCheck.getmStoreName());
        holder.sCalling.setImageResource(R.drawable.call_button);
        holder.sId.setText(sListCheck.getmStoreId());
        holder.sPnumber.setText(sListCheck.getmPnumber());


        final TextView finalV = (TextView) v.findViewById(R.id.Store_Pnumber);

        holder.sCalling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pn = finalV;
                String pnumber = pn.getText().toString();
                if(pnumber.equals("")) { // 전화번호가 없을 때 Toast
                    Toast toast = Toast.makeText(sContext , "전화번호가 없습니다." , Toast.LENGTH_LONG);
                    toast.show();
                }else { // 전화번호 있을 때
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + pnumber));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sContext.startActivity(intent);
                }


            }
        });
        return v;
    }
    public void addItem(Store_ListData StoreData) {
        sListData.add(StoreData);
    }





}