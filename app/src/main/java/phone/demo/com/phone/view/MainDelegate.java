package phone.demo.com.phone.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.phone.R;
import phone.demo.com.phone.widget.HackViewPager;

/**
 * @author Administrator
 * @name phone.demo.com.phone.view
 * @description
 * @date 2016/9/28 0028
 */
public class MainDelegate extends AppDelegate {

    private TextView textView;
    private TabLayout mTabLayout;
    private HackViewPager viewpager;
    private String[] titles;
    private Fragment[] frgs;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        textView = get(R.id.textView);
        mTabLayout = get(R.id.tabLayout);
        viewpager = get(R.id.pager);
        //all pagers
        titles = getRootView().getContext().getResources().getStringArray(R.array.images_category_list_id);
        /*frgs = new Fragment[titles.length];
        for (int i = 0; i < titles.length; i++) {
            frgs[i] = new ImageListFrgPresenter();
            Bundle args = new Bundle();
            args.putString("title", titles[i]);
            frgs[i].setArguments(args);
        }*/
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("哈哈 你变了。111");
        mTabLayout.addTab(mTabLayout.newTab().setText("TabOne"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("TabTwo"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TabThree"));
        /*viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pageViews.size();
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }

            @Override
            public void destroyItem(View arg0, int arg1, Object arg2) {
            *//*if(pageViews.get(arg1)!=null){
                ((ViewPager) arg0).removeView(pageViews.get(arg1));
            }*//*
                ((ViewPager) arg0).removeAllViews();
            }

            @Override
            public Object instantiateItem(View arg0, int arg1) {
                ((ViewPager) arg0).addView(pageViews.get(arg1));
                return pageViews.get(arg1);
            }
        });*/
    }
}
