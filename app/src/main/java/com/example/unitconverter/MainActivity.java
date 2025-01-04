package com.example.unitconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner spinnerFromUnit, spinnerToUnit;
    Button convertButton;
    TextView resultTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        String[] units = {"Kilometer", "Meter", "Centimeter", "Millimeter", "Kilogram", "Gram", "Milligram", "Liter", "Milliliter", "Minute",
                "Hour", "Second", "Celsius", "Fahrenheit", "Kelvin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,  // Use the custom spinner layout
                units
        );
        adapter.setDropDownViewResource(R.layout.spinner_item); // Apply the same style to dropdown
        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);

        convertButton.setOnClickListener(v -> performConversion());
    }

    @SuppressLint("SetTextI18n")
    private void performConversion() {
        String value = inputValue.getText().toString();
        if (value.isEmpty()) {
            resultTextView.setText("Please enter a value.");
            return;
        }

        double inputValueInDouble = Double.parseDouble(value);
        String fromUnit = spinnerFromUnit.getSelectedItem().toString();
        String toUnit = spinnerToUnit.getSelectedItem().toString();

        double result = convertUnits(inputValueInDouble, fromUnit, toUnit);
        resultTextView.setText("Result: " + result);
    }

    private double convertUnits(double value, String fromUnit, String toUnit) {
        // Distance Conversions
        if (fromUnit.equals("Kilometer") && toUnit.equals("Meter")) return value * 1000;
        if (fromUnit.equals("Meter") && toUnit.equals("Kilometer")) return value / 1000;
        if (fromUnit.equals("Meter") && toUnit.equals("Centimeter")) return value * 100;
        if (fromUnit.equals("Centimeter") && toUnit.equals("Meter")) return value / 100;
        if (fromUnit.equals("Centimeter") && toUnit.equals("Millimeter")) return value * 10;
        if (fromUnit.equals("Millimeter") && toUnit.equals("Centimeter")) return value / 10;
        if (fromUnit.equals("Kilometer") && toUnit.equals("Centimeter")) return value * 100000;
        if (fromUnit.equals("Centimeter") && toUnit.equals("Kilometer")) return value / 100000;
        if (fromUnit.equals("Kilometer") && toUnit.equals("Millimeter")) return value * 1_000_000;
        if (fromUnit.equals("Millimeter") && toUnit.equals("Kilometer")) return value / 1_000_000;
        if (fromUnit.equals("Meter") && toUnit.equals("Millimeter")) return value * 1000;
        if (fromUnit.equals("Millimeter") && toUnit.equals("Meter")) return value / 1000;

        // Weight Conversions
        if (fromUnit.equals("Kilogram") && toUnit.equals("Gram")) return value * 1000;
        if (fromUnit.equals("Gram") && toUnit.equals("Kilogram")) return value / 1000;
        if (fromUnit.equals("Gram") && toUnit.equals("Milligram")) return value * 1000;
        if (fromUnit.equals("Milligram") && toUnit.equals("Gram")) return value / 1000;
        if (fromUnit.equals("Kilogram") && toUnit.equals("Milligram")) return value * 1_000_000;
        if (fromUnit.equals("Milligram") && toUnit.equals("Kilogram")) return value / 1_000_000;

        // Volume Conversions
        if (fromUnit.equals("Kiloliter") && toUnit.equals("Liter")) return value * 1000;
        if (fromUnit.equals("Liter") && toUnit.equals("Kiloliter")) return value / 1000;
        if (fromUnit.equals("Liter") && toUnit.equals("Milliliter")) return value * 1000;
        if (fromUnit.equals("Milliliter") && toUnit.equals("Liter")) return value / 1000;
        if (fromUnit.equals("Centiliter") && toUnit.equals("Milliliter")) return value * 10;
        if (fromUnit.equals("Milliliter") && toUnit.equals("Centiliter")) return value / 10;
        if (fromUnit.equals("Liter") && toUnit.equals("Centiliter")) return value * 100;
        if (fromUnit.equals("Centiliter") && toUnit.equals("Liter")) return value / 100;

        // Time Conversions
        if (fromUnit.equals("Minute") && toUnit.equals("Second")) return value * 60;
        if (fromUnit.equals("Second") && toUnit.equals("Minute")) return value / 60;
        if (fromUnit.equals("Hour") && toUnit.equals("Minute")) return value * 60;
        if (fromUnit.equals("Minute") && toUnit.equals("Hour")) return value / 60;
        if (fromUnit.equals("Hour") && toUnit.equals("Second")) return value * 3600;
        if (fromUnit.equals("Second") && toUnit.equals("Hour")) return value / 3600;
        if (fromUnit.equals("Day") && toUnit.equals("Hour")) return value * 24;
        if (fromUnit.equals("Hour") && toUnit.equals("Day")) return value / 24;
        if (fromUnit.equals("Week") && toUnit.equals("Day")) return value * 7;
        if (fromUnit.equals("Day") && toUnit.equals("Week")) return value / 7;
        if (fromUnit.equals("Month") && toUnit.equals("Day")) return value * 30; // Approximate
        if (fromUnit.equals("Day") && toUnit.equals("Month")) return value / 30; // Approximate
        if (fromUnit.equals("Year") && toUnit.equals("Day")) return value * 365; // Approximate
        if (fromUnit.equals("Day") && toUnit.equals("Year")) return value / 365; // Approximate

        // Temperature Conversions
        if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) return (value * 9 / 5) + 32;
        if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) return (value - 32) * 5 / 9;
        if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) return value + 273.15;
        if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) return value - 273.15;
        if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) return ((value - 32) * 5 / 9) + 273.15;
        if (fromUnit.equals("Kelvin") && toUnit.equals("Fahrenheit")) return ((value - 273.15) * 9 / 5) + 32;

        // If no conversion matches, return the input value
        return value;
    }
}

