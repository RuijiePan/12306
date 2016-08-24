package ruijie.com.my12306.ui.search;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/18.
 */

public class FinishFragment extends BaseFragment {

    public static FinishFragment instance;
    View root;

    public static FinishFragment getInstance() {
        if (instance == null) {
            synchronized (FinishFragment.class) {
                if (instance == null)
                    instance = new FinishFragment();
            }
        }
        return instance;
    }

    public FinishFragment() {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_finish;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;

        showContent(true);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
