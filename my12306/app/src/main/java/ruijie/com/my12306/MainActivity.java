package ruijie.com.my12306;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ruijie.com.my12306.ui.base.BaseActivity;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    }

    @Override
    public void initUiAndListener() {

    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }
}
