package cc.ricksimon.android.filteringplurk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.PlurkBean;
import cc.ricksimon.android.filteringplurk.bean.StatusBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

public class EditPlurkActivity extends BaseActivity {

    public static final String TAG = EditPlurkActivity.class.getSimpleName();

    private TextView tvName = null;
    private Spinner spVerb = null;
    private EditText etContent = null;
    private Button btnSend = null;

    private UserBean userBean = null;
    private ArrayAdapter<CharSequence> arrayAdapter = null;

    private String verb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plurk);

        userBean = Util.getUserProfile(EditPlurkActivity.this);

        arrayAdapter = ArrayAdapter.createFromResource(
                EditPlurkActivity.this,
                R.array.plurk_verbs,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        initView();
    }

    private void initView(){
        tvName = (TextView)findViewById(R.id.tvName);
        spVerb = (Spinner)findViewById(R.id.spVerb);
        etContent = (EditText)findViewById(R.id.etContent);
        btnSend = (Button)findViewById(R.id.btnSend);

        tvName.setText(userBean.getDisplayName());

        spVerb.setAdapter(arrayAdapter);
        spVerb.setOnItemSelectedListener(onItemSelectedListener);
        spVerb.setSelection(0);

        btnSend.setOnClickListener(onClickListener);
    }

    private AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            verb = arrayAdapter.getItem(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            spVerb.setSelection(0);
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            createPlurk(new PlurkOAuthCallback() {
                @Override
                public void onAPICallBack(BaseBean dataBean) {
                    if (dataBean == null) {
                        Log.e(TAG, "dataBean is null");
                        return;
                    }

                    //TODO: show message and return to timeline
                    if (dataBean instanceof StatusBean) {
                        Log.e(TAG,"error, message:"+((StatusBean) dataBean).getMessage());
                    }else if(dataBean instanceof PlurkBean){
                        Log.e(TAG,"success, id:"+((PlurkBean) dataBean).getPlurkId());
                    }
                }
            },etContent.getText().toString(),verb);
        }
    };
}
