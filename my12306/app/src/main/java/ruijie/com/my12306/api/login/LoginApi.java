package ruijie.com.my12306.api.login;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import ruijie.com.my12306.bean.loginBean;
import ruijie.com.my12306.components.retrofit.FastJsonConverterFactory;
import ruijie.com.my12306.components.retrofit.RequestHelper;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/16.
 */

public class LoginApi {

    static final String BASE_URL = "http://172.21.118.14:3306/Server12306/";

    private LoginService loginService;
    private RequestHelper requestHelper;

    public LoginApi(RequestHelper requestHelper, OkHttpClient okHttpClient){
        this.requestHelper = requestHelper;
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        loginService = retrofit.create(LoginService.class);
    }

    public Observable<loginBean> login(String username,String password){
        HashMap<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        String sign = requestHelper.getRequestSign(params);
        params.put("sign",sign);
        return loginService.login(username,password).subscribeOn(Schedulers.io());
    }
}
