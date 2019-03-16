package com.wuruoye.know.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wuruoye.know.R;
import com.wuruoye.library.adapter.FragmentVPAdapter;
import com.wuruoye.library.ui.WBaseActivity;
import com.wuruoye.library.widget.changetab.ChangeTabLayout;

import java.util.Arrays;

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:19.
 * Description :
 */
public class MainActivity extends WBaseActivity {
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
        vp = findViewById(R.id.vp_main);
        ctl = findViewById(R.id.ctl_main);

        FragmentVPAdapter adapter = new FragmentVPAdapter(getSupportFragmentManager(),
                Arrays.asList("复习", "知识", "用户"), Arrays.<Fragment>asList(new ReviewFragment(),
                new KnowledgeFragment(), new UserFragment()));

        vp.setAdapter(adapter);
        ctl.attachViewPager(vp);
    }
}
