package com.example.aaaa;// TicketDetailActivity.java

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicketDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        Intent intent = getIntent();
        String departure = intent.getStringExtra("departure");
        String destination = intent.getStringExtra("destination");
        String time = intent.getStringExtra("time");
        String price = intent.getStringExtra("price");

        TextView textViewDeparture = findViewById(R.id.textViewDeparture);
        TextView textViewDestination = findViewById(R.id.textViewDestination);
        TextView textViewTime = findViewById(R.id.textViewTime);
        TextView textViewPrice = findViewById(R.id.textViewPrice);

        textViewDeparture.setText("Điểm đi: " + departure);
        textViewDestination.setText("Điểm đến: " + destination);
        textViewTime.setText("Thời gian: " + time);
        textViewPrice.setText("Giá vé: " + price);

        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý khi người dùng xác nhận đặt vé và chuyển hướng tới trang thanh toán của VNPay
                processBookingConfirmation(price);
            }
        });
    }

    private void processBookingConfirmation(String price) {
        // Ở đây, bạn có thể thực hiện các bước chuẩn bị dữ liệu cho thanh toán
        // Ví dụ: Tạo đối tượng đơn hàng, lấy thông tin khách hàng, ...

        // Chuyển hướng tới trang thanh toán của VNPay
        redirectToVNPayPayment(price);
    }

    private void redirectToVNPayPayment(String price) {
        // Thay thế YOUR_VNPAY_API_URL và các tham số thanh toán theo đúng API của VNPay
        String vnpayApiUrl = " https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "https://sandbox.vnpayment.vn/merchant_webapi/merchant.html";

        // Tạo URI cho trang thanh toán VNPay
        Uri paymentUri = Uri.parse(vnpayApiUrl)
                .buildUpon()
                .appendQueryParameter("price", price)
                .appendQueryParameter("returnUrl", returnUrl)
                // Thêm các tham số thanh toán khác tại đây
                .build();

        // Mở trang thanh toán của VNPay trong trình duyệt
        Intent intent = new Intent(Intent.ACTION_VIEW, paymentUri);
        startActivity(intent);
    }
}
