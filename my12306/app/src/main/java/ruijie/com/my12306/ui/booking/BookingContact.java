package ruijie.com.my12306.ui.booking;

import java.util.ArrayList;

import ruijie.com.my12306.db.dao.User;
import ruijie.com.my12306.ui.base.BasePresenter;
import ruijie.com.my12306.ui.base.BaseView;

/**
 * Created by prj on 2016/8/21.
 */

public interface BookingContact {

    interface View extends BaseView{
        void showLoading();

        void dimissLoading();

        void getCheck(boolean[] check);

        void addUser(ArrayList<User> list);

    }

    interface Presenter extends BasePresenter<View>{

        void search(String from,String to,String startData,String startTime,String seatType,
            String ticketType,boolean isStudent);

        void onSeatClick(boolean[] check,int position);

        void onCustomClick(ArrayList<User> list);

    }
}
