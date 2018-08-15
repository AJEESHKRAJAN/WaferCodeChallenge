package com.workaround.ajeesh.wafercodechallenge.WaferCountires;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private final String logName = "WFR-CLG-MAIN";
    private final String jsonUrl = "https://restcountries.eu/rest/v2/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
