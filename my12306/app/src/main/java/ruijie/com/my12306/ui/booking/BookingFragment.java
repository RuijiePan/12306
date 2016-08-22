package ruijie.com.my12306.ui.booking;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.internal.Util;
import ruijie.com.my12306.R;
import ruijie.com.my12306.db.dao.User;
import ruijie.com.my12306.entity.AddressItem;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.util.DisplayUtil;
import ruijie.com.my12306.util.LoadMoreRecyclerView;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.util.TextUtil;
import ruijie.com.my12306.util.ToastUtil;
import ruijie.com.my12306.widget.AddressSelectLayout;
import ruijie.com.my12306.widget.FlowLayout;
import ruijie.com.my12306.widget.swipeback.Utils;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BookingFragment extends BaseFragment implements BookingContact.View,View.OnClickListener {

    @Inject
    Context context;
    @Inject
    MainActivity mainActivity;
    @Inject
    BookingPresenter presenter;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private FlowLayout flowlayout;
    private FlowLayout fl_customer;
    private AddressSelectLayout addressLayout;
    private Button bt_start_time;
    private Button bt_start_date;
    private Button bt_seat_type;
    private Button bt_student;
    private Button bt_search;
    private String[] type;
    private String[] customer;
    private View deleteView;
    private boolean[] check;
    private View root;
    private LayoutInflater inflater;
    private View headView;
    private MaterialDialog deleteDialog;
    private MaterialDialog studentDialog;
    private MaterialDialog timeDialog;
    private MaterialDialog seatTypeDialog;
    private MaterialDialog progressDialog;
    private BookingAdapter bookingAdapter;
    private List<AddressItem> list;
    public static BookingFragment instance;

    public static BookingFragment getInstance() {
        if (instance == null) {
            synchronized (BookingFragment.class) {
                if (instance == null)
                    instance = new BookingFragment();
            }
        }
        return instance;
    }

    public BookingFragment() {
    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_booking;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        presenter.attachView(this);
        root = view;

        inflater = mainActivity.getLayoutInflater();
        headView = inflater.inflate(R.layout.layout_booking_head, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //lp.setMargins(0,0,0, DisplayUtil.px2dip(context,100));
        headView.setLayoutParams(lp);

        flowlayout = (FlowLayout) headView.findViewById(R.id.flowlayout);
        fl_customer = (FlowLayout) headView.findViewById(R.id.fl_customer);
        bt_start_date = (Button) headView.findViewById(R.id.bt_start_date);
        bt_start_time = (Button) headView.findViewById(R.id.bt_start_time);
        bt_seat_type = (Button) headView.findViewById(R.id.bt_seat_type);
        bt_student = (Button) headView.findViewById(R.id.bt_student);
        bt_search = (Button) headView.findViewById(R.id.bt_search);

        bt_start_date .setOnClickListener(this);
        bt_start_time.setOnClickListener(this);
        bt_seat_type.setOnClickListener(this);
        bt_student.setOnClickListener(this);
        bt_search.setOnClickListener(this);
        showContent(true);
    }

    @Override
    public void initData() {
        //recyclerview先初始化，将上面布局作为head加入recyclerview
        AddressItem addressItem = new AddressItem("北京","上海");
        list = new ArrayList<>();
        for (int i=0;i<50;i++)
            list.add(addressItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        bookingAdapter = new BookingAdapter(list);
        bookingAdapter.addHeaderView(headView);
        recyclerView.setAdapter(bookingAdapter);

        //seatFlowLayout;
        type = new String[]{"全部", "G/D/C", "Z字头", "T字头", "K字头", "其他"};
        check = new boolean[]{true, false, false, false, false, false};
        for (int i = 0; i < type.length; i++) {
            TextView tv = (TextView) inflater.inflate(R.layout.tv, flowlayout, false);
            tv.setText(type[i]);
            flowlayout.setTag(i);
            flowlayout.addView(tv);
            int finalI = i;
            tv.setOnClickListener(view -> presenter.onSeatClick(check, finalI));
        }
        presenter.onSeatClick(check, 0);

        //customFlowLayout
        TextView tv = (TextView) inflater.inflate(R.layout.tv_add, flowlayout, false);
        tv.setText("⊕乘客");
        fl_customer.setTag(0);
        fl_customer.addView(tv);
        fl_customer.setOnClickListener(view -> presenter.onCustomClick(new ArrayList<>()));

        deleteDialog = new MaterialDialog
                .Builder(getContext())
                .title("温馨提示")
                .content("确定删除该乘客吗?")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive((dialog1, which) -> {
                    fl_customer.removeView(deleteView);
                    dialog1.dismiss();
                })
                .build();

        timeDialog = new MaterialDialog
                .Builder(getContext())
                .title("出发时间")
                .items(R.array.time)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        return true;
                    }
                })
                .positiveText("确定")
                .show();
    }

    @Override
    public void showLoading() {
        showProgress(true);
    }

    @Override
    public void dimissLoading() {
        showProgress(false);
    }

    @Override
    public void getCheck(boolean[] check) {
        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                flowlayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.item_select_check));
                ((TextView) flowlayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.md_white));
            } else {
                flowlayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.item_select_uncheck));
                ((TextView) flowlayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.base_text_black));
            }
        }
    }

    @Override
    public void addUser(ArrayList<User> list) {
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                TextView tv = (TextView) inflater.inflate(R.layout.tv_customer, fl_customer, false);
                tv.setText(list.get(i).getUserName());
                tv.setTag(i);
                fl_customer.addView(tv);
                tv.setOnClickListener(view -> {
                    deleteView = view;
                    deleteDialog.show();
                });
            }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_start_date:
                break;
            case R.id.bt_start_time:
                break;
            case R.id.bt_seat_type:
                break;
            case R.id.bt_student:
                break;
            case R.id.bt_search:
                presenter.search(
                        addressLayout.getTv_from(), addressLayout.getTv_to(),
                        TextUtil.getText(bt_start_date),TextUtil.getText(bt_start_time),
                        TextUtil.getText(bt_seat_type),"",true);
                break;
        }
    }
}
