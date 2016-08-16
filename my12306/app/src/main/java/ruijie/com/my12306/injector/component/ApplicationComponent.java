package ruijie.com.my12306.injector.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ruijie.com.my12306.components.okhttp.OkHttpHelper;
import ruijie.com.my12306.components.storage.UserStorage;
import ruijie.com.my12306.db.entity.UserDao;
import ruijie.com.my12306.injector.moudel.ApiMoudle;
import ruijie.com.my12306.injector.moudel.ApplicationMoudle;
import ruijie.com.my12306.injector.moudel.DBModule;
import ruijie.com.my12306.ui.base.BaseActivity;

/**
 * Created by prj on 2016/8/15.
 */
@Singleton @Component(modules = {ApplicationMoudle.class, ApiMoudle.class, DBModule.class})
public interface ApplicationComponent {

    Context getContext();

    UserDao getUserDao();

    OkHttpHelper getOkHttpHelper();

    UserStorage getUserStorage();

    void inject(Application application);

    void inject(BaseActivity mBaseActivity);
}
