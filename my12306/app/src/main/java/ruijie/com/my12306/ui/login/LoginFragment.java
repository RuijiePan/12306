package ruijie.com.my12306.ui.login;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.util.SnackbarUtils;

/**
 * Created by prj on 2016/8/18.
 */

public class LoginFragment extends BaseFragment implements LoginContact.View {

    @Inject
    LoginPresenter mPresenter;
    @Inject
    MainActivity mainActivity;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    @Bind(R.id.etUserName)
    EditText etUserName;
    @Bind(R.id.textInputUserName)
    TextInputLayout textInputUserName;
    @Bind(R.id.etPassWord)
    EditText etPassWord;
    @Bind(R.id.textInputPassword)
    TextInputLayout textInputPassword;
    @Bind(R.id.btnCommit)
    Button btnCommit;
    LinearLayout ll;
    public static LoginFragment instance;

    public static LoginFragment getInstance() {
        if (instance == null) {
            synchronized (LoginFragment.class) {
                if (instance == null)
                    instance = new LoginFragment();
            }
        }
        return instance;
    }

    public LoginFragment() {

    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        ll = (LinearLayout) view.findViewById(R.id.ll);
        showContent(true);
        mPresenter.attachView(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void showLoading() {
        showProgress(true);
    }

    @Override
    public void dimissLoading() {
        showProgress(false);
    }

    @Override
    public void showUserNameError(String error) {
        SnackbarUtils.show(ll, "用户名错误", 0, null);
    }

    @Override
    public void showPasswordError(String error) {
        SnackbarUtils.show(ll, "密码错误", 0, null);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailure(String msg) {
        SnackbarUtils.show(ll, msg, 0, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        ButterKnife.unbind(this);
    }
}
