package com.example.cole.tipcalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Rating;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        float tip_percentage = sharedPref.getFloat(getString(R.string.saved_tip_percentage), 0);
        String currency_symbol = sharedPref.getString("saved_currency", "$");

        EditText tipPercentage = (EditText) findViewById(R.id.tipPercentage);
        tipPercentage.setText(String.format("%.2f", tip_percentage));

        TextView currencySymbol = (TextView) findViewById(R.id.dollarSign);
        currencySymbol.setText(currency_symbol);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateButtonPressed(View view) {
        showSummary(false);
    }

    public void suggestTipButtonPressed(View view) {
        showSummary(true);
    }

    public void showSummary(Boolean suggest) {
        EditText billAmount = (EditText) findViewById(R.id.billAmount);
        EditText tipPercentage = (EditText) findViewById(R.id.tipPercentage);
        EditText numberOfPeople = (EditText) findViewById(R.id.numberOfPeople);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        float tip = suggest ? 10 + (ratingBar.getRating() * 2) : Float.parseFloat(tipPercentage.getText().toString());

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("BILL_AMOUNT", billAmount.getText().toString());
        intent.putExtra("TIP_PERCENTAGE", Float.toString(tip));
        intent.putExtra("NUMBER_OF_PEOPLE", numberOfPeople.getText().toString());
        startActivity(intent);
    }
}
