package com.playappstore.playappstore.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.playappstore.playappstore.content.DummyContent.DummyItem;
import com.playappstore.playappstore.entity.FindBean;
import com.playappstore.playappstore.view.Fragment.FavouriteFragment.OnListFragmentInteractionListener;
import com.playappstore.playappstore.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FavouriteItemRecyclerViewAdapter extends RecyclerView.Adapter<FavouriteItemRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ArrayList<FindBean>> mValues;
    private OnListFragmentInteractionListener mListener;
    private LayoutInflater inflater;
    private Context context;

    public FavouriteItemRecyclerViewAdapter(ArrayList<ArrayList<FindBean>> items, OnListFragmentInteractionListener listener,
                                            LayoutInflater inflater, Context context) {
        mValues = items;
        mListener = listener;
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mNameView.setText(mValues.get(position).get(0).getName());
        holder.mNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"更多",0).show();
            }
        });
        holder.list.removeAllViews();
        if (mValues.size()!=0&&mValues.get(position)!=null){
            ArrayList<FindBean> lsitData = mValues.get(position);
            for (FindBean findBean : lsitData){
                View view = inflater.inflate(R.layout.find_child_list,null);
                Glide.with(context).load(findBean.getIcon()).
                        into((ImageView) view.findViewById(R.id.app_icon));
                TextView upTime = (TextView) view.findViewById(R.id.time);
                upTime.setText("更新时间:"+findBean.getUpdatedAt());
                TextView versions = (TextView) view.findViewById(R.id.versions);
                versions.setText("版本:"+findBean.getVersion());
                TextView dowload = (TextView) view.findViewById(R.id.dowload);
                dowload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"下载",0).show();
                    }
                });
                holder.list.addView(view);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mNameView;
        public LinearLayout list;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.name);
            list = (LinearLayout) view.findViewById(R.id.list);
        }

    }
}
