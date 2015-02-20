package com.example.cole.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        float tip_percentage = sharedPref.getFloat(getString(R.string.saved_tip_percentage), 0);
        String currency_symbol = sharedPref.getString("saved_currency", "$");

        EditText tipPercentage = (EditText) findViewById(R.id.defaultTipPercentage);
        tipPercentage.setText(Float.toString(tip_percentage));

        RadioButton euroRadioButton = (RadioButton) findViewById(R.id.euro);
        RadioButton poundRadioButton = (RadioButton) findViewById(R.id.pound);
        RadioButton dollarRadioButton = (RadioButton) findViewById(R.id.dollar);

        euroRadioButton.setChecked(currency_symbol.equals("€"));
        poundRadioButton.setChecked(currency_symbol.equals("£"));
        dollarRadioButton.setChecked(currency_symbol.equals("$"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void save(View view) {
        EditText tipPercentage = (EditText) findViewById(R.id.defaultTipPercentage);

        String currencySymbol;
        RadioGroup currencyRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = currencyRadioGroup.getCheckedRadioButtonId();
        if (selectedId == R.id.euro) {
            currencySymbol = "€";
        } else if (selectedId == R.id.pound) {
            currencySymbol = "£";
        } else {
            currencySymbol = "$";
        }

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(getString(R.string.saved_tip_percentage), Float.parseFloat(tipPercentage.getText().toString()));
        editor.putString("saved_currency", currencySymbol);
        editor.commit();
    }
}
