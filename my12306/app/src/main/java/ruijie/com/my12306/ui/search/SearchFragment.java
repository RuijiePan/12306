package ruijie.com.my12306.ui.search;

import android.os.Bundle;
import android.view.View;

import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseLazyLoadFragment;

/**
 * Created by Administrator on 2016/8/18.
 */

public class SearchFragment extends BaseLazyLoadFragment{

    public static SearchFragment instance;
    public static SearchFragment getInstance(){
        if(instance==null)
            return new SearchFragment();
        return instance;
    }

    public SearchFragment(){

    }

    @Override
    public void initInjector() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_search;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        showContent(true);
    }

    @Override
    public void initData() {

    }
}
