package com.forif.park.delion;

import android.graphics.drawable.Drawable;

/**
 * Created by park on 2015-08-06.
 */

public class Convenient_ListData {
    private Drawable cStoreIcon;
    private Drawable cStoreCallIcon;
    private String cStoreName;
    private String cStoreDetail;
    private String cStoreId;
    private String cStoreUrl;
    private String cStorePnumber;

    public Convenient_ListData(Drawable Icon, String Name, String Detail, Drawable Call , String id, String url , String Pnumber){
        cStoreIcon = Icon;
        cStoreName = Name;
        cStoreDetail = Detail;
        cStoreCallIcon = Call;
        cStoreId = id;
        cStoreUrl = url;
        cStorePnumber = Pnumber;
    }
    public Drawable getcStoreIcon(){return cStoreIcon;}
    public String getcStoreName(){return cStoreName;}
    public String getcStoreDetail(){return cStoreDetail;}
    public Drawable getcStoreCallIcon() {return cStoreCallIcon;}
    public String getcStoreId(){return cStoreId;}
    public String getcStoreUrl(){return cStoreUrl;}
    public String getcStorePnumber() { return  cStorePnumber;}
}
