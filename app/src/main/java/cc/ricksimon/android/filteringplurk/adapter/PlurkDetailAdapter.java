package cc.ricksimon.android.filteringplurk.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.FriendBean;
import cc.ricksimon.android.filteringplurk.bean.ResponseBean;
import cc.ricksimon.android.filteringplurk.bean.ResponseContentBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.utils.ContentViewClient;
import cc.ricksimon.android.filteringplurk.utils.GetImageFromWebTask;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

/**
 * Created by Simon on 2018/2/16.
 */

public class PlurkDetailAdapter extends BaseAdapter {

    public static final String TAG = PlurkDetailAdapter.class.getSimpleName();

    private Context context = null;
    private LayoutInflater inflater = null;

    private ArrayList<ResponseContentBean> responses = null;
    private HashMap<String,FriendBean> friends = null;

    private UserBean userBean = null;

    private View.OnClickListener onClickListener = null;

    public PlurkDetailAdapter(Context context, View.OnClickListener onClickListener){
        this.context = context;
        this.onClickListener = onClickListener;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        responses = new ArrayList<ResponseContentBean>();
        friends = new HashMap<String,FriendBean>();

        userBean = Util.getUserProfile(context);
    }

    public void setPlurkList(ResponseBean rb){
        responses = rb.getResponses();
        friends = rb.getFriends();

//        for(ResponseContentBean r: responses){
//            Log.e(TAG,"PLURK USER_ID:"+r.getUserId());
//        }
//        Set<String> k = friends.keySet();
//        for(String id: k){
//            Log.e(TAG,"USER_ID:"+friends.get(id).getId());
//        }
//        Log.e(TAG,"response count:"+responseCount);
//        Log.e(TAG,"responses seen:"+responsesSeen);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return responses.size();
    }

    @Override
    public ResponseContentBean getItem(int position) {
        if(responses.size() < position){
            return null;
        }else{
            return responses.get(position);
        }

    }

    @Override
    public long getItemId(int i) {
        //use index as Id
        //TODO: return plurk id?
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentView) {
        ViewHolder viewHolder;
//        PlurkListActivity.EditPlurkData editPlurkData = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_plurk_simple_content,parentView,false);

            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivAvatar);
            viewHolder.tvDisplayName = (TextView) convertView.findViewById(R.id.tvDisplayName);
            viewHolder.tvVerb = (TextView) convertView.findViewById(R.id.tvVerb);
            viewHolder.tvResponseCount = (TextView) convertView.findViewById(R.id.tvResponseCount);
            viewHolder.clContentHolder = (ConstraintLayout) convertView.findViewById(R.id.clContentHolder);
//            viewHolder.tvContent = convertView.findViewById(R.id.tvContent);
            viewHolder.wvContent = (WebView) convertView.findViewById(R.id.wvContent);

            convertView.setTag(R.id.TAG_VIEW_HOLDER,viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag(R.id.TAG_VIEW_HOLDER);
//            editPlurkData = (PlurkListActivity.EditPlurkData) convertView.getTag(R.id.TAG_PLURK_EDIT_DATA);
        }

        ResponseContentBean responseContentBean = getItem(position);
        FriendBean friend = friends.get(String.valueOf(responseContentBean.getUserId()));

//        if(editPlurkData == null ){
//            editPlurkData = new PlurkListActivity.EditPlurkData();
//        }
//        if(editPlurkData.isEmpty()){
//            editPlurkData.plurkContent = plurkBean.getContentRaw();
//            editPlurkData.plurkVerb = plurkBean.getQualifier();
//            editPlurkData.plurkId = plurkBean.getPlurkId();
//            editPlurkData.plurkResponses = plurkBean.getResponseCount();
//
//            convertView.setTag(R.id.TAG_PLURK_EDIT_DATA,editPlurkData);
//        }

        viewHolder.ivAvatar.setTag(R.id.TAG_PLURK_USER_ID,friend.getId());
        Glide.with(context).load(Util.getAvatarUrl(Util.TYPE_MEDIUM,friend)).into(viewHolder.ivAvatar);
//        GetImageFromWebTask task = new GetImageFromWebTask(viewHolder.ivAvatar, Util.getAvatarUrl(Util.TYPE_MEDIUM,friend));
//        task.execute();

        viewHolder.tvDisplayName.setText(friend.getDisplayName());

        viewHolder.tvVerb.setText(responseContentBean.getQualifier());
        viewHolder.tvResponseCount.setVisibility(View.GONE);
//        viewHolder.tvContent.setText(responseContentBean.getContent());

        viewHolder.wvContent.getSettings().setJavaScriptEnabled(true);
        viewHolder.wvContent.setWebViewClient(new ContentViewClient());
        viewHolder.wvContent.addJavascriptInterface(
                new ContentViewClient.GetWebViewHeightInterface(convertView,onSizeChangedListener)
                ,"getViewHeight");
        viewHolder.wvContent.loadData(responseContentBean.getContent(),"text/html", StandardCharsets.UTF_8.name());
        viewHolder.wvContent.setEnabled(false);
        convertView.setOnClickListener(onClickListener);

        convertView.setOnClickListener(onClickListener);

        return convertView;
    }

    private static class ViewHolder{
        ImageView ivAvatar;
        TextView tvDisplayName;
        TextView tvVerb;
        TextView tvResponseCount;
        ConstraintLayout clContentHolder;
//        TextView tvContent;
        WebView wvContent;
    }

    private ContentViewClient.OnSizeChangedListener onSizeChangedListener = new ContentViewClient.OnSizeChangedListener() {
        @Override
        public void onSizeChanged(final View view, final float sizeInPX) {
            if(view == null || view.getTag(R.id.TAG_VIEW_HOLDER) == null){
                return;
            }

            Handler handler = new Handler(context.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ViewHolder viewHolder = (ViewHolder)view.getTag(R.id.TAG_VIEW_HOLDER);

                    int h = (int)(sizeInPX * PlurkDetailAdapter.this.context.getResources().getDisplayMetrics().density);
                    ViewGroup.LayoutParams layoutParams = viewHolder.clContentHolder.getLayoutParams();
                    layoutParams.height = h;
                    viewHolder.clContentHolder.setLayoutParams(layoutParams);
                    view.requestLayout();
                }
            });
        }
    };
}
