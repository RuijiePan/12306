package ruijie.com.my12306.ui.main;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import ruijie.com.my12306.ui.booking.BookingFragment;
import ruijie.com.my12306.ui.me.MeFragment;
import ruijie.com.my12306.ui.search.SearchFragment;
import rx.Subscription;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MainPresenter implements MainContract.Presenter {

    public static final int BOOKING = 0;
    public static final int SEARCH = 1;
    public static final int ME = 2;
    private MainContract.View mMainView;
    private Context context;

    @Inject
    public MainPresenter(Context context){
        this.context = context;
    }

    @Override
    public void onBottomNavClick(int position) {
        Fragment mFragment = null;
        //Log.w("haha",position+"");
        switch (position){
            case BOOKING:
                mFragment = BookingFragment.getInstance();
                break;
            case SEARCH:
                mFragment = SearchFragment.getInstance();
                break;
            case ME:
                mFragment = MeFragment.getInstance();
                break;
        }
        if(mFragment!=null){
            mMainView.showFragment(mFragment);
        }
    }

    @Override
    public void attachView(@NonNull MainContract.View View) {
        mMainView = View;
    }

    @Override
    public void detachView() {
        /*if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }*/
        mMainView = null;
    }
}
