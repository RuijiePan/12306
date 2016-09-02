package ruijie.com.my12306.ui.booking.searchActivity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.CheciData;
import ruijie.com.my12306.event.CheciAllInfo;
import ruijie.com.my12306.widget.FlowLayout;

/**
 * Created by prj on 2016/8/20.
 */

public class BookingSearchAdapter extends BaseQuickAdapter<CheciAllInfo> {

    private Context context;
    public BookingSearchAdapter(ArrayList<CheciAllInfo> list, Context context) {
        super(R.layout.item_checi_select, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CheciAllInfo checiData) {
        baseViewHolder.setText(R.id.textview_cnumber,checiData.getCheciData().getCodenumber())
                .setText(R.id.textview_from_city,checiData.getCheciData().getStartsite())
                .setText(R.id.textview_to_city,checiData.getCheciData().getEndsite())
                .setText(R.id.textview_sum_time, "0")
                .setText(R.id.textview_to_time,checiData.getCheciData().getEnddate())
                .setText(R.id.textview_from_time,checiData.getCheciData().getStartdate());

        RecyclerView recyclerView = baseViewHolder.getView(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        PassStationAdapter passStationAdapter = new PassStationAdapter(checiData.getStation());
        recyclerView.setAdapter(passStationAdapter);
    }

}
