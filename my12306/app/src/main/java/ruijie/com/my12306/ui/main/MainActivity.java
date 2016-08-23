package ruijie.com.my12306.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import ruijie.com.my12306.R;
import ruijie.com.my12306.event.toolbarEvent;
import ruijie.com.my12306.injector.HasComponent;
import ruijie.com.my12306.ui.base.BusActivity;
import ruijie.com.my12306.ui.booking.BookingFragment;
import ruijie.com.my12306.ui.login.LoginFragment;
import ruijie.com.my12306.ui.me.MeFragment;
import ruijie.com.my12306.ui.search.SearchFragment;
import ruijie.com.my12306.util.RxBus;

public class MainActivity extends BusActivity implements MainContract.View, HasComponent {

    @Bind(R.id.BottomNavigation)
    it.sephiroth.android.library.bottomnavigation.BottomNavigation BottomNavigation;
    @Inject
    MainPresenter mainPresenter;
    @Bind(R.id.CoordinatorLayout)
    android.support.design.widget.CoordinatorLayout CoordinatorLayout;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    @Bind(R.id.content)
    LinearLayout content;
    /*@Bind(R.id.viewPager)
    NoScollerViewPager viewPager;*/
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
        /*ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(BookingFragment.getInstance());
        fragmentList.add(SearchFragment.getInstance());
        fragmentList.add(MeFragment.getInstance());
        BaseFragmentPagerAdapter baseFragmentPagerAdapter;
        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(baseFragmentPagerAdapter);*/
        initFragment();
        setSupportActionBar(toolbar);

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
        return true;
    }

    @Override
    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (fragment != oldFragment) {
            ft.hide(oldFragment).show(fragment).commit();
            oldFragment = fragment;
        }
    }

    @Override
    public void showTitle(String title,boolean isShowBack) {
        if (isShowBack){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        toolbar.setTitle(title);
    }

    public void initFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, BookingFragment.getInstance())
                .add(R.id.content, SearchFragment.getInstance())
                .add(R.id.content, MeFragment.getInstance())
                .add(R.id.content, LoginFragment.getInstance())
                .hide(SearchFragment.getInstance())
                .hide(MeFragment.getInstance())
                .hide(LoginFragment.getInstance())
                .commit();
        oldFragment = BookingFragment.getInstance();
    }

    @Override
    public Object getComponent() {
        return mainComponent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            BottomNavigation.setSelectedIndex(MainPresenter.ME,false);
            mainPresenter.onBottomNavClick(MainPresenter.ME);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public android.support.design.widget.AppBarLayout getAppBarLayout(){
        return AppBarLayout;
    }

}