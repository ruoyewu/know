package com.wuruoye.know.ui.home;

import android.os.Bundle;
import android.view.View;

import com.wuruoye.know.R;
import com.wuruoye.know.base.ToolbarFragment;
import com.wuruoye.know.ui.home.contract.RecordContract;
import com.wuruoye.library.ui.WBaseFragment;

/**
 * Created : wuruoye
 * Date : 2019/3/6 23:24.
 * Description :
 */
public class RecordFragment extends WBaseFragment<RecordContract.Presenter>
        implements RecordContract.View {
    @Override
    protected int getContentView() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initView(View view) {

    }
}
