package com.android.claytonturner.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Boolean tipSelected = false;
        final Boolean billEntered = false;
        //int tipOptions = 4;

        final EditText bill = (EditText) findViewById(R.id.billAmount);
        final RadioGroup tipRadioGroup = (RadioGroup) findViewById(R.id.tipGroup);

        final TextView tipTotal = (TextView) findViewById(R.id.tipCount_result);
        final TextView totalPrice = (TextView) findViewById(R.id.totalPrice_result);
        bill.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event){
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER &&
                         !bill.getText().toString().contains("[a-zA-z]"))) {
                    // Perform action on key press
                        double tipPercentage;
                        switch (((RadioGroup)findViewById(R.id.tipGroup)).getCheckedRadioButtonId()){
                            case R.id.ten: tipPercentage = .1;
                                break;
                            case R.id.fifteen: tipPercentage = .15;
                                break;
                            case R.id.eighteen:    tipPercentage = .18;
                                break;
                            case R.id.twenty:  tipPercentage = .2;
                                break;
                            default:    tipPercentage = .2; // Shouldn't happen, but let's assume generosity
                                break;
                        }
                       NumberFormat numformat = NumberFormat.getCurrencyInstance(Locale.US);
                       Double enteredBill = Double.parseDouble(bill.getText().toString());
                       Double tip = enteredBill * tipPercentage;
                       Double price = enteredBill + (enteredBill * tipPercentage);
                       tipTotal.setText(numformat.format(tip));
                       totalPrice.setText(numformat.format(price));
                       return true;
                }
                return false;
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
