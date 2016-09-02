package ruijie.com.my12306.ui.booking.searchActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.event.CheciAllInfo;
import ruijie.com.my12306.ui.base.BaseSwipeBackActivity;
import ruijie.com.my12306.util.LoadMoreRecyclerView;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.util.ToastUtil;

public class BookingSearchActivity extends BaseSwipeBackActivity implements BookingSearchContract.View {

    @Inject
    Context context;
    @Inject
    BookingSeatchPresenter mPresenter;
    BookingSearchComponent bookingSearchComponent;
    BookingSearchAdapter adapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.AppBarLayout)
    android.support.design.widget.AppBarLayout AppBarLayout;
    @Bind(R.id.textview_preday)
    TextView textviewPreday;
    @Bind(R.id.textview_afterday)
    TextView textviewAfterday;
    @Bind(R.id.root)
    RelativeLayout root;
    @Bind(R.id.recyclerView)
    LoadMoreRecyclerView recyclerView;
    private MaterialDialog progressDialog;
    private ArrayList<CheciData> list;
    private ArrayList<CheciAllInfo> checiAllInfoList;

    @Override
    protected int initContentView() {
        return R.layout.activity_bookingserch;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    public void initInjector() {
        bookingSearchComponent = DaggerBookingSearchComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityMoudle(getActivityMoudle())
                .build();
        bookingSearchComponent.inject(this);
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);

        mPresenter.attachView(this);
        list = (ArrayList<CheciData>) getIntent().getSerializableExtra("list");
        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        initToolBar(toolbar,from+"<>"+to);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        for (int i=0;i<list.size();i++){
            checiAllInfoList.get(i).setCheciData(list.get(i));
        }
        adapter = new BookingSearchAdapter(checiAllInfoList,context);
        recyclerView.setAdapter(adapter);

        adapter.setOnRecyclerViewItemClickListener((view, i) -> {
            switch (view.getId()){
                case R.id.imageview_switch:
                mPresenter.getPassStation(list.get(i).getCid(),i);
            }
        });

        progressDialog  = new MaterialDialog
                .Builder(this)
                .title("提示")
                .content("正在查询，请稍后...")
                .progress(true, 0)
                .build();
    }

    @Override
    protected boolean isApplyStatusBarColor() {
        return true;
    }

    @OnClick({R.id.textview_preday, R.id.textview_afterday, R.id.recyclerView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textview_preday:
                break;
            case R.id.textview_afterday:
                break;
            case R.id.recyclerView:
                break;
        }
    }

    @Override
    public void showLoading() {
        if (!isFinishing() && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (!isFinishing() && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showPassStation(List<Station> list,int position) {
        adapter.getItem(position).setStation(list);
        adapter.notifyDataSetChanged();
        SnackbarUtils.show(recyclerView,list.toString(),0,null);
    }

    @Override
    public void showError(String Error) {
        SnackbarUtils.show(recyclerView,Error,0,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
