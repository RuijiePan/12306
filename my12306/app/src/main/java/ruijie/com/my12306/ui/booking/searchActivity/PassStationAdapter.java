package ruijie.com.my12306.ui.booking.searchActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import ruijie.com.my12306.R;
import ruijie.com.my12306.bean.Station;
import ruijie.com.my12306.bean.StationInfo;

/**
 * Created by Administrator on 2016/9/2.
 */

public class PassStationAdapter extends BaseQuickAdapter<Station>{

    public PassStationAdapter(List<Station> list){
        super(R.layout.item_checi_station,list);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Station station) {
        baseViewHolder.setText(R.id.tv_cnumber,station.getCodenumber()+"")
                .setText(R.id.tv_from,station.getName())
                .setText(R.id.tv_out,station.getOutbounddate())
                .setText(R.id.tv_stop,"3分钟")
                .setText(R.id.tv_start,station.getArrivedate());
    }
}
