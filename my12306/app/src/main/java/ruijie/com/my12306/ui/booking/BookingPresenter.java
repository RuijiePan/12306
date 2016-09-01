package ruijie.com.my12306.ui.booking;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import ruijie.com.my12306.entity.User;
import ruijie.com.my12306.injector.PerActivity;
import ruijie.com.my12306.ui.base.BaseView;
import rx.Subscription;

/**
 * Created by prj on 2016/8/21.
 */
@PerActivity
public class BookingPresenter implements BookingContact.Presenter{

    private BookingContact.View mBookingView;
    private Subscription mSubscription;

    @Inject
    public BookingPresenter(){

    }

    @Override
    public void search(String from, String to, String startData, String startTime, String seatType, String ticketType, boolean isStudent) {

    }

    @Override
    public void onSeatClick(boolean[] check,int position) {

        if(position==0){
            if(!check[0]){
                for (int i=1;i<check.length;i++){
                    check[i] = false;
                }
                check[0] = true;
            }
        }else{
            check[position] = !check[position];
            if(check[0]){
                check[0] = false;
            }
            if (!check[1] && !check[2] && !check[3] && !check[4] && !check[5]) {
                check[0] = true;
            }
        }
        mBookingView.getCheck(check);
    }

    @Override
    public void onCustomClick(ArrayList<User> list) {
        User user = new User("1","1","1","1","1","1","1","1","1",0 ,"1");
        list.add(user);
        list.add(user);
        mBookingView.addUser(list);
    }

    @Override
    public void attachView(@NonNull BookingContact.View View) {
        mBookingView = View;
    }

    @Override
    public void detachView() {
        if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
        mBookingView = null;
    }
}
