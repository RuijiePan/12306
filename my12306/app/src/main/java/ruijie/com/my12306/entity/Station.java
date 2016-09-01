package ruijie.com.my12306.entity;

public class Station {
    private Integer sid;

    private Integer cid;

    private Double premilege;

    private Double preprice;

    private Integer codenumber;

    private String arrivedate;

    private String outbounddate;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Double getPremilege() {
        return premilege;
    }

    public void setPremilege(Double premilege) {
        this.premilege = premilege;
    }

    public Double getPreprice() {
        return preprice;
    }

    public void setPreprice(Double preprice) {
        this.preprice = preprice;
    }

    public Integer getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(Integer codenumber) {
        this.codenumber = codenumber;
    }

    public String getArrivedate() {
        return arrivedate;
    }

    public void setArrivedate(String arrivedate) {
        this.arrivedate = arrivedate == null ? null : arrivedate.trim();
    }

    public String getOutbounddate() {
        return outbounddate;
    }

    public void setOutbounddate(String outbounddate) {
        this.outbounddate = outbounddate == null ? null : outbounddate.trim();
    }
}