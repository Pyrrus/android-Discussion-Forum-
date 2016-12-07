package com.example.guest.messagingboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guest.messagingboard.R;
import com.example.guest.messagingboard.model.Message;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/6/16.
 */
public class MessagesAdapter extends ArrayAdapter<Message> {
    public MessagesAdapter(Context context, ArrayList<Message> messages) {
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView Name = (TextView) convertView.findViewById(R.id.titleMassage);
        Name.setText(message.getTitle());

        return convertView;
    }
}
