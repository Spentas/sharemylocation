package com.spentas.javad.sharemylocation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spentas.javad.sharemylocation.R;
import com.spentas.javad.sharemylocation.model.User;

import java.util.List;

/**
 * Created by javad on 12/8/2015.
 */
public class FriendListAdapter extends BaseAdapter {
    List<User> users;
    LayoutInflater inflater;

    public FriendListAdapter(List<User> users, Context context) {
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_friendlistview, null);
            holder = new ViewHolder();
            holder.username = (TextView) convertView.findViewById(R.id.username_txt);
            holder.phoneNo = (TextView) convertView.findViewById(R.id.phone_txt);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.username.setText(users.get(position).getUsername());
        holder.phoneNo.setText(users.get(position).getPhoneNumber());


        return convertView;
    }


    private class ViewHolder {
        TextView username;
        TextView phoneNo;
    }

}
