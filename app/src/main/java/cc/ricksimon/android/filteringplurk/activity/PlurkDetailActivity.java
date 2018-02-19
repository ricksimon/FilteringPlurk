package cc.ricksimon.android.filteringplurk.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.adapter.PlurkDetailAdapter;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.ResponseBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.GetImageFromWebTask;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

public class PlurkDetailActivity extends BaseActivity {

    public static final String TAG = PlurkDetailActivity.class.getSimpleName();

    private TextView tvDisplayName = null;
    private TextView tvVerb = null;
    private TextView tvCount = null;
    private TextView tvContent = null;
    private ImageView ivAvatar = null;
    private Spinner spFunctions = null;

    private ListView lvResponses = null;

    private ActionBar actionBar = null;

    private UserBean userBean = null;
    private long plurkId = -1;
    private int plurkResponses = -1;
    private String plurkContent = null;
    private String plurkVerb = null;

    private ArrayAdapter<CharSequence> arrayAdapter = null;
    private PlurkDetailAdapter adapter = null;
    private GetImageFromWebTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plurk_detail);

        userBean = Util.getUserProfile(PlurkDetailActivity.this);
        plurkId = getIntent().getLongExtra(BaseActivity.EXTRA_PLURK_ID,-1);
        plurkResponses = getIntent().getIntExtra(BaseActivity.EXTRA_PLURK_RESPONSES,-1);
        plurkContent = getIntent().getStringExtra(BaseActivity.EXTRA_PLURK_CONTENT);
        plurkVerb = getIntent().getStringExtra(BaseActivity.EXTRA_PLURK_VERB);

        arrayAdapter = ArrayAdapter.createFromResource(
                PlurkDetailActivity.this,
                R.array.detail_functions,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPlurkResponse(new PlurkOAuthCallback() {
            @Override
            public void onAPICallBack(BaseBean dataBean) {
                if(dataBean == null){
                    Log.e(TAG,"getPlurkResponse-dataBean is null");
                    return;
                }

                if(dataBean instanceof ResponseBean){
                    ResponseBean rb = (ResponseBean) dataBean;

                    adapter.setPlurkList(rb);

                    plurkResponses = rb.getResponseCount();
                    tvCount.setText(String.valueOf(plurkResponses));
                }
            }
        },plurkId);
    }

    private void initView(){
        tvDisplayName = (TextView) findViewById(R.id.tvDisplayName);
        tvVerb = (TextView) findViewById(R.id.tvVerb);
        tvCount = (TextView) findViewById(R.id.tvCount);
        tvContent = (TextView) findViewById(R.id.tvContent);
        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        spFunctions = (Spinner) findViewById(R.id.spFunctions);

        lvResponses = (ListView) findViewById(R.id.lvResponses);

        actionBar = getSupportActionBar();

        tvDisplayName.setText(userBean.getDisplayName());
        tvVerb.setText(plurkVerb);;
        tvCount.setText(String.valueOf(plurkResponses));
        tvContent.setText(plurkContent);

        Glide.with(PlurkDetailActivity.this).load(Util.getAvatarUrl(Util.TYPE_BIG,userBean)).into(ivAvatar);
//        task = new GetImageFromWebTask(ivAvatar, Util.getAvatarUrl(Util.TYPE_BIG,userBean));
//        task.execute();

        spFunctions.setAdapter(arrayAdapter);
        spFunctions.setOnItemSelectedListener(onItemSelectedListener);

        adapter = new PlurkDetailAdapter(PlurkDetailActivity.this,onResponseClickListener);
        lvResponses.setAdapter(adapter);

        if(actionBar != null) {
            actionBar.setTitle(R.string.title_plurk_detail);
        }
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //TODO: run function here
            Log.e(TAG,"Action:"+arrayAdapter.getItem(position).toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private View.OnClickListener onResponseClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: operate click event
            Log.e(TAG,"response clicked");
        }
    };
}
