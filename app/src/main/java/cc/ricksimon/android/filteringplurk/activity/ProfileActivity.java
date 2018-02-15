package cc.ricksimon.android.filteringplurk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.adapter.ProfileDetailListAdapter;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.GetImageFromWebTask;
import cc.ricksimon.android.filteringplurk.utils.Log;

public class ProfileActivity extends BaseActivity {

    public static final String TAG = ProfileActivity.class.getSimpleName();

    private ImageView ivAvatar = null;
    private ListView lvUserProfile = null;

    private ProfileDetailListAdapter adapter = null;
    private GetImageFromWebTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();
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

                if(dataBean instanceof UserBean){
                    UserBean ub = (UserBean) dataBean;

                    adapter.setProfileDetail(ub);
                    task = new GetImageFromWebTask(ivAvatar, ub.getAvatarBig());
                    task.execute();
                    showViews();
                }
            }
        });
    }

    private void initView(){
        ivAvatar = (ImageView)findViewById(R.id.ivAvatar);
        lvUserProfile = (ListView) findViewById(R.id.lvUserProfile);

        adapter = new ProfileDetailListAdapter(ProfileActivity.this);
        lvUserProfile.setAdapter(adapter);

        hideViews();
    }

    private void hideViews(){
        ivAvatar.setVisibility(View.INVISIBLE);
        lvUserProfile.setVisibility(View.INVISIBLE);
    }

    private void showViews(){
        ivAvatar.setVisibility(View.VISIBLE);
        lvUserProfile.setVisibility(View.VISIBLE);
    }
}
