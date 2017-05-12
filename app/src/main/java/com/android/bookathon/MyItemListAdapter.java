package com.android.bookathon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.Models.ItemList;

import java.util.List;

/**
 * Created by LENOVO on 09-May-17.
 */

public class MyItemListAdapter extends ArrayAdapter<ItemList> {

    List<ItemList> itemList;
    Context context;
    private LayoutInflater mInflater;

    public MyItemListAdapter(@NonNull Context context, List<ItemList> objects) {
        super(context, 0, objects);
        this.context = context;
        itemList = objects;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ItemList getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.activity_upload__result, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

       ItemList item = getItem(position);

        vh.textViewName.setText(item.getBname());
        vh.textisbn.setText(item.getIsbn());
        vh.textauthor.setText(item.getAuthor());
        vh.textpublisher.setText(item.getPublisher());

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final TextView textViewName;
        public final TextView textisbn;
        public final TextView textpublisher;
        public final TextView textauthor;

        private ViewHolder(RelativeLayout rootView, TextView textpublisher, TextView textViewName, TextView textauthor, TextView textisbn) {
            this.rootView = rootView;
            this.textpublisher = textpublisher;
            this.textauthor = textauthor;
            this.textViewName = textViewName;
            this.textisbn = textisbn;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView textViewName = (TextView) rootView.findViewById(R.id.name_res_edit);
            TextView textisbn = (TextView) rootView.findViewById(R.id.isbn_res_edit);
            TextView textauthor = (TextView) rootView.findViewById(R.id.author_res_edit);
            TextView textpublisher = (TextView) rootView.findViewById(R.id.pub_res_edit);

            return new ViewHolder(rootView, textpublisher, textViewName,textauthor, textisbn);
        }
    }
}
