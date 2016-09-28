package phone.demo.com.phone;

import android.os.Bundle;
import android.support.annotation.Nullable;

import phone.demo.com.library.activity.BaseActivity;
import phone.demo.com.phone.view.MainDelegate;

public class MainActivity extends BaseActivity<MainDelegate> {

    @Override
    protected Class getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
