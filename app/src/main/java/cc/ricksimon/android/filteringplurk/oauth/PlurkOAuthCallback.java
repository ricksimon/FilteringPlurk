package cc.ricksimon.android.filteringplurk.oauth;

import cc.ricksimon.android.filteringplurk.bean.BaseBean;

/**
 * Created by Simon on 2018/2/12.
 */

public interface PlurkOAuthCallback {

    public void onAPICallBack(BaseBean dataBean);

}
