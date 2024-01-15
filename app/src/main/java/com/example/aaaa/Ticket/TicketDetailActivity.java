package com.example.aaaa.Ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aaaa.R;

public class TicketDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);


        TextView textViewDeparture = findViewById(R.id.textViewDeparture);
        TextView textViewDestination = findViewById(R.id.textViewDestination);
        TextView textViewTime = findViewById(R.id.textViewTime);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        Button confirmButton = findViewById(R.id.confirmButton);

        textViewDeparture.setText("Điểm đi: Hà Nội");
        textViewDestination.setText("Điểm đến: Hồ Chí Minh" );
        textViewTime.setText("Thời gian: 2024-01-10");
        textViewPrice.setText("Giá vé: 530000");
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TicketDetailActivity.this, "Đặt vé thành công!", Toast.LENGTH_SHORT).show();
                Intent ticketListIntent = new Intent(TicketDetailActivity.this, TicketListActivity.class);
                startActivity(ticketListIntent);
            }
        });

    }
    private void processBookingConfirmation(String price) {

    }
}
