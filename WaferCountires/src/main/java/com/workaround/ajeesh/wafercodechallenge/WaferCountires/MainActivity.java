package com.workaround.ajeesh.wafercodechallenge.WaferCountires;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers.LogHelper;
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers.RecyclerViewAdapter;
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals.Countries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Vars
    private final String logName = "WFR-CLG-MAIN";
    private final String jsonUrl = "https://restcountries.eu/rest/v2/all";
    private List<Countries> mCountries;
    private Gson gson;

    //Widgets
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogHelper.LogThreadId(logName, "OnCreate: Main Activity - Wafer Coding Challenge");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCountries = new ArrayList<>();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        LoadCountriesDataUsingVolley();
    }

    private void LoadCountriesDataUsingVolley() {
        LogHelper.LogThreadId(logName, "Volley call has been started");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray theJsonArray = new JSONArray(response);
                    LogHelper.LogThreadId(logName, "Total list count : " + theJsonArray.length());

                    mCountries = Arrays.asList(gson.fromJson(theJsonArray.toString(), Countries[].class));
                    adapter = new RecyclerViewAdapter(mCountries, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    LogHelper.LogThreadId(logName, "LoadCountriesDataUsingVolley Exception: " + e.getMessage());

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "URL returned an error : " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

