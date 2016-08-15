package ruijie.com.my12306.db;

/**
 * Created by prj on 2016/8/15.
 */

public class User {

    private long id;
    private String userName;
    private String passWord;
    private String email;
    private String number;
    private Integer sex;
    private String cookie;
    private String token;
    private String registerTime;
    private String location;
    private String nickNameUrl;
    private String uid;

    public User(long id, String userName, String passWord, String email, String number, Integer sex,
                String cookie, String token, String registerTime, String location, String nickNameUrl,
                String uid) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.number = number;
        this.sex = sex;
        this.cookie = cookie;
        this.token = token;
        this.registerTime = registerTime;
        this.location = location;
        this.nickNameUrl = nickNameUrl;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNickNameUrl() {
        return nickNameUrl;
    }

    public void setNickNameUrl(String nickNameUrl) {
        this.nickNameUrl = nickNameUrl;
    }

}
