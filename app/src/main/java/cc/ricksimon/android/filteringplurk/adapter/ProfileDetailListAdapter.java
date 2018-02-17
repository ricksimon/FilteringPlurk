package cc.ricksimon.android.filteringplurk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cc.ricksimon.android.filteringplurk.R;
import cc.ricksimon.android.filteringplurk.bean.UserBean;

/**
 * Created by Simon on 2018/2/15.
 */

public class ProfileDetailListAdapter extends BaseAdapter {

    private Context context = null;
    private LayoutInflater inflater = null;
    private ArrayList<ProfileDetailDataCell> cells = null;

    public ProfileDetailListAdapter(Context context){
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cells = new ArrayList<ProfileDetailDataCell>();
    }

    public void setProfileDetail(UserBean userBean){
        parseUserBeanToDataCell(userBean);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public ProfileDetailDataCell getItem(int position) {
        if(cells.size() < position){
            return null;
        }else{
            return cells.get(position);
        }
    }

    @Override
    public long getItemId(int i) {
        //use index as Id
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parentView) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_user_profile_detail,parentView,false);

            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvValue = convertView.findViewById(R.id.tvValue);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(cells.get(position).getName());
        viewHolder.tvValue.setText(cells.get(position).getValue());

        return convertView;
    }

    private void parseUserBeanToDataCell(UserBean userBean){
        if(userBean == null){
            return;
        }
        cells.clear();

        cells.add(new ProfileDetailDataCell(getString(R.string.name_display_name)    , userBean.getDisplayName()));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_nickname)        , userBean.getNickName()));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_full_name)       , userBean.getFullName()));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_birthday)        , userBean.getDateOfBirth()));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_location)        , userBean.getLocation()));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_gender)          , userBean.getGenderString()));

        cells.add(new ProfileDetailDataCell(getString(R.string.name_karma)           , String.valueOf(userBean.getKarma())));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_plurk_count)     , String.valueOf(userBean.getPlurksCount())));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_response_count)  , String.valueOf(userBean.getResponseCount())));

        cells.add(new ProfileDetailDataCell(getString(R.string.name_friend_count)    , String.valueOf(userBean.getFriendsCount())));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_fans_count)      , String.valueOf(userBean.getFansCount())));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_invited_count)   , String.valueOf(userBean.getRecruited())));
        cells.add(new ProfileDetailDataCell(getString(R.string.name_profile_view)    , String.valueOf(userBean.getProfileViews())));

        cells.add(new ProfileDetailDataCell(getString(R.string.name_about)           , userBean.getAbout()));
    }

    private String getString(int resId){
        if(context == null){
            return "";
        }

        return context.getString(resId);
    }

    private static class ViewHolder{
        TextView tvName;
        TextView tvValue;
    }

    public static class ProfileDetailDataCell {
        private String name;
        private String value;

        public ProfileDetailDataCell(String name, String value){
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}
