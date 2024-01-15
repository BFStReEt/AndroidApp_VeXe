package com.example.aaaa.Ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TicketAdapter extends ArrayAdapter<Ticket> {
    public TicketAdapter(Context context, List<Ticket> tickets) {
        super(context, 0, tickets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ticket ticket = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);

        if (ticket != null) {
            String displayText = "Từ " + ticket.getDeparture() + " -> " +
                    ticket.getDestination() + " \nThời gian:  " +
                    ticket.getTime() + " \nGiá vé: " +
                    ticket.getPrice() + "\n";
            textView.setText(displayText);
        }

        return convertView;
    }
}
