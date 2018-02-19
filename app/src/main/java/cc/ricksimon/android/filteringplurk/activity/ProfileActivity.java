package cc.ricksimon.android.filteringplurk.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.adapter.ProfileDetailListAdapter;
import cc.ricksimon.android.filteringplurk.bean.BaseBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.oauth.PlurkOAuthCallback;
import cc.ricksimon.android.filteringplurk.utils.GetImageFromWebTask;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

public class ProfileActivity extends BaseActivity {

    public static final String TAG = ProfileActivity.class.getSimpleName();

    private ActionBar actionBar = null;
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

        UserBean userBean = Util.getUserProfile(ProfileActivity.this);

        if(userBean != null){
            Log.i(TAG,"userBean from shared preference");
            setUpProfileData(userBean);
        }else {
            Log.i(TAG,"userBean from API call");
            getUserProfile(new PlurkOAuthCallback() {
                @Override
                public void onAPICallBack(BaseBean dataBean) {
                    if (dataBean == null) {
                        Log.e(TAG, "getUserProfile-dataBean is null");
                        return;
                    }

                    if (dataBean instanceof UserBean) {
                        setUpProfileData((UserBean) dataBean);
                    }
                }
            });
        }
    }

    private void setUpProfileData(UserBean userBean){
        adapter.setProfileDetail(userBean);
        Glide.with(ProfileActivity.this).load(Util.getAvatarUrl(Util.TYPE_BIG,userBean)).into(ivAvatar);
//        task = new GetImageFromWebTask(ivAvatar, Util.getAvatarUrl(Util.TYPE_BIG,userBean));
//        task.execute();
        showViews();
    }

    private void initView(){
        ivAvatar = (ImageView)findViewById(R.id.ivAvatar);
        lvUserProfile = (ListView) findViewById(R.id.lvUserProfile);

        adapter = new ProfileDetailListAdapter(ProfileActivity.this);
        lvUserProfile.setAdapter(adapter);

        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(R.string.title_user_profile);
        }

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
