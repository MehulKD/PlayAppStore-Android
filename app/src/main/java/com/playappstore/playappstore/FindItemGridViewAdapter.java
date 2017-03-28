package com.playappstore.playappstore;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.playappstore.playappstore.DummyContent.DummyItem;
import com.playappstore.playappstore.FindFragment.OnListFragmentInteractionListener;

import java.net.URI;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FindItemGridViewAdapter extends BaseAdapter {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public FindItemGridViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
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
    public View getView(int position, View convertView, ViewGroup parent) {


        // view holder pattern
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_find_item, parent, false);

            final ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        final ViewHolder holder = (ViewHolder)convertView.getTag();

        holder.nameTextView.setText("What is Success");
        holder.authorTextView.setText("Harry y");
        //holder.coverImageView.setImageResource();
        //holder.coverImageView.setImageURI(imageUrl);
        Context context = holder.mView.getContext();
        String url = "https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg";
        //String url = "https://45.77.13.248:1337/cgi/files/playappstore/a0ab29fe812a0582b6dd355e8dcaac66_icon.png";
        Glide.with(context).load(url).into(holder.coverImageView);


        return convertView;
    }
    public int getItemCount() {
        return mValues.size();
    }

    private class ViewHolder  {
        private final View mView;
        private final TextView nameTextView;
        private final TextView authorTextView;
        private final ImageView coverImageView;
        private final  SmoothCheckBox checkBox;
        private DummyItem mItem;

        public ViewHolder(View view) {
            mView = view;
            nameTextView = (TextView) view.findViewById(R.id.textview_book_name);
            authorTextView = (TextView) view.findViewById(R.id.textview_book_author);
            coverImageView = (ImageView) view.findViewById(R.id.imageview_cover_art);
            checkBox = (SmoothCheckBox) view.findViewById(R.id.checkbox_favorite);
        }

        public String toString() {
            return super.toString() + " '" + nameTextView.getText() + "'";
        }
    }
}
