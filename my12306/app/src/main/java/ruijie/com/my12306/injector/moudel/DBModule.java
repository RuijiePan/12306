package ruijie.com.my12306.injector.moudel;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ruijie.com.my12306.db.entity.DaoMaster;
import ruijie.com.my12306.db.entity.DaoSession;
import ruijie.com.my12306.db.entity.UserDao;

/**
 * Created by Administrator on 2016/8/16.
 */

@Module
public class DBModule {

    @Provides @Singleton
    DaoMaster.DevOpenHelper provideDevHelper(Context context){
        return new DaoMaster.DevOpenHelper(context,"app.db",null);
    }

    @Provides @Singleton
    DaoMaster proviceDaoMaster(DaoMaster.DevOpenHelper helper){
        return new DaoMaster(helper.getWritableDatabase());
    }

    @Provides @Singleton
    DaoSession proviceDaoSession(DaoMaster master){
        return master.newSession();
    }

    @Provides @Singleton
    UserDao getUserDao(DaoSession daoSession){
        return daoSession.getUserDao();
    }

}
