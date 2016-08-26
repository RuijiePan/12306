package ruijie.com.my12306.injector.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ruijie.com.my12306.MyApplication;
import ruijie.com.my12306.api.login.LoginApi;
import ruijie.com.my12306.components.okhttp.OkHttpHelper;
import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.db.entity.UserDao;
import ruijie.com.my12306.injector.moudel.ApiMoudle;
import ruijie.com.my12306.injector.moudel.ApplicationMoudle;
import ruijie.com.my12306.injector.moudel.DBModule;
import ruijie.com.my12306.ui.base.BaseActivity;
import ruijie.com.my12306.ui.booking.BookingFragment;
import ruijie.com.my12306.ui.login.LoginActivity;
import ruijie.com.my12306.ui.login.LoginFragment;
import ruijie.com.my12306.ui.me.MeFragment;
import ruijie.com.my12306.ui.register.RegisterActivity;
import ruijie.com.my12306.ui.search.SearchFragment;

/**
 * Created by prj on 2016/8/15.
 */
@Singleton @Component(modules = {ApplicationMoudle.class, ApiMoudle.class, DBModule.class})
public interface ApplicationComponent {

    Context getContext();

    UserDao getUserDao();

    OkHttpHelper getOkHttpHelper();

    UserStorage getUserStorage();

    LoginApi getLoginApi();

    void inject(MyApplication application);

    void inject(BaseActivity mBaseActivity);

}
