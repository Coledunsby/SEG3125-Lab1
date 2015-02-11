package com.example.cole.tipcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

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

        EditText tipPercentage = (EditText) findViewById(R.id.tipPercentage);
        tipPercentage.setText(Float.toString(tip_percentage));
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

    public void showSummary(View view) {
        EditText billAmount = (EditText) findViewById(R.id.billAmount);
        EditText tipPercentage = (EditText) findViewById(R.id.tipPercentage);
        EditText numberOfPeople = (EditText) findViewById(R.id.numberOfPeople);

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("BILL_AMOUNT", billAmount.getText().toString());
        intent.putExtra("TIP_PERCENTAGE", tipPercentage.getText().toString());
        intent.putExtra("NUMBER_OF_PEOPLE", numberOfPeople.getText().toString());
        startActivity(intent);
    }
}
