package ruijie.com.my12306.ui.main;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.MyFragment;
import ruijie.com.my12306.ui.base.BaseActivity;
import ruijie.com.my12306.ui.booking.BookingFragment;
import ruijie.com.my12306.ui.me.MeFragment;
import ruijie.com.my12306.ui.search.SearchFragment;
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
    private Fragment oldFragment;

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
        initFragment();
        //transFragmentTo(BookingFragment.getInstance());

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
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return false;
    }

    @Override
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(fragment!=oldFragment) {
            ft.hide(oldFragment).show(fragment).commit();
            oldFragment = fragment;
        }
    }

    public void initFragment(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content,BookingFragment.getInstance())
                .add(R.id.content,SearchFragment.getInstance())
                .add(R.id.content,MeFragment.getInstance())
                .hide(SearchFragment.getInstance())
                .hide(MeFragment.getInstance())
                .commit();
        oldFragment = BookingFragment.getInstance();
    }
}
