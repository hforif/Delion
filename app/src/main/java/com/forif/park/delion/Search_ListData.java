package com.forif.park.delion;

import android.graphics.drawable.Drawable;

/**
 * Created by park on 2015-08-06.
 */
public class Search_ListData {
    private Drawable SearchImage;
    private String SearchNameText;
    private String SearchDetail;
    private Drawable SearchCallIcon;
    private String SearchIdText;
    private String SearchPnumText;
    private String SearchLauText;

    public Search_ListData(Drawable Image ,String Name, String Detail , Drawable Call , String Id , String Pnum,  String Lau){
        SearchImage = Image;
        SearchNameText = Name;
        SearchDetail = Detail;
        SearchCallIcon = Call;
        SearchIdText = Id;
        SearchPnumText = Pnum;
        SearchLauText = Lau;
    }
    public Drawable getSearchImage(){return SearchImage;}
    public String getSearchNameText(){return SearchNameText;}
    public String getSearchDetail() { return SearchDetail;}
    public Drawable getSearchCallIcon() { return SearchCallIcon; }
    public String getSearchIdText(){return SearchIdText;}
    public String getSearchPnumText(){return SearchPnumText;}
    public String getSearchLauText(){return SearchLauText;}
}
