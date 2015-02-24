package com.android.claytonturner.stockquotes;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText stockText = (EditText) findViewById(R.id.stock_prompt_edit_text);
        stockText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                class RetrieveStock extends AsyncTask<String, Void, Stock> {
                    protected Stock doInBackground(String... symbols){
                        Stock stock = new Stock(symbols[0]);
                        try {
                            stock.load();
                            return stock;
                        }
                        catch(Exception e){
                            Log.e("Loading error: ","Error loading stock" + e.getMessage());
                            return null;
                        }
                    }
                    protected void onPostExecute(Stock stock){
                        TextView symbol = (TextView) findViewById(R.id.symbol_result);
                        TextView name = (TextView) findViewById(R.id.name_result);
                        TextView lastTradePrice = (TextView) findViewById(R.id.lastTradePrice_result);
                        TextView lastTradeTime = (TextView) findViewById(R.id.lastTradeTime_result);
                        TextView change = (TextView) findViewById(R.id.change_result);
                        TextView yearRange = (TextView) findViewById(R.id.yearRange_result);
                        symbol.setText(stock.getSymbol());
                        name.setText(stock.getName());
                        lastTradePrice.setText(stock.getLastTradePrice());
                        lastTradeTime.setText(stock.getLastTradeTime());
                        change.setText(stock.getChange());
                        yearRange.setText(stock.getRange());
                    }
                }
                String stockName = stockText.getText().toString();
                if(!stockName.equals("")){
                    RetrieveStock stockToRetrieve = new RetrieveStock();
                    stockToRetrieve.execute(stockName);
                    Toast.makeText(getApplicationContext(), stockName, Toast.LENGTH_LONG).show();
                }
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
