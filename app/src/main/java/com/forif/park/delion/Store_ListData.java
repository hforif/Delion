package com.forif.park.delion;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by park on 2015-08-06.
 */
public class Store_ListData {
    private Drawable mStoreIcon;
    private String mStoreName;
    private String mStoreDetail;
    private Drawable mStoreCallIcon;
    private String mStoreId;
    private String mStorePnumber;

    public Store_ListData(Drawable Icon, String Name, String Detail , Drawable call, String Id, String pnumber){
        mStoreIcon = Icon;
        mStoreName = Name;
        mStoreDetail = Detail;
        mStoreCallIcon = call;
        mStoreId = Id;
        mStorePnumber = pnumber;

    }
    public Drawable getmStoreIcon(){return mStoreIcon;}
    public String getmStoreName(){return mStoreName;}
    public String getmStoreDetail(){return mStoreDetail;}
    public Drawable getmStoreCallIcon(){return mStoreCallIcon;}
    public String getmStoreId(){return mStoreId;}
    public String getmPnumber(){return mStorePnumber;}
}
