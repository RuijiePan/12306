package ruijie.com.my12306.ui.main;

import dagger.Module;
import dagger.Provides;
import ruijie.com.my12306.injector.PerActivity;

/**
 * Created by Administrator on 2016/8/18.
 */
@Module
public class MainMoudle {

    MainActivity mainActivity;
    public MainMoudle(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

}
