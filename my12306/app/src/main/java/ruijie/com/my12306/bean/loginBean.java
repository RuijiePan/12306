package ruijie.com.my12306.bean;

import ruijie.com.my12306.util.StringUtil;

/**
 * Created by Administrator on 2016/8/16.
 */

public class loginBean {

    private String status;
    private String msg;
    private userBean userBean;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ruijie.com.my12306.bean.userBean getUserBean() {
        return userBean;
    }

    public void setUserBean(ruijie.com.my12306.bean.userBean userBean) {
        this.userBean = userBean;
    }
}
