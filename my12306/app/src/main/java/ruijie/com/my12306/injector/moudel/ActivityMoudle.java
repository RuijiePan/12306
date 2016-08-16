package ruijie.com.my12306.injector.moudel;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/8/16.
 */
@Module
public class ActivityMoudle {

    private final Activity mActivity;

    public ActivityMoudle(Activity activity){
        this.mActivity = activity;
    }

    @Provides @Singleton
    public Activity proviceActivity(){
        return mActivity;
    }
}
