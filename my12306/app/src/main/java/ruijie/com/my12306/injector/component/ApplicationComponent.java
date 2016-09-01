package ruijie.com.my12306.injector.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ruijie.com.my12306.MyApplication;
import ruijie.com.my12306.api.User.UserApi;
import ruijie.com.my12306.components.okhttp.OkHttpHelper;
import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.injector.moudel.ApiMoudle;
import ruijie.com.my12306.injector.moudel.ApplicationMoudle;
import ruijie.com.my12306.ui.base.BaseActivity;

/**
 * Created by prj on 2016/8/15.
 */
@Singleton @Component(modules = {ApplicationMoudle.class, ApiMoudle.class})
public interface ApplicationComponent {

    Context getContext();

    OkHttpHelper getOkHttpHelper();

    UserStorage getUserStorage();

    UserApi getUserApi();

    void inject(MyApplication application);

    void inject(BaseActivity mBaseActivity);

}
