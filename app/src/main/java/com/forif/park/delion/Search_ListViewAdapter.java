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

import com.forif.park.delion.R;import java.util.ArrayList;

/**
 * Created by park on 2015-08-06.
 */
public class Search_ListViewAdapter extends BaseAdapter {


    private Search_ListData SearchListCheck; //검사용으로 쓰이는 변수이기 때문에 Check
    private Context SearchContext; //Context변수 mContext 선언

    private ArrayList<Search_ListData> SearchListData; //커스터마이징 자료형을 어레이로 받는 어레이리스트 변수 mListData 선언

    public Search_ListViewAdapter(Context SearchContext){
        super();
        this.SearchContext = SearchContext;
        SearchListData = new ArrayList<Search_ListData>();
    }
    @Override
    public int getCount() {
        return SearchListData.size();
    }

    @Override
    public Object getItem(int position) {
        return SearchListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Search_ViewHolder holder;
        if(v == null){
            holder = new Search_ViewHolder();
            v= ((LayoutInflater)SearchContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.search_listview_item, null);
            holder.SearchImage = (ImageView)v.findViewById(R.id.search_icon);
            holder.SearchName = (TextView)v.findViewById(R.id.search_text);
            holder.SearchDetail = (TextView)v.findViewById(R.id.search_detail);
            holder.SearchCall = (ImageView)v.findViewById(R.id.searchCalling);
            holder.SearchId = (TextView)v.findViewById(R.id.search_id);
            holder.SearchPnum = (TextView)v.findViewById(R.id.search_pnum);
            holder.SearchLau = (TextView)v.findViewById(R.id.search_lau);
            v.setTag(holder);
        }else{
            holder = (Search_ViewHolder)v.getTag();
        }

        SearchListCheck = SearchListData.get(position);
        if (SearchListCheck.getSearchDetail().equals("null")){
            holder.SearchDetail.setVisibility(View.INVISIBLE);
        }else{
            holder.SearchDetail.setText(SearchListCheck.getSearchDetail());
        }

        holder.SearchImage.setImageDrawable(SearchListCheck.getSearchImage());
        holder.SearchCall.setImageResource(R.drawable.call_button);
        holder.SearchName.setText(SearchListCheck.getSearchNameText());
        holder.SearchId.setText(SearchListCheck.getSearchIdText());
        holder.SearchPnum.setText(SearchListCheck.getSearchPnumText());
        holder.SearchLau.setText(SearchListCheck.getSearchLauText());

        final TextView finalV = (TextView)v.findViewById(R.id.search_pnum);

        holder.SearchCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView pntv = finalV;
                String pnum = pntv.getText().toString();
                if(pnum.equals("")) {
                    Toast toast = Toast.makeText(SearchContext , "전화번호가 없습니다." , Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + pnum));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    SearchContext.startActivity(intent);
                }
            }
        });
        return v;
    }
    public void addItem(Search_ListData MenuData) {
        SearchListData.add(MenuData);
    }


    //온클릭 함수


}
