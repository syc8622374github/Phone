package phone.demo.com.library.presenter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import phone.demo.com.library.view.AppDelegate;

/**
 * @author cyc
 * @name phone.demo.com.library.presenter
 * @description presenter 基类
 * @date 2016/9/27 0027
 */
public abstract class ActivityPresenter<T extends AppDelegate> extends AppCompatActivity {
    protected T viewDelegate;
    protected Context context;
    protected Handler handler = new Handler(getMainLooper());
    private Toolbar toolbar;
    private static boolean isShowToolbar = true;

    /**
     * 初始化视图实体类
     */
    public ActivityPresenter() {
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        viewDelegate.create(getLayoutInflater(),null,savedInstanceState);
        setContentView(viewDelegate.getRootView());
        viewDelegate.initWidget();
        initToolbar();
    }

    /**
     * 初始化toolbar
     */
    protected void initToolbar(){
        if(getToolbarAvailable()){
            toolbar = viewDelegate.getToolbar();
            if(toolbar!=null){
                setSupportActionBar(toolbar);
            }
        }
    }

    /*--------------------开放方法--------------------*/

    /**
     * 获取视图协议类
     * @return
     */
    protected abstract Class<T> getDelegateClass();

    /**
     * 初始化一些监听等
     */
    protected void bindEvenListener() {
    }

    /**
     * 接收bundle回调
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 是否启用toolBar
     */
    protected boolean getToolbarAvailable() {
        return isShowToolbar;
    }

    /**
     * only Iconify use
     */
    protected void onUseIconifySetMenuItem(Menu menu) {
    }
}
