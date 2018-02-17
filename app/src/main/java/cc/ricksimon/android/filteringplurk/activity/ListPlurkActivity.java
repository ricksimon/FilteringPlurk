package cc.ricksimon.android.filteringplurk.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ListView;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.adapter.PlurkListAdapter;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.TimeLineBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.Log;

public class ListPlurkActivity extends BaseActivity {

    private ActionBar actionBar = null;
    private ListView lvPlurks = null;

    private PlurkListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_plurk);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPlurks(new PlurkOAuthCallback() {
            @Override
            public void onAPICallBack(BaseBean dataBean) {
                if(dataBean == null){
                    Log.e(TAG,"dataBean is null");
                    return;
                }

                if(dataBean instanceof TimeLineBean){
                    TimeLineBean tlb = (TimeLineBean) dataBean;

                    adapter.setPlurkList(tlb);
                }
            }
        });
    }

    private void initView(){
        lvPlurks = (ListView) findViewById(R.id.lvPlurks);

        adapter = new PlurkListAdapter(ListPlurkActivity.this);
        lvPlurks.setAdapter(adapter);

        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(R.string.title_plurks);
        }
    }
}
