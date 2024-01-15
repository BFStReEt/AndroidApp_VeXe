package com.example.aaaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ResignActivity extends AppCompatActivity {

    EditText textInputEditName, textInputEditEmail, textInputEditPassword;
    Button button;
    String name, email, password;
    TextView textViewError;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resign);

        progressBar = findViewById(R.id.loading);
        textInputEditName = findViewById(R.id.name);
        textInputEditEmail = findViewById(R.id.email);
        textInputEditPassword = findViewById(R.id.password);
        textViewError = findViewById(R.id.error);
        button = findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewError.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                name = String.valueOf(textInputEditName.getText());
                email = String.valueOf(textInputEditEmail.getText());
                password = String.valueOf(textInputEditPassword.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.1.32/Login/register.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                Log.d("Response", response);

                                if (response.equals("success")) {
                                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    textViewError.setText(response);
                                    textViewError.setVisibility(View.VISIBLE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        textViewError.setText(error.getLocalizedMessage());
                        textViewError.setVisibility(View.VISIBLE);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name);
                        params.put("email", email);
                        params.put("password", password);
                        return params;
                    }
                };

                queue.add(stringRequest);
            }
        });
    }
}
