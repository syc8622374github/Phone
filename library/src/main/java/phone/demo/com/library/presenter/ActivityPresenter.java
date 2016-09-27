package phone.demo.com.library.presenter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import phone.demo.com.library.R;
import phone.demo.com.library.util.varyview.VaryViewHelper;
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
    //加载数据流转控制器
    private VaryViewHelper varyViewHelper;

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

        if (viewDelegate.getLoadingTargetView() != null) {
            varyViewHelper = new VaryViewHelper.Builder()
                    .setDataView(viewDelegate.getLoadingTargetView())
                    .setLoadingView(LayoutInflater.from(context).inflate(R.layout.layout_loadingview, null))
                    .setEmptyView(LayoutInflater.from(context).inflate(R.layout.layout_emptyview, null))
                    .setErrorView(LayoutInflater.from(context).inflate(R.layout.layout_errorview, null))
                    .setRefreshListener(new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            onRetryListener();
                        }
                    })
                    .build();
        }

        Bundle extras = getIntent().getExtras();

        if (null != extras) {
            getBundleExtras(extras);
        }
    }

    /**
     * 当应用被回收后重新启动应用需要重新初始化下视图代理器
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (viewDelegate == null) {
            try {
                viewDelegate = getDelegateClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("create IDelegate error");
            } catch (IllegalAccessException e) {
                throw new RuntimeException("create IDelegate error");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * activity生命周期销毁所做操作
     * 1.清空handler队列
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
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

    /**
     * 如果有设置loadingView，加载失败时重试点击的回调
     *
     * @see phone.demo.com.library.view.IDelegate #getLoadingTargetView()
     */
    protected void onRetryListener() {
    }
}
