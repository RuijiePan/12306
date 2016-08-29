package ruijie.com.my12306.ui.me;

import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by Administrator on 2016/8/29.
 */

public interface MeContract {

    interface View extends BaseView{

        void showLoginDialog();

        void showCommitDialog();

        void showRegDialogF();

        void showRegDialogS();

        void dimissRegDialogF();

        void dimissRegDialogS();

        void dimissLoginDialog();

        void dimissCommitDialog();

        void registerSuccess();

        void registerFailure(String error);

    }

    interface Presenter extends BasePresenter<View>{

        void login(String username,String password);

        void LoginBtClick();

        void RegisterBtClick();

        void RegisterNextClick();

        void register();
    }
}
