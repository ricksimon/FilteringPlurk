package cc.ricksimon.android.filteringplurk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.Log;

public class ProfileActivity extends BaseActivity {

    public static final String TAG = ProfileActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getUserProfile(new PlurkOAuthCallback() {
            @Override
            public void onAPICallBack(BaseBean dataBean) {
                if(dataBean == null){
                    Log.e(TAG,"dataBean is null");
                    return;
                }

                Log.e(TAG,dataBean.getBeanType());
                if(dataBean instanceof UserBean){
                    UserBean ub = (UserBean) dataBean;
                    Log.e(TAG,"DisplayName:"+ub.getDisplayName());
                }
            }
        });
    }
}
