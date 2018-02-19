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
import java.util.Set;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.activity.PlurkListActivity;
import cc.ricksimon.android.filteringplurk.bean.PlurkBean;
import cc.ricksimon.android.filteringplurk.bean.TimeLineBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
import cc.ricksimon.android.filteringplurk.utils.ContentViewClient;
import cc.ricksimon.android.filteringplurk.utils.GetImageFromWebTask;
import cc.ricksimon.android.filteringplurk.utils.Log;
import cc.ricksimon.android.filteringplurk.utils.Util;

/**
 * Created by Simon on 2018/2/16.
 */

public class PlurkListAdapter extends BaseAdapter {

    public static final String TAG = PlurkListAdapter.class.getSimpleName();

    private Context context = null;
    private LayoutInflater inflater = null;
    private ArrayList<PlurkBean> plurks = null;
    private HashMap<String,UserBean> users = null;
    private View.OnClickListener onClickListener = null;

    public PlurkListAdapter(Context context, View.OnClickListener onClickListener){
        this.context = context;
        this.onClickListener = onClickListener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        plurks = new ArrayList<PlurkBean>();
        users = new HashMap<String,UserBean>();
    }

    public void setPlurkList(TimeLineBean timeLineBean){
        plurks = timeLineBean.getPlurks();
        users = timeLineBean.getUsers();

//        for(PlurkBean p: plurks){
//            Log.e(TAG,"PLURK OWNER_ID:"+p.getOwnerId());
//        }
//        Set<String> k = users.keySet();
//        for(String id: k){
//            Log.e(TAG,"USER_ID:"+users.get(id).getId());
//        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return plurks.size();
    }

    @Override
    public PlurkBean getItem(int position) {
        if(plurks.size() < position){
            return null;
        }else{
            return plurks.get(position);
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
        PlurkListActivity.EditPlurkData editPlurkData = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_plurk_simple_content,parentView,false);

            viewHolder.ivAvatar = convertView.findViewById(R.id.ivAvatar);
            viewHolder.tvDisplayName = convertView.findViewById(R.id.tvDisplayName);
            viewHolder.tvVerb = convertView.findViewById(R.id.tvVerb);
            viewHolder.tvResponseCount = convertView.findViewById(R.id.tvResponseCount);
            viewHolder.clContentHolder = convertView.findViewById(R.id.clContentHolder);
//            viewHolder.tvContent = convertView.findViewById(R.id.tvContent);
            viewHolder.wvContent = convertView.findViewById(R.id.wvContent);

            convertView.setTag(R.id.TAG_VIEW_HOLDER,viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag(R.id.TAG_VIEW_HOLDER);
            editPlurkData = (PlurkListActivity.EditPlurkData) convertView.getTag(R.id.TAG_PLURK_EDIT_DATA);
        }

        PlurkBean plurkBean = getItem(position);
        UserBean user = users.get(String.valueOf(plurkBean.getOwnerId()));

        if(editPlurkData == null ){
            editPlurkData = new PlurkListActivity.EditPlurkData();
        }
        if(editPlurkData.isEmpty()){
            editPlurkData.plurkContent = plurkBean.getContentRaw();
            editPlurkData.plurkVerb = plurkBean.getQualifier();
            editPlurkData.plurkId = plurkBean.getPlurkId();
            editPlurkData.plurkResponses = plurkBean.getResponseCount();

            convertView.setTag(R.id.TAG_PLURK_EDIT_DATA,editPlurkData);
        }

        viewHolder.ivAvatar.setTag(R.id.TAG_PLURK_USER_ID,user.getId());
        Glide.with(context).load(Util.getAvatarUrl(Util.TYPE_MEDIUM,user)).into(viewHolder.ivAvatar);
//        GetImageFromWebTask task = new GetImageFromWebTask(viewHolder.ivAvatar, Util.getAvatarUrl(Util.TYPE_MEDIUM,user));
//        task.execute();

        viewHolder.tvDisplayName.setText(user.getDisplayName());

        viewHolder.tvVerb.setText(plurkBean.getQualifier());
        viewHolder.tvResponseCount.setText(String.valueOf(plurkBean.getResponseCount()));
//        viewHolder.tvContent.setText(plurkBean.getContent());

        viewHolder.wvContent.getSettings().setJavaScriptEnabled(true);
        viewHolder.wvContent.setWebViewClient(new ContentViewClient());
        viewHolder.wvContent.addJavascriptInterface(
                new ContentViewClient.GetWebViewHeightInterface(convertView,onSizeChangedListener)
                ,"getViewHeight");
        viewHolder.wvContent.loadData(plurkBean.getContent(),"text/html", StandardCharsets.UTF_8.name());
        viewHolder.wvContent.setEnabled(false);
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

                    int h = (int)(sizeInPX * PlurkListAdapter.this.context.getResources().getDisplayMetrics().density);
                    ViewGroup.LayoutParams layoutParams = viewHolder.clContentHolder.getLayoutParams();
                    layoutParams.height = h;
                    viewHolder.clContentHolder.setLayoutParams(layoutParams);
                    view.requestLayout();
                }
            });
        }
    };
}
