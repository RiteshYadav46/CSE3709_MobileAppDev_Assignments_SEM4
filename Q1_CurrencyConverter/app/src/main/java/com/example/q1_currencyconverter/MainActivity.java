package com.example.q1_currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Basic exchange rates (relative to 1 USD)
    double USD = 1.0, INR = 83.0, JPY = 150.0, EUR = 0.92;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI elements
        EditText etAmount = findViewById(R.id.etAmount);
        Spinner spinnerFrom = findViewById(R.id.spinnerFrom);
        Spinner spinnerTo = findViewById(R.id.spinnerTo);
        Button btnConvert = findViewById(R.id.btnConvert);
        Button btnSettings = findViewById(R.id.btnSettings);
        TextView tvResult = findViewById(R.id.tvResult);

        // Setup Spinner
        String[] currencies = {"USD", "INR", "JPY", "EUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Conversion Button Logic
        btnConvert.setOnClickListener(v -> {
            String val = etAmount.getText().toString();
            if (!val.isEmpty()) {
                double amount = Double.parseDouble(val);
                String from = spinnerFrom.getSelectedItem().toString();
                String to = spinnerTo.getSelectedItem().toString();

                double inUSD = amount / getRate(from);
                double result = inUSD * getRate(to);

                tvResult.setText(String.format("%.2f %s", result, to));
            }
        });

        // Settings Button Logic (MUST BE INSIDE onCreate)
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    private double getRate(String currency) {
        switch (currency) {
            case "INR": return INR;
            case "JPY": return JPY;
            case "EUR": return EUR;
            default: return USD;
        }
    }
}