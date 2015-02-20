package com.example.cole.tipcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SummaryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String currency_symbol = sharedPref.getString("saved_currency", "$");

        TextView tipTextView = (TextView) findViewById(R.id.tipTextView);
        TextView billAmountTextView = (TextView) findViewById(R.id.billAmount);
        TextView tipAmountTextView = (TextView) findViewById(R.id.tipAmount);
        TextView totalAmountTextView = (TextView) findViewById(R.id.totalAmount);

        Intent intent = getIntent();

        double billAmount = Double.parseDouble(intent.getStringExtra("BILL_AMOUNT"));
        double tipPercentage = Double.parseDouble(intent.getStringExtra("TIP_PERCENTAGE"));
        int numberOfPeople = Integer.parseInt(intent.getStringExtra("NUMBER_OF_PEOPLE"));

        double tipAmount = billAmount * (tipPercentage / 100);
        double totalAmount = billAmount + tipAmount;

        tipTextView.setText("TIP (%" + String.format("%.2f", tipPercentage) + ")");
        billAmountTextView.setText(currency_symbol + String.format("%.2f", billAmount));
        tipAmountTextView.setText(currency_symbol + String.format("%.2f", tipAmount));
        totalAmountTextView.setText(currency_symbol + String.format("%.2f", totalAmount));

        LinearLayout perPersonView = (LinearLayout) findViewById(R.id.PerPersonView);

        if (numberOfPeople > 1) {
            double tipPerPerson = tipAmount / numberOfPeople;
            double eachPersonPays = totalAmount / numberOfPeople;

            TextView tipPerPersonLabel = (TextView) findViewById(R.id.TipPerPersonLabel);
            TextView eachPersonPaysLabel = (TextView) findViewById(R.id.EachPersonPaysLabel);

            tipPerPersonLabel.setText(currency_symbol + String.format("%.2f", tipPerPerson));
            eachPersonPaysLabel.setText(currency_symbol + String.format("%.2f", eachPersonPays));
        } else {
            perPersonView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
