package ruijie.com.my12306.widget.calendarSelector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import ruijie.com.my12306.R;

/**
 * @author lvning
 * @version create time:2014-10-29_上午9:56:45
 * @Description 预订日选择
 */
public class CalendarSelectorActivity extends AppCompatActivity {

	/**
	 * 可选天数
	 */
	public static final String DAYS_OF_SELECT = "days_of_select";
	/**
	 * 上次预订日
	 */
	public static final String ORDER_DAY = "order_day";

	private int daysOfSelect;
	private String orderDay;

	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_selector);
		daysOfSelect = getIntent().getIntExtra(DAYS_OF_SELECT, 30);
		orderDay = getIntent().getStringExtra(ORDER_DAY);
		listView = (ListView) findViewById(R.id.lv_calendar);

		CalendarListAdapter adapter = new CalendarListAdapter(this, daysOfSelect, orderDay);
		listView.setAdapter(adapter);

		adapter.setOnCalendarOrderListener(orderInfo -> {
            Log.w("haha",orderInfo);
            Intent result = new Intent();
            result.putExtra(ORDER_DAY, orderInfo);
            setResult(RESULT_OK, result);
            finish();
        });
	}
}
