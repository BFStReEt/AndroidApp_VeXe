package com.example.aaaa.Seat;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaaa.R;

import java.util.List;

// SeatAdapter.java
public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private List<Seat> seatList;
    private OnSeatClickListener onSeatClickListener;

    public SeatAdapter(List<Seat> seatList) {
        this.seatList = seatList;
    }
    public Seat getSelectedSeat(int position) {
        return seatList.get(position);
    }

    public interface OnSeatClickListener {
        void onSeatClick(Seat seat);
    }

    public void setOnSeatClickListener(OnSeatClickListener listener) {
        this.onSeatClickListener = listener;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your seat item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);

        holder.seatNumberTextView.setText(seat.getSeatNumber());

        int color = seat.isBooked() ? Color.GRAY : Color.parseColor("#FFA500");
        holder.seatImageView.setColorFilter(color);
        holder.itemView.setEnabled(!seat.isBooked());
        float alpha = seat.isBooked() ? 0.5f : 1.0f;
        holder.itemView.setAlpha(alpha);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onSeatClickListener != null && !seat.isBooked()) {
                    onSeatClickListener.onSeatClick(seat);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    static class SeatViewHolder extends RecyclerView.ViewHolder {
        ImageView seatImageView;
        TextView seatNumberTextView;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatImageView = itemView.findViewById(R.id.seatImageView);
            seatNumberTextView = itemView.findViewById(R.id.seatNumberTextView);
        }
    }
}
