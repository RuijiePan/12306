package ruijie.com.my12306.ui.main;

import javax.inject.Singleton;

import dagger.Component;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.injector.component.ApplicationComponent;

/**
 * Created by Administrator on 2016/8/18.
 */
@PerActivity @Component(dependencies = ApplicationComponent.class,modules = MainMoudle.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}

