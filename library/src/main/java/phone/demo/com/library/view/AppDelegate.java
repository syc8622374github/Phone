package phone.demo.com.library.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author cyc
 * @name phone.demo.com.library.view
 * @description 视图代理层基类
 * @date 2016/9/27 0027
 */
public abstract class AppDelegate implements IDelegate {
    //视图管理器
    protected final SparseArray<View> mViews = new SparseArray<View>();

    protected View rootView;
    protected Context context;


    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getRootLayoutId(),container);
        context = rootView.getContext();
    }

    public final <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public View getRootView() {
        return rootView;
    }


    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    /**
     * 视图管理
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T bindView(int id){
        T view = (T) mViews.get(id);
        if(view==null){
            view = (T) rootView.findViewById(id);
            mViews.put(id,view);
        }
        return view;
    }

    @Override public View getLoadingTargetView() {
        return null;
    }

    /*---------开放方法-----------*/
    //获取layout资源文件id
    protected abstract int getRootLayoutId();
}
