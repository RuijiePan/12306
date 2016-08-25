package ruijie.com.my12306.ui.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.anupcowkur.reservoir.Reservoir;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import ruijie.com.my12306.Constant;
import ruijie.com.my12306.R;
import ruijie.com.my12306.api.login.LoginApi;
import ruijie.com.my12306.bean.loginBean;
import ruijie.com.my12306.bean.userBean;
import ruijie.com.my12306.db.dao.User;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.util.ReservoirUtil;
import ruijie.com.my12306.util.ReservoirUtils;
import ruijie.com.my12306.util.RxUtil;
import ruijie.com.my12306.util.TextUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/8/17.
 */

@PerActivity
public class LoginPresenter implements LoginContact.Presenter{

    private LoginContact.View mLoginView;
    private Subscription mSubscription;
    private LoginApi mLoginApi;

    @Inject
    public LoginPresenter(LoginApi loginApi){
        mLoginApi = loginApi;
    }

    @Override
    public void login(String username, String password) {
        if(TextUtil.isEmpty(username)){
            mLoginView.showUserNameError("请输入用户名");
            return;
        }
        if(TextUtil.isEmpty(password)){
            mLoginView.showPasswordError("请输入密码");
            return;
        }
        mLoginView.showLoading();
        mSubscription = mLoginApi.login(username,password)
                .map(loginBean -> {
                    if(loginBean.getStatus().equals(Constant.SUCCESS))
                    return loginBean.getUserBean();
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userBean -> {
                    if (userBean != null) {
                        mLoginView.dimissLoading();
                        mLoginView.loginSuccess();
                    }else {
                        mLoginView.dimissLoading();
                        mLoginView.loginFailure("登录失败");
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    mLoginView.dimissLoading();
                    mLoginView.loginFailure(throwable+"");
                });

    }

    @Override
    public void attachView(@NonNull LoginContact.View View) {
        mLoginView = View;
    }

    @Override
    public void detachView() {
        if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mLoginView = null;
    }
}
