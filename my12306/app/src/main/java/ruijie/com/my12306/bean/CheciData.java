package ruijie.com.my12306.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/1.
 */
public class CheciData implements Serializable{

    private int cid;
    private String startsite;
    private String endsite;
    private String startdate;
    private String enddate;
    private String type;
    private String codenumber;
    private int startcodenumber;
    private int endcodenumber;
    private String result;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getStartsite() {
        return startsite;
    }

    public void setStartsite(String startsite) {
        this.startsite = startsite;
    }

    public String getEndsite() {
        return endsite;
    }

    public void setEndsite(String endsite) {
        this.endsite = endsite;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(String codenumber) {
        this.codenumber = codenumber;
    }

    public int getStartcodenumber() {
        return startcodenumber;
    }

    public void setStartcodenumber(int startcodenumber) {
        this.startcodenumber = startcodenumber;
    }

    public int getEndcodenumber() {
        return endcodenumber;
    }

    public void setEndcodenumber(int endcodenumber) {
        this.endcodenumber = endcodenumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

