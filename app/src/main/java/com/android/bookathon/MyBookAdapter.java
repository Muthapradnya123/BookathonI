package com.android.bookathon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.Models.BItems;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by LENOVO on 25-May-17.
 */

public class MyBookAdapter extends ArrayAdapter<BItems> {

    List<BItems> itemsList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyBookAdapter(Activity context, List<BItems> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        itemsList = objects;
    }

    public BItems getItem(int position) {
        return itemsList.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        BItems item = getItem(position);

        vh.textViewName.setText(item.getBookName());
        vh.textViewAuthor.setText(item.getAuthor());
        Picasso.with(context).load(item.getImagelink()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(vh.imageView);
        vh.textViewPublish.setText(item.getPublisher());
        vh.textViewCopies.setText(Integer.toString(item.getCopies()));
        vh.textViewUsername.setText(item.getUsername());

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewName;
        public final TextView textViewAuthor;
        public final TextView textViewPublish;
        public final TextView textViewCopies;
        public final TextView textViewUsername;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewName, TextView textViewAuthor, TextView textViewPublish, TextView textViewUsername, TextView textViewCopies) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewName = textViewName;
            this.textViewAuthor = textViewAuthor;
            this.textViewCopies = textViewCopies;
            this.textViewUsername = textViewUsername;
            this.textViewPublish = textViewPublish;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewAuthor = (TextView) rootView.findViewById(R.id.textViewAuthor);
            TextView textViewPublish = (TextView) rootView.findViewById(R.id.textViewPublish);
            TextView textViewCopies = (TextView) rootView.findViewById(R.id.textViewCopies);
            TextView textViewUsername = (TextView) rootView.findViewById(R.id.textViewUsername);
            return new ViewHolder(rootView, imageView, textViewName, textViewAuthor, textViewPublish, textViewUsername, textViewCopies);
        }
    }

}
