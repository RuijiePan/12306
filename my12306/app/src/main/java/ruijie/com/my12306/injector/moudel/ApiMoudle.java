package ruijie.com.my12306.injector.moudel;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import ruijie.com.my12306.api.User.UserApi;
import ruijie.com.my12306.components.retrofit.RequestHelper;

/**
 * Created by Administrator on 2016/8/16.
 */

@Module
public class ApiMoudle {

    @Provides @Singleton
    public UserApi proviceUserApi(RequestHelper requestHelper, @Named("api") OkHttpClient okHttpClient){
        return new UserApi(requestHelper,okHttpClient);
    }
}


