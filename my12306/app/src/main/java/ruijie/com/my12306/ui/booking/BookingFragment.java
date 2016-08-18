package ruijie.com.my12306.ui.booking;

import android.os.Bundle;
import android.view.View;

import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.base.BaseLazyLoadFragment;

/**
 * Created by Administrator on 2016/8/18.
 */

public class BookingFragment extends BaseFragment {

    public static BookingFragment instance;
    public static BookingFragment getInstance(){
        if(instance==null) {
            {
                synchronized (BookingFragment.class){
                    if (instance==null)
                        instance = new BookingFragment();
                }
            }
        }
        return instance;
    }

    public BookingFragment(){
    }

    @Override
    public void initInjector() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_booking;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        //showProgress(true);
        showContent(true);
    }

    @Override
    public void initData() {

    }
}
