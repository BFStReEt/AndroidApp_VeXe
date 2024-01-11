package com.example.aaaa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class TicketListActivity extends AppCompatActivity {

    private ListView listViewTickets;
    private EditText editTextDeparture;
    private EditText editTextDestination;
    private EditText editTextTime;
    private ArrayAdapter<Ticket> adapter; // Updated to use Ticket class

    // Replace with your API endpoint
    private static final String API_URL = "http://192.168.0.102/Login/search_tickets.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick);

        listViewTickets = findViewById(R.id.listViewTickets);
        editTextDeparture = findViewById(R.id.editTextDeparture);
        editTextDestination = findViewById(R.id.editTextDestination);
        editTextTime = findViewById(R.id.editTextTime);

        adapter = new TicketAdapter(this, new ArrayList<>());
        listViewTickets.setAdapter(adapter);

        listViewTickets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Ticket selectedTicket = adapter.getItem(position);

                Intent intent = new Intent(TicketListActivity.this, TicketDetailActivity.class);

                intent.putExtra("departure", selectedTicket.getDeparture());
                intent.putExtra("destination", selectedTicket.getDestination());
                intent.putExtra("time", selectedTicket.getTime());
                intent.putExtra("price", selectedTicket.getPrice());

                startActivity(intent);
            }
        });
    }

    public void performSearch(View view) {
        String departure = editTextDeparture.getText().toString().trim();
        String destination = editTextDestination.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();

        new SearchTicketsTask().execute(departure, destination, time);
    }

    // Updated to use Ticket class
    private class SearchTicketsTask extends AsyncTask<String, Void, List<Ticket>> {
        @Override
        protected List<Ticket> doInBackground(String... params) {
            // Background task to perform API call
            List<Ticket> searchResults = new ArrayList<>();

            try {
                URL url = new URL(API_URL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                writer.write("departure=" + URLEncoder.encode(params[0], "UTF-8") +
                        "&destination=" + URLEncoder.encode(params[1], "UTF-8") +
                        "&time=" + URLEncoder.encode(params[2], "UTF-8"));
                writer.flush();
                writer.close();
                outputStream.close();

                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }

                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject ticketObject = jsonArray.getJSONObject(i);
                    String departure = ticketObject.getString("departure");
                    String destination = ticketObject.getString("destination");
                    String time = ticketObject.getString("time");
                    String price = ticketObject.getString("price");

                    Ticket ticket = new Ticket(departure, destination, time, price);
                    searchResults.add(ticket);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return searchResults;
        }

        @Override
        protected void onPostExecute(List<Ticket> searchResults) {

            adapter.clear();
            adapter.addAll(searchResults);
            adapter.notifyDataSetChanged();

            if (searchResults.isEmpty()) {
                Toast.makeText(TicketListActivity.this, "Không tìm thấy vé xe", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
