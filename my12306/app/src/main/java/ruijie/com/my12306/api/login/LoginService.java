package ruijie.com.my12306.api.login;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ruijie.com.my12306.bean.loginBean;
import rx.Observable;

/**
 * Created by Administrator on 2016/8/16.
 */

public interface LoginService {

    @FormUrlEncoded @POST("login/LoginServlet")
    Observable<loginBean> login(@Field("username")String username,@Field("password")String password);
}
