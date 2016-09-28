package phone.demo.com.phone.view;

import android.widget.TextView;

import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.phone.R;

/**
 * @author Administrator
 * @name phone.demo.com.phone.view
 * @description
 * @date 2016/9/28 0028
 */
public class MainDelegate extends AppDelegate {

    private TextView textView;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        textView = get(R.id.textView);
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("哈哈 你变了。111");
    }
}
