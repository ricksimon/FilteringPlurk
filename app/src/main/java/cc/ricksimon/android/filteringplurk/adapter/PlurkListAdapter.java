package cc.ricksimon.android.filteringplurk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.PlurkBean;
import cc.ricksimon.android.filteringplurk.bean.TimeLineBean;
import cc.ricksimon.android.filteringplurk.bean.UserBean;
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

    public PlurkListAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        plurks = new ArrayList<PlurkBean>();
        users = new HashMap<String,UserBean>();
    }

    public void setPlurkList(TimeLineBean timeLineBean){
        plurks = timeLineBean.getPlurks();
        users = timeLineBean.getUsers();

//        for(PlurkBean p: plurks){
//            Log.e(TAG,"PLURK USER_ID:"+p.getUserId());
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

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_plurk_simple_content,parentView,false);

            viewHolder.ivAvatar = convertView.findViewById(R.id.ivAvatar);
            viewHolder.tvDisplayName = convertView.findViewById(R.id.tvDisplayName);
            viewHolder.tvResponseCount = convertView.findViewById(R.id.tvResponseCount);
            viewHolder.tvContent = convertView.findViewById(R.id.tvContent);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserBean user = users.get(String.valueOf(getItem(position).getUserId()));

        GetImageFromWebTask task = new GetImageFromWebTask(viewHolder.ivAvatar, Util.getAvatarUrl(Util.TYPE_MEDIUM,user));
        task.execute();

        viewHolder.tvDisplayName.setText(user.getDisplayName());

        viewHolder.tvResponseCount.setText(String.valueOf(getItem(position).getResponseCount()));
        viewHolder.tvContent.setText(getItem(position).getContent());

        return convertView;
    }

    private static class ViewHolder{
        ImageView ivAvatar;
        TextView tvDisplayName;
        TextView tvResponseCount;
        TextView tvContent;//TODO:convert non-text content
    }
}
