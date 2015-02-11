package com.example.cole.tipcalculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SummaryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView billAmountTextView = (TextView) findViewById(R.id.billAmount);
        TextView tipAmountTextView = (TextView) findViewById(R.id.tipAmount);
        TextView totalAmountTextView = (TextView) findViewById(R.id.totalAmount);

        Intent intent = getIntent();

        double billAmount = Double.parseDouble(intent.getStringExtra("BILL_AMOUNT"));
        double tipPercentage = Double.parseDouble(intent.getStringExtra("TIP_PERCENTAGE"));
        int numberOfPeople = Integer.parseInt(intent.getStringExtra("NUMBER_OF_PEOPLE"));

        double tipAmount = billAmount * (tipPercentage / 100);

        billAmountTextView.setText("$" + billAmount);
        tipAmountTextView.setText("$" + tipAmount);
        totalAmountTextView.setText("$" + (billAmount + tipAmount));
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
