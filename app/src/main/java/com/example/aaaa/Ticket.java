package com.example.aaaa;

// Ticket.java
public class Ticket {
    private String departure;
    private String destination;
    private String time;
    private String price;

    public Ticket(String departure, String destination, String time, String price) {
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.price = price;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }
}
