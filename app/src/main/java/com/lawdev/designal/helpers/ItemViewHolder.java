package com.lawdev.designal.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lawdev.designal.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    //public final TextView textView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        //textView = itemView.getContext().
        //textView = (TextView) itemView;
    }

    public void sendMessage(View view) {
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(view.getContext(), text, duration);
        toast.show();
    }
}