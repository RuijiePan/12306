package ruijie.com.my12306.components.storage;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import ruijie.com.my12306.db.dao.User;
import ruijie.com.my12306.util.SettingPrefUtil;

/**
 * Created by prj on 2016/8/15.
 */

public class UserStorage {

    private Context mContext;
    private String cookie;
    private String token;
    private User user;

    public UserStorage(Context context){
        this.mContext = context;
    }

    public User getUser(){
        return user;
    }

    public void logout(){
        if(user.getUid().equals(SettingPrefUtil.getLoginUid(mContext))){
            SettingPrefUtil.setLoginUid(mContext,"");
        }
        user = null;
        cookie = "";
        token = "";
        removeCookie();
    }

    private void removeCookie() {
        CookieSyncManager.createInstance(mContext);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public boolean isLogin(){
        return user!=null&&SettingPrefUtil.getLoginUid(mContext).equals(user.getUid());
    }

    public String getUid(){
        if(!isLogin()){
            return "";
        }
        return user.getUid();
    }

    public String getToken() {
        if (!isLogin()) {
            return token;
        }
        return user.getToken();
    }

    public String getCookie() {
        if (isLogin()) {
            return user.getCookie();
        }
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
