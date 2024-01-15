package com.example.aaaa.Seat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaaa.R;
import com.example.aaaa.Ticket.TicketDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SeatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SeatAdapter seatAdapter;
    private TextView textViewDeparture;
    private TextView textViewDestination;

    private TextView textViewPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);

        recyclerView = findViewById(R.id.recyclerViewSeats);
        textViewDeparture = findViewById(R.id.textViewDeparture);
        textViewDestination = findViewById(R.id.textViewDestination);
        fetchSeatDataFromAPI();
        seatAdapter.setOnSeatClickListener(new SeatAdapter.OnSeatClickListener() {
            @Override
            public void onSeatClick(Seat seat) {
                // Xử lý khi một ghế được click
                Intent intent = new Intent(SeatActivity.this, TicketDetailActivity.class);
                intent.putExtra("seatNumber", seat.getSeatNumber());
                intent.putExtra("isBooked", seat.isBooked());
                startActivity(intent);
            }
        });
    }



    private void fetchSeatDataFromAPI() {
        List<Seat> dummySeatList = new ArrayList<>();
        dummySeatList.add(new Seat("A1", false));
        dummySeatList.add(new Seat("A2", true));
        dummySeatList.add(new Seat("A3", false));
        dummySeatList.add(new Seat("A4", false));
        dummySeatList.add(new Seat("A5", true));
        dummySeatList.add(new Seat("A6", true));
        dummySeatList.add(new Seat("A7", false));
        dummySeatList.add(new Seat("A8", false));
        dummySeatList.add(new Seat("B1", false));
        dummySeatList.add(new Seat("B2", true));
        dummySeatList.add(new Seat("B3", false));
        dummySeatList.add(new Seat("B4", false));
        dummySeatList.add(new Seat("B5", true));
        dummySeatList.add(new Seat("B6", true));
        dummySeatList.add(new Seat("B7", false));
        dummySeatList.add(new Seat("B8", false));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);

        seatAdapter = new SeatAdapter(dummySeatList);
        recyclerView.setAdapter(seatAdapter);

        displayTicketInfo();
    }

    private void displayTicketInfo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String departure = extras.getString("departure");
            String destination = extras.getString("destination");
            String price = extras.getString("price");

            textViewDeparture.setText(departure);
            textViewDestination.setText(destination);
        }
    }
}
