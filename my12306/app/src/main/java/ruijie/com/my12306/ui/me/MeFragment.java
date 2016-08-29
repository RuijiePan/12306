package ruijie.com.my12306.ui.me;

import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.base.BusFragment;
import ruijie.com.my12306.ui.login.LoginActivity;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.ui.register.RegisterActivity;
import ruijie.com.my12306.util.MTextWatcher;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.widget.citySelector.utils.ClearEditText;
import rx.Subscriber;
import rx.observers.Observers;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MeFragment extends BusFragment implements View.OnClickListener,MeContract.View{

    public static MeFragment instance;
    @Inject
    MainActivity mainActivity;
    @Inject
    MePresenter mePresenter;
    @Inject
    Context context;
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
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    View root;
    private MaterialDialog loginDialog;
    private MaterialDialog commitDialog;
    private MaterialDialog regDialogF;
    private MaterialDialog regDialogS;
    private View loginView;
    private View regViewF;
    private View regViewS;
    private ClearEditText et_username;
    private ClearEditText et_password;
    private ClearEditText rg_usernmae;
    private ClearEditText rg_password;
    private ClearEditText rg_spassword;
    private TextInputEditText rgTextInputUserName;
    private TextInputEditText rgTextInputPassWord;
    private TextInputEditText rgTextInputSPassWord;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputUserName;

    public static MeFragment getInstance() {
        if (instance == null) {
            synchronized (MeFragment.class) {
                if (instance == null)
                    instance = new MeFragment();
            }
        }
        return instance;
    }

    public MeFragment() {
    }

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
        ButterKnife.bind(this, view);
        root = view;

        mePresenter.attachView(this);
        mainActivity.setSupportActionBar(toolbar);
        mainActivity.setTitle("我的12306");

        loginView = mainActivity.getLayoutInflater().inflate(R.layout.layout_login,null,false);
        et_username = (ClearEditText) loginView.findViewById(R.id.etUserName);
        et_password = (ClearEditText) loginView.findViewById(R.id.etPassWord);
        textInputUserName = (TextInputLayout) loginView.findViewById(R.id.textInputUserName);
        textInputPassword = (TextInputLayout) loginView.findViewById(R.id.textInputPassword);
        et_username.addTextChangedListener(new LoginextWatcher(textInputUserName));
        et_password.addTextChangedListener(new LoginextWatcher(textInputPassword));

        regViewF = mainActivity.getLayoutInflater().inflate(R.layout.layout_register_f,null,false);
        rg_usernmae = (ClearEditText) regDialogF.findViewById(R.id.rgUserName);
        rg_password = (ClearEditText) regDialogF.findViewById(R.id.rgPassWord);
        rg_spassword = (ClearEditText) regDialogF.findViewById(R.id.rgSPassWord);
        rgTextInputUserName = (TextInputEditText) regDialogF.findViewById(R.id.rgTextInputUserName);
        rgTextInputPassWord = (TextInputEditText) regDialogF.findViewById(R.id.rgTextInputPassword);
        rgTextInputSPassWord = (TextInputEditText) regDialogF.findViewById(R.id.rgTextInputSecondPassword);

        loginDialog = new MaterialDialog.Builder(getContext())
                .title("登陆")
                .customView(loginView,true)
                .positiveText("登陆")
                .neutralText("无法登陆？")
                .neutralColor(getResources().getColor(R.color.base_text_black))
                .onPositive((dialog, which) -> {
                    mePresenter.login(et_username.getText().toString(),et_password.getText().toString());
                })
                .onNeutral((dialog, which) -> {
                    SnackbarUtils.show(root,"点击",0,null);
                })
                .build();

        commitDialog = new MaterialDialog.Builder(getContext())
                .content("正在登录...")
                .progress(true, 0)
                .build();

        regDialogF = new MaterialDialog.Builder(getContext())
                .title("注册")
                .customView(regViewF,true)
                .positiveText("下一步")
                .negativeText("取消")
                .onPositive((dialog, which) -> {

                })
                .onNeutral((dialog, which) -> {
                    regDialogF.dismiss();
                })
                .build();
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

    @OnClick({R.id.bt_login, R.id.bt_register, R.id.bt_guide, R.id.bt_service, R.id.bt_travel, R.id.bt_update, R.id.bt_notification, R.id.bt_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                /*Intent i = new Intent(context, LoginActivity.class);
                startActivity(i);
                mainActivity.overridePendingTransition(0,0);*/
                mePresenter.LoginBtClick();
                break;
            case R.id.bt_register:
                mePresenter.register();
                /*i = new Intent(context, RegisterActivity.class);
                startActivity(i);
                mainActivity.overridePendingTransition(0,0);*/
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

    @Override
    public void showLoginDialog() {
        loginDialog.show();
    }

    @Override
    public void showCommitDialog() {
        commitDialog.show();
    }

    @Override
    public void showRegDialogF() {

    }

    @Override
    public void showRegDialogS() {

    }

    @Override
    public void dimissRegDialogF() {

    }

    @Override
    public void dimissRegDialogS() {

    }

    @Override
    public void dimissLoginDialog() {
        loginDialog.dismiss();
    }

    @Override
    public void dimissCommitDialog() {
        commitDialog.dismiss();
    }

    @Override
    public void registerSuccess() {
        commitDialog.dismiss();
    }

    @Override
    public void registerFailure(String error) {
        textInputPassword.setError(error);
        textInputPassword.setEnabled(true);
    }

    public class LoginextWatcher implements TextWatcher {

        TextInputLayout textInputLayout;

        public LoginextWatcher(TextInputLayout textInputLayout) {
            this.textInputLayout = textInputLayout;
        }

        @Override
        public void afterTextChanged(Editable arg0) {
            if(et_username.getText().toString().length()>=2&&
                    et_password.getText().toString().length()>=6){
                loginDialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
            }else {
                loginDialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            textInputLayout.setErrorEnabled(false);
        }
    }
}

