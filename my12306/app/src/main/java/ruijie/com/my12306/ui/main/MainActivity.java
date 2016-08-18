package ruijie.com.my12306.ui.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.MyFragment;
import ruijie.com.my12306.ui.base.BaseActivity;
import ruijie.com.my12306.ui.booking.BookingFragment;
import ruijie.com.my12306.util.SnackbarUtils;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Bind(R.id.content)
    FrameLayout content;
    @Bind(R.id.BottomNavigation)
    it.sephiroth.android.library.bottomnavigation.BottomNavigation BottomNavigation;
    @Inject
    MainPresenter mainPresenter;
    @Bind(R.id.CoordinatorLayout)
    android.support.design.widget.CoordinatorLayout CoordinatorLayout;
    private MainComponent mainComponent;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainMoudle(new MainMoudle(this))
                .build();
        mainComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        //getFragmentManager().beginTransaction().replace(R.id.content,new MyFragment()).commit();
        BottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(@IdRes int itemId, int position) {
                mainPresenter.onBottomNavClick(position);
            }

            @Override
            public void onMenuItemReselect(@IdRes int itemId, int position) {

            }
        });
        mainPresenter.attachView(this);
        mainPresenter.onBottomNavClick(0);
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    public void showFragment(Fragment fragment) {
        //SnackbarUtils.show(CoordinatorLayout,"jiajia",0,null);
        getFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }

}
