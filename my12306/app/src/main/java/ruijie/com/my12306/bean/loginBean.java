package ruijie.com.my12306.bean;

/**
 * Created by Administrator on 2016/8/16.
 */

public class loginBean {


    /**
     * errortype :
     * status : success
     * data : {"uid":1,"identity":"学生","username":"BeJson","phone":"15622625081","nickname":"wuyiming","email":"270949894@qq.com","idcard":"44098120000000000","password":"123456"}
     * msg : value
     */

    private String errortype;
    private String status;
    /**
     * uid : 1
     * identity : 学生
     * username : BeJson
     * phone : 15622625081
     * nickname : wuyiming
     * email : 270949894@qq.com
     * idcard : 44098120000000000
     * password : 123456
     */

    private User data;
    private String msg;

    public String getErrortype() {
        return errortype;
    }

    public void setErrortype(String errortype) {
        this.errortype = errortype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class User {
        private int uid;
        private String identity;
        private String username;
        private String phone;
        private String nickname;
        private String email;
        private String idcard;
        private String password;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
