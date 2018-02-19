package cc.ricksimon.android.filteringplurk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ListView;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.adapter.PlurkListAdapter;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.TimeLineBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.Log;

public class PlurkListActivity extends BaseActivity {

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
                    Log.e(TAG,"getPlurks-dataBean is null");
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

        adapter = new PlurkListAdapter(PlurkListActivity.this,onPlurkClickListener);
        lvPlurks.setAdapter(adapter);

        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(R.string.title_plurks);
        }
    }

    private View.OnClickListener onPlurkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Object o = view.getTag(R.id.TAG_PLURK_EDIT_DATA);
            if(o.getClass().getSimpleName().equals(EditPlurkData.class.getSimpleName())){
                EditPlurkData editPlurkData = (EditPlurkData) o;
//                Intent intent = new Intent(PlurkListActivity.this, EditPlurkActivity.class);
                Intent intent = new Intent(PlurkListActivity.this, PlurkDetailActivity.class);
                intent.putExtra(BaseActivity.EXTRA_PLURK_ID, editPlurkData.plurkId);
                intent.putExtra(BaseActivity.EXTRA_PLURK_RESPONSES, editPlurkData.plurkResponses);
                intent.putExtra(BaseActivity.EXTRA_PLURK_VERB, editPlurkData.plurkVerb);
                intent.putExtra(BaseActivity.EXTRA_PLURK_CONTENT, editPlurkData.plurkContent);
                startActivity(intent);
            }else{
                Log.e(TAG,"Tag is null, ignore");
            }
        }
    };

    public static class EditPlurkData {
        public long plurkId = -1;
        public int plurkResponses = -1;
        public String plurkContent = null;
        public String plurkVerb = null;

        public boolean isEmpty(){
            if(plurkId == -1 || plurkResponses == -1 || plurkContent == null || !plurkContent.isEmpty() || plurkVerb == null || !plurkVerb.isEmpty()){
                return true;
            }else{
                return false;
            }
        }
    }
}
