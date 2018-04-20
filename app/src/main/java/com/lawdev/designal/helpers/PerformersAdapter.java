package com.lawdev.designal.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lawdev.designal.R;
import com.lawdev.designal.entities.Performer;

import java.util.ArrayList;

public class PerformersAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<Performer> group;

    public PerformersAdapter(Context context, ArrayList<Performer> group) {
        this.group = group;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return group.size();
    }

    @Override
    public Object getItem(int position) {
        return group.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.performer_item, parent, false);
        }

        Performer performer = getPerson(position);

        ((TextView) view.findViewById(R.id.textName)).setText(performer.getName());
        ((TextView) view.findViewById(R.id.textWorkingOn)).setText(performer.getWorkingOn());
        switch (performer.getId()) {
            case 0:
                ((ImageView) view.findViewById(R.id.photo)).setImageResource(R.mipmap.nikita);
                break;
            case 1:
                ((ImageView) view.findViewById(R.id.photo)).setImageResource(R.mipmap.maksim);
                break;
            case 2:
                ((ImageView) view.findViewById(R.id.photo)).setImageResource(R.mipmap.mark);
                break;
        }

        return view;
    }

    private Performer getPerson(int position) {
        return ((Performer) getItem(position));
    }
}
