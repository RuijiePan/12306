package ruijie.com.my12306.ui.me;

import android.os.Bundle;
import android.view.View;

import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseLazyLoadFragment;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MeFragment extends BaseLazyLoadFragment {

    public static MeFragment instance;
    public static MeFragment getInstance(){
        if(instance==null)
            return new MeFragment();
        return instance;
    }

    public MeFragment(){

    }

    @Override
    public void initInjector() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {

    }

    @Override
    public void initData() {

    }
}
