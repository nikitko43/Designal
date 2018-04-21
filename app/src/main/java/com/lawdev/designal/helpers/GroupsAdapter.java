package com.lawdev.designal.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawdev.designal.R;
import com.lawdev.designal.entities.Group;
import com.lawdev.designal.entities.Performer;

import java.util.ArrayList;

public class GroupsAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Group> groups;

    public GroupsAdapter(Context context, ArrayList<Group> groups) {
        this.groups = groups;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.group_item, parent, false);
        }

        Group group = getGroup(position);

        ((TextView) view.findViewById(R.id.tv_name_group)).setText(group.getName());
        ((TextView) view.findViewById(R.id.tv_description_group)).setText(group.getMainTask());

        return view;
    }

    private Group getGroup(int position) {
        return ((Group) getItem(position));
    }
}
