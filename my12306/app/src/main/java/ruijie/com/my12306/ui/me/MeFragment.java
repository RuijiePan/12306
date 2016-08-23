package ruijie.com.my12306.ui.me;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ruijie.com.my12306.R;
import ruijie.com.my12306.entity.AddressItem;
import ruijie.com.my12306.ui.base.BaseFragment;
import ruijie.com.my12306.ui.main.MainActivity;
import ruijie.com.my12306.ui.main.MainComponent;
import ruijie.com.my12306.util.SnackbarUtils;
import ruijie.com.my12306.widget.emptyAdapter;

/**
 * Created by Administrator on 2016/8/18.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener{

    public static MeFragment instance;
    @Inject
    MainActivity mainActivity;
    @Inject
    Context context;
    @Bind(R.id.rv)
    RecyclerView recyclerView;
    private View root;
    private viewHolder holder;
    private BaseQuickAdapter adapter;

    public static MeFragment getInstance() {
        if (instance == null) {
            synchronized (MeFragment.class) {
                if (instance == null)
                    instance = new MeFragment();
            }
        }
        return instance;
    }

    public MeFragment() {
    }

    @Override
    public void initInjector() {
        getComponent(MainComponent.class).inject(this);
    }

    @Override
    public int initContentView() {
        return R.layout.fragment_me;
    }

    @Override
    public void getBundle(Bundle bundle) {

    }

    @Override
    public void initUI(View view) {
        ButterKnife.bind(this, view);
        root = view;
        View headView = mainActivity.getLayoutInflater().inflate(R.layout.fragment_me_content, null);
        holder = new viewHolder(headView);
        showContent(true);
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new emptyAdapter(new ArrayList<>());
        adapter.addHeaderView(holder.itemView);
        recyclerView.setAdapter(adapter);

        holder.btLogin.setOnClickListener(this);
        holder.btRegister.setOnClickListener(this);
        holder.btGuide.setOnClickListener(this);
        holder.btService.setOnClickListener(this);
        holder.btTravel.setOnClickListener(this);
        holder.btUpdate.setOnClickListener(this);
        holder.btNotification.setOnClickListener(this);
        holder.btAbout.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                SnackbarUtils.show(root,"登陆",0,null);
                break;
            case R.id.bt_register:
                break;
            case R.id.bt_guide:
                break;
            case R.id.bt_service:
                break;
            case R.id.bt_travel:
                break;
            case R.id.bt_update:
                break;
            case R.id.bt_notification:
                break;
            case R.id.bt_about:
                break;
        }
    }

    class viewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bt_login)
        Button btLogin;
        @Bind(R.id.bt_register)
        Button btRegister;
        @Bind(R.id.bt_guide)
        Button btGuide;
        @Bind(R.id.bt_service)
        Button btService;
        @Bind(R.id.bt_travel)
        Button btTravel;
        @Bind(R.id.bt_update)
        Button btUpdate;
        @Bind(R.id.bt_notification)
        Button btNotification;
        @Bind(R.id.bt_about)
        Button btAbout;
        @Bind(R.id.root)
        LinearLayout root;

        viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

