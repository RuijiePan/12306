package ruijie.com.my12306.ui.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseActivity;
import ruijie.com.my12306.util.SnackbarUtils;

/**
 * Created by Administrator on 2016/8/17.
 */

public class LoginActivity extends BaseActivity implements LoginContact.View {

    @Inject
    LoginPresenter mPresenter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
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
    @Bind(R.id.root)
    LinearLayout root;
    private MaterialDialog dialog;

    @Override
    protected int initContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build()
                .inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        mPresenter.attachView(this);
        initToolBar(toolbar);
        setTitle("登录");
        dialog = new MaterialDialog.Builder(this)
                .title("提示")
                .content("正在登录")
                .progress(true, 0)
                .build();
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @Override
    public void showLoading() {
        if (!isFinishing() && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void dimissLoading() {
        if (!isFinishing() && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void showUserNameError(String error) {
        textInputUserName.setError(error);
        textInputUserName.setErrorEnabled(true);
    }

    @Override
    public void showPasswordError(String error) {
        textInputPassword.setError(error);
        textInputPassword.setErrorEnabled(true);
    }

    @Override
    public void loginSuccess() {
        SnackbarUtils.show(root, "登录成功", 0, null);
    }

    @Override
    public void loginFailure(String msg) {
        SnackbarUtils.show(root, "登录失败", 0, null);
    }

    @OnClick(R.id.btnCommit)
    public void onClick() {
        String mUserName = etUserName.getText().toString().trim();
        String mPassword = etPassWord.getText().toString().trim();
        mPresenter.login(mUserName, mPassword);
    }

    public void initToolBar(Toolbar mToolBar) {
        if (null != mToolBar) {
            setSupportActionBar(mToolBar);
            //getSupportActionBar().setDisplayShowHomeEnabled(false);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

}
