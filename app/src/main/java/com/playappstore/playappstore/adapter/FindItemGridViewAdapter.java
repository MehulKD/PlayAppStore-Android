package com.playappstore.playappstore.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.playappstore.playappstore.content.DummyContent.DummyItem;
import com.playappstore.playappstore.entity.FindBean;
import com.playappstore.playappstore.view.Fragment.FindFragment.OnListFragmentInteractionListener;
import com.playappstore.playappstore.R;
import com.playappstore.playappstore.view.SmoothCheckBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FindItemGridViewAdapter extends BaseAdapter {

    private final ArrayList<FindBean> mValues;
    private final OnListFragmentInteractionListener mListener;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;
    private final Set<String> bundleIds;

    public FindItemGridViewAdapter(ArrayList<FindBean> items, OnListFragmentInteractionListener listener, SharedPreferences sp) {
        mValues = items;
        mListener = listener;
        this.sp = sp;
        edit = sp.edit();
        bundleIds = sp.getStringSet("bundleId", new HashSet<String>());
    }

    @Override
    public int getCount () {
        return mValues.size();
    }
    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }
    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // view holder pattern
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_find_item, parent, false);

            final ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder)convertView.getTag();

        holder.nameTextView.setText(mValues.get(position).getName());
        Context context = holder.mView.getContext();

        Glide.with(context).load(mValues.get(position).getIcon()).into(holder.coverImageView);
        if (bundleIds.contains(mValues.get(position).getBundleId())){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked){
                    bundleIds.add(mValues.get(position).getBundleId());
                    edit.putStringSet("bundleId",bundleIds);
                    edit.commit();
                }else{
                    bundleIds.remove(mValues.get(position).getBundleId());
                    edit.putStringSet("bundleId",bundleIds);
                    edit.commit();
                }
            }
        });
        return convertView;
    }

    public void setData(ArrayList<FindBean> body) {
        mValues.clear();
        mValues.addAll(body);
        notifyDataSetChanged();
    }

    private class ViewHolder  {
        private final View mView;
        private final TextView nameTextView;
        private final ImageView coverImageView;
        private final SmoothCheckBox checkBox;
        private DummyItem mItem;

        public ViewHolder(View view) {
            mView = view;
            nameTextView = (TextView) view.findViewById(R.id.textview_book_name);
            coverImageView = (ImageView) view.findViewById(R.id.imageview_cover_art);
            checkBox = (SmoothCheckBox) view.findViewById(R.id.checkbox_favorite);
        }

        public String toString() {
            return super.toString() + " '" + nameTextView.getText() + "'";
        }
    }
}
