package ruijie.com.my12306.ui.booking;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ruijie.com.my12306.R;
import ruijie.com.my12306.db.dao.User;
import ruijie.com.my12306.entity.AddressItem;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.widget.FlowLayout;

/**
 * Created by Administrator on 2016/8/18.
 */
public class BookingFragment extends BaseFragment implements BookingContact.View{

    @Inject
    Context context;
    @Inject
    MainActivity mainActivity;
    @Inject
    BookingPresenter presenter;
    @Bind(R.id.flowlayout)
    FlowLayout flowlayout;
    @Bind(R.id.fl_customer)
    FlowLayout fl_customer;
    private String[] type;
    private String[] customer;
    private View deleteView;
    private boolean[] check;
    private View root;
    private LayoutInflater inflater;
    private MaterialDialog dialog;
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
        /*LoadMoreRecyclerView loadMoreRecyclerView = (LoadMoreRecyclerView) view.findViewById(recyclerView);
        AddressItem addressItem = new AddressItem("北京","上海");
        list = new ArrayList<>();
        for (int i=0;i<2;i++)
        list.add(addressItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        loadMoreRecyclerView.setLayoutManager(layoutManager);
        bookingAdapter = new BookingAdapter(list);
        loadMoreRecyclerView.setAdapter(bookingAdapter);*/
        showContent(true);
    }

    @Override
    public void initData() {

        //seatFlowLayout;
        inflater = mainActivity.getLayoutInflater();
        type = new String[]{"全部","G/D/C","Z字头","T字头","K字头","其他"};
        check = new boolean[]{true,false,false,false,false,false};
        for (int i=0;i<type.length;i++){
            TextView tv = (TextView) inflater.inflate(R.layout.tv,flowlayout,false);
            tv.setText(type[i]);
            flowlayout.setTag(i);
            flowlayout.addView(tv);
            int finalI = i;
            tv.setOnClickListener(view -> presenter.onSeatClick(check,finalI));
        }
        presenter.onSeatClick(check,0);

        //customFlowLayout
        TextView tv = (TextView) inflater.inflate(R.layout.tv_add,flowlayout,false);
        tv.setText("⊕乘客");
        fl_customer.setTag(0);
        fl_customer.addView(tv);
        fl_customer.setOnClickListener(view -> presenter.onCustomClick(new ArrayList<>()));

        dialog = new MaterialDialog
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
        for (int i=0;i<check.length;i++){
            if(check[i])
            {
                flowlayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.item_select_check));
                ((TextView)flowlayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.md_white));
            }else {
                flowlayout.getChildAt(i).setBackground(getResources().getDrawable(R.drawable.item_select_uncheck));
                ((TextView)flowlayout.getChildAt(i)).setTextColor(getResources().getColor(R.color.base_text_black));
            }
        }
    }

    @Override
    public void addUser(ArrayList<User> list) {
        if(list!=null)
        for (int i=0;i<list.size();i++){
            TextView tv = (TextView) inflater.inflate(R.layout.tv_customer,fl_customer,false);
            tv.setText(list.get(i).getUserName());
            tv.setTag(i);
            fl_customer.addView(tv);
            tv.setOnClickListener(view -> {
                deleteView = view;
                dialog.show();
            });
        }
    }
}
