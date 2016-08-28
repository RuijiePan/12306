package ruijie.com.my12306.ui.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
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
import ruijie.com.my12306.ui.login.LoginActivity;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.ui.register.RegisterActivity;
import ruijie.com.my12306.util.MTextWatcher;
import ruijie.com.my12306.widget.citySelector.utils.ClearEditText;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    public static MeFragment instance;
    @Inject
    MainActivity mainActivity;
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
    private View loginView;
    private ClearEditText et_username;
    private ClearEditText et_password;
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

        mainActivity.setSupportActionBar(toolbar);
        mainActivity.setTitle("我的12306");

        loginView = mainActivity.getLayoutInflater().inflate(R.layout.layout_login,null,false);
        et_username = (ClearEditText) loginView.findViewById(R.id.etUserName);
        et_password = (ClearEditText) loginView.findViewById(R.id.etPassWord);
        textInputUserName = (TextInputLayout) loginView.findViewById(R.id.textInputUserName);
        textInputPassword = (TextInputLayout) loginView.findViewById(R.id.textInputPassword);
        et_username.addTextChangedListener(new MTextWatcher(textInputUserName));
        et_password.addTextChangedListener(new MTextWatcher(textInputPassword));

        loginDialog = new MaterialDialog.Builder(getContext())
                .title("登陆")
                .customView(loginView,true)
                .positiveText("登陆")
                .neutralText("无法登陆？")
                .neutralColor(getResources().getColor(R.color.base_text_black))
                /*.inputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PERSON_NAME |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .alwaysCallInputCallback()
                .input(R.string.about, 0, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                    }
                })*/
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
                loginDialog.show();
                break;
            case R.id.bt_register:
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

}

