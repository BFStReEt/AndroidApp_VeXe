package com.example.aaaa;

import com.example.aaaa.Seat.Seat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("get_seats.php")
    Call<List<Seat>> getSeats();
}
