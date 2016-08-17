package ruijie.com.my12306.widget;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2016/8/17.
 */

public class ProgressFragment extends Fragment{

    public boolean isPrepare = false;

    //Override this method to change content view
    public View onCreateContentView(LayoutInflater inflater) {
        return null;
    }

    //Override this method to change error view
    public View onCreateContentErrorView(LayoutInflater inflater) {
        return null;
    }
}
