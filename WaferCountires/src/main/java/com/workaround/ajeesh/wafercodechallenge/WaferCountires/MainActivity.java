package com.workaround.ajeesh.wafercodechallenge.WaferCountires;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers.RecyclerViewSwipeHelper;
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Helpers.RecyclerViewSwipeListener;
import com.workaround.ajeesh.wafercodechallenge.WaferCountires.Modals.Countries;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewSwipeListener {

    private final String logName = "WFR-CLG-MAIN";
    private final String jsonUrl = "https://restcountries.eu/rest/v2/all";
    private List<Countries> mCountries;
    private Gson gson;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private CoordinatorLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogHelper.LogThreadId(logName, "OnCreate: Main Activity - Wafer Coding Challenge");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Toolbar toolbar = findViewById(R.id.waferToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Countries list");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        baseLayout = findViewById(R.id.baseLayout);

        mCountries = new ArrayList<>();

        adapter = new RecyclerViewAdapter(mCountries, getApplicationContext());

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration((new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)));
        recyclerView.setAdapter(adapter);

        LoadCountriesDataUsingVolley();

        ItemTouchHelper.SimpleCallback swipeItem = new RecyclerViewSwipeHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(swipeItem).attachToRecyclerView(recyclerView);
    }

    private void LoadCountriesDataUsingVolley() {
        LogHelper.LogThreadId(logName, "Volley call has been started");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, jsonUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray theJsonArray = new JSONArray(response);
                    LogHelper.LogThreadId(logName, "Total list count : " + theJsonArray.length());

                    mCountries.clear();
                    List<Countries> result = Arrays.asList(gson.fromJson(theJsonArray.toString(), Countries[].class));
                    mCountries.addAll(result);

                    adapter.notifyDataSetChanged();
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

    @Override
    public void onSwipeCompletion(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerViewAdapter.RecyclerViewHolder) {
            String name = mCountries.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Countries deletedItem = mCountries.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            adapter.removeItem(viewHolder.getAdapterPosition());
        }
    }
}

