package ruijie.com.my12306.ui.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MeFragment extends BaseFragment {

    public static MeFragment instance;
    @Inject
    MainActivity mainActivity;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.bt_register)
    Button btRegister;
    @Bind(R.id.bt_guide)
    Button btGuide;
    @Bind(R.id.bt_service)
    Button btService;
    @Bind(R.id.bt_travel)
    Button btTravel;
    @Bind(R.id.bt_update)
    Button btUpdate;
    @Bind(R.id.bt_notification)
    Button btNotification;
    @Bind(R.id.bt_about)
    Button btAbout;
    private View root;

    public static MeFragment getInstance() {
        if (instance == null) {
            synchronized (MeFragment.class) {
                if (instance == null)
                    instance = new MeFragment();
            }
        }
        return instance;
    }

    public MeFragment() {}

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
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
        showContent(true);
        root = view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_login, R.id.bt_register, R.id.bt_guide, R.id.bt_service, R.id.bt_travel, R.id.bt_update, R.id.bt_notification, R.id.bt_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                break;
            case R.id.bt_register:
                break;
            case R.id.bt_guide:
                break;
            case R.id.bt_service:
                break;
            case R.id.bt_travel:
                break;
            case R.id.bt_update:
                break;
            case R.id.bt_notification:
                break;
            case R.id.bt_about:
                break;
        }
    }
}
