package ruijie.com.my12306.api.User;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ruijie.com.my12306.bean.loginBean;
import ruijie.com.my12306.entity.User;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/1.
 */

public interface UserService {

    @POST("register")
    Observable<loginBean> register(@Body User user);


}
