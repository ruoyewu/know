package com.wuruoye.know.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wuruoye.know.R;
import com.wuruoye.know.base.ToolbarActivity;
import com.wuruoye.library.adapter.FragmentVPAdapter;
import com.wuruoye.library.adapter.OnPageChangeListenerAdapter;
import com.wuruoye.library.widget.changetab.ChangeTabLayout;

import java.util.Arrays;

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:19.
 * Description :
 */
public class MainActivity extends ToolbarActivity {
    private static final String[] TITLE = {"复习", "记录", "用户"};
    private ViewPager vp;
    private ChangeTabLayout ctl;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView() {
        super.initView();
        vp = findViewById(R.id.vp_main);
        ctl = findViewById(R.id.ctl_main);

        FragmentVPAdapter adapter = new FragmentVPAdapter(getSupportFragmentManager(),
                Arrays.asList(TITLE), Arrays.<Fragment>asList(new ReviewFragment(),
                new RecordFragment(), new UserFragment()));
        vp.addOnPageChangeListener(new OnPageChangeListenerAdapter() {
            @Override
            public void onPageSelected(int position) {
                setToolbarTitle(TITLE[position]);
            }
        });
        vp.setAdapter(adapter);
        ctl.attachViewPager(vp);
        vp.setCurrentItem(1);
    }
}
