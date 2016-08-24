package ruijie.com.my12306.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.ui.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/18.
 */

public class FinishFragment extends BaseFragment {

    public static FinishFragment instance;
    View root;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.bt_order_today)
    Button btOrderToday;
    @Bind(R.id.bt_order_future)
    Button btOrderFuture;
    @Bind(R.id.bt_oreder_history)
    Button btOrederHistory;

    public static FinishFragment getInstance() {
        if (instance == null) {
            synchronized (FinishFragment.class) {
                if (instance == null)
                    instance = new FinishFragment();
            }
        }
        return instance;
    }

    public FinishFragment() {

    }

    @Override
    public void initInjector() {

    }

    @Override
    public int initContentView() {
        return R.layout.fragment_finish;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;

        showContent(true);
    }

    @Override
    public void initData() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  EEEE");
        Date curDate = new Date(System.currentTimeMillis());
        String str = format.format(curDate);
        tvDate.setText(str);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_date, R.id.bt_order_today, R.id.bt_order_future, R.id.bt_oreder_history})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                break;
            case R.id.bt_order_today:
                break;
            case R.id.bt_order_future:
                break;
            case R.id.bt_oreder_history:
                break;
        }
    }
}
