package com.forif.park.delion;

import android.graphics.drawable.Drawable;

/**
 * Created by park on 2015-08-06.
 */
public class Convenient_ListData {
    private Drawable cStoreIcon;
    private Drawable cStoreCallIcon;
    private String cStoreText;
    private String cStoreDetail;
    private String cStoreId;
    private String cStoreUrl;
    private String cStorePnumber;

    public Convenient_ListData(Drawable Icon, String Text, String Detail, Drawable Call , String id, String url , String Pnumber){
        cStoreIcon = Icon;
        cStoreText = Text;
        cStoreDetail = Detail;
        cStoreCallIcon = Call;
        cStoreId = id;
        cStoreUrl = url;
        cStorePnumber = Pnumber;
    }
    public Drawable getcStoreIcon(){return cStoreIcon;}
    public String getcStoreText(){return cStoreText;}
    public String getcStoreDetail(){return cStoreDetail;}
    public Drawable getcStoreCallIcon() {return cStoreCallIcon;}
    public String getcStoreId(){return cStoreId;}
    public String getcStoreUrl(){return cStoreUrl;}
    public String getcStorePnumber() { return  cStorePnumber;}
}
