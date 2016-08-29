package ruijie.com.my12306.ui.me;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.blankj.utilcode.utils.RegularUtils;
import com.blankj.utilcode.utils.StringUtils;

import javax.inject.Inject;

import ruijie.com.my12306.Constant;
import ruijie.com.my12306.api.login.LoginApi;
import ruijie.com.my12306.bean.loginBean;
import ruijie.com.my12306.util.TextUtil;
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
        meView.dimissRegDialogF();
    }

    @Override
    public void register() {
        meView.showCommitDialog();
    }

    @Override
    public boolean getCheckRegF(EditText username, EditText password, EditText spassword) {
        String name = TextUtil.getText(username);
        String pwd = TextUtil.getText(password);
        String spwd = TextUtil.getText(spassword);
        if(name.equals("")){
            meView.showRegNameError("用户名不能为空");
            return false;
        }else if(pwd.equals("")){
            meView.showRegPwdError("密码不能为空");
            return false;
        }else if(pwd.equals("")){
            meView.showRegSPwdError("确认密码不能为空");
            return false;
        }else if(!pwd.equals(spwd)){
            meView.showRegPwdError("请确认两次密码是否相同");
            return false;
        }else if (name.length()<2){
            meView.showRegNameError("用户名不能小于两位数");
            return false;
        }else if (password.length()<6){
            meView.showRegPwdError("密码不能小于6位数");
            return false;
        }
        return true;
    }

    @Override
    public boolean getCheckRegS(EditText nickname, EditText idcard, EditText email, EditText identity,EditText phone) {
        String tv_nickname = TextUtil.getText(nickname);
        String tv_idcard = TextUtil.getText(idcard);
        String tv_email = TextUtil.getText(email);
        String tv_identity = TextUtil.getText(identity);
        String tv_phone = TextUtil.getText(phone);
        if(tv_nickname.equals("")){
            meView.showNickNameError("昵称不能小于两位数");
            return false;
        }
        if(tv_idcard.equals("")){
            meView.showIdCardError("身份证不能为空");
            return false;
        }
        if(tv_email.equals("")){
            meView.showEmailError("邮箱不能为空");
            return false;
        }
        if(tv_identity.equals("")){
            meView.showIdentityError("身份类型不能为空");
            return false;
        }
        if(tv_phone.equals("")) {
            meView.showPhoneError("联系方式不能为空");
            return false;
        }
        if (!RegularUtils.isIDCard15(tv_idcard)&&!RegularUtils.isIDCard18(tv_idcard)){
            meView.showIdCardError("请输入真实的身份证号码");
            return false;
        }
        if(!RegularUtils.isMobileExact(tv_phone)){
            meView.showPhoneError("请输入真实的手机号码");
            return false;
        }
        if(!RegularUtils.isEmail(tv_email)){
            meView.showEmailError("请输入正确的邮箱");
        }
        return true;
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
