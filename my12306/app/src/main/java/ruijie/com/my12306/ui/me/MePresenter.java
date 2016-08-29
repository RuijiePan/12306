package ruijie.com.my12306.ui.me;

import android.support.annotation.NonNull;

import com.blankj.utilcode.utils.StringUtils;

import javax.inject.Inject;

import ruijie.com.my12306.Constant;
import ruijie.com.my12306.api.login.LoginApi;
import ruijie.com.my12306.bean.loginBean;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/8/29.
 */

public class MePresenter implements MeContract.Presenter  {

    private MeContract.View meView;
    private LoginApi loginApi;
    private Subscription mSubscription;

    @Inject
    MePresenter(LoginApi loginApi){
        this.loginApi = loginApi;
    }

    @Override
    public void login(String username, String password) {

        meView.dimissLoginDialog();
        meView.showCommitDialog();
        mSubscription = loginApi.login(username,password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBean -> {
                    if(loginBean.getStatus().equals(Constant.SUCCESS)){
                        meView.registerSuccess();
                    }else{
                        meView.registerFailure(loginBean.getMsg());
                        meView.showLoginDialog();
                    }
                    meView.dimissCommitDialog();
                },throwable ->{
                    throwable.printStackTrace();
                    meView.showLoginDialog();
                    meView.dimissCommitDialog();
                    meView.registerFailure(throwable.toString());
                });
    }

    @Override
    public void LoginBtClick() {
        meView.showLoginDialog();
    }

    @Override
    public void RegisterBtClick() {
        meView.showRegDialogF();
    }

    @Override
    public void RegisterNextClick() {
        meView.showRegDialogS();
    }

    @Override
    public void register() {

    }

    @Override
    public void attachView(@NonNull MeContract.View View) {
        meView = View;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        meView = null;
    }
}
