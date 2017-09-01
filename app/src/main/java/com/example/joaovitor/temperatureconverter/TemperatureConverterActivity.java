package com.example.joaovitor.temperatureconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;

import java.util.Locale;

/**
 * Created by joaovitor on 01/09/17.
 */


public class TemperatureConverterActivity extends AppCompatActivity {

    private AppCompatEditText editField;
    private AppCompatButton convertButton;
    private AppCompatTextView resultText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);
        editField = (AppCompatEditText) findViewById(R.id.edit_field);
        convertButton = (AppCompatButton) findViewById(R.id.convert_button);
        resultText = (AppCompatTextView) findViewById(R.id.result_text);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertTemperature();
            }

        });

        editField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    convertTemperature();
                    return true;
                }
                return false;
            }
        });
    }

    private void convertTemperature() {
        try {
            Double value = Double.valueOf(editField.getText().toString());
            Double result = celsius2Farenheit(value);
            resultText.setTextColor(ContextCompat.getColor(resultText.getContext(), R.color.colorValid));
            resultText.setText(String.format(Locale.getDefault(), getString(R.string.fahrenheit_result), result));
        } catch (Exception e) {
            resultText.setTextColor(ContextCompat.getColor(TemperatureConverterActivity.this, R.color.colorInvalid));
            resultText.setText(R.string.invalid_input);
        }
    }

    private Double celsius2Farenheit(Double value) {
        return 1.8 * value + 32;
    }
}
