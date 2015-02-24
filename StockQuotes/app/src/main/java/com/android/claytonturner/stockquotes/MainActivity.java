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
        boolean isPortrait = getResources().getBoolean(R.bool.is_portrait);
        if(isPortrait)
            setContentView(R.layout.activity_main_portrait);
        else
            setContentView(R.layout.activity_main_landscape);

        // Let's restore state if we need to
        if(savedInstanceState != null){
            TextView symbol = (TextView) findViewById(R.id.symbol_result);
            TextView name = (TextView) findViewById(R.id.name_result);
            TextView lastTradePrice = (TextView) findViewById(R.id.lastTradePrice_result);
            TextView lastTradeTime = (TextView) findViewById(R.id.lastTradeTime_result);
            TextView change = (TextView) findViewById(R.id.change_result);
            TextView yearRange = (TextView) findViewById(R.id.yearRange_result);
            symbol.setText(savedInstanceState.getString("symbol"));
            name.setText(savedInstanceState.getString("name"));
            lastTradePrice.setText(savedInstanceState.getString("lastTradePrice"));
            lastTradeTime.setText(savedInstanceState.getString("lastTradeTime"));
            change.setText(savedInstanceState.getString("change"));
            yearRange.setText(savedInstanceState.getString("yearRange"));
        }

        final EditText stockText = (EditText) findViewById(R.id.stock_prompt_edit_text);
        stockText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                class RetrieveStock extends AsyncTask<String, Void, Stock>{
                    protected Stock doInBackground(String... symbol) {
                        if(symbol[0].contains(" "))
                            return new Stock("N/A");
                        Stock stock = new Stock(symbol[0]);
                        try {
                            stock.load();
                            return stock;
                        }
                        catch(Exception e){
                            Log.wtf("Yahoo Error","Error loading Yahoo Finance: " + e.getMessage());
                            return new Stock("N/A");
                        }
                    }
                    @Override
                    protected void onPostExecute(Stock stock){
                        // Check if the symbol sent was malformed
                        String error = "";
                        if(stock.getSymbol().equals("N/A")){
                            error = "Error in Stock name - perhaps malformed";
                            stock.clearFields();
                        }
                        // Check if the symbol was fine, but didn't have a match
                        else if(stock.getLastTradeTime().equals("N/A")){
                            error = "Error retrieving stock information - check the name";
                            stock.clearFields();
                        }

                        TextView symbol = (TextView) findViewById(R.id.symbol_result);
                        TextView name = (TextView) findViewById(R.id.name_result);
                        TextView lastTradePrice = (TextView) findViewById(R.id.lastTradePrice_result);
                        TextView lastTradeTime = (TextView) findViewById(R.id.lastTradeTime_result);
                        TextView change = (TextView) findViewById(R.id.change_result);
                        TextView yearRange = (TextView) findViewById(R.id.yearRange_result);
                        symbol.setText(((Stock) stock).getSymbol());
                        name.setText(((Stock) stock).getName());
                        lastTradePrice.setText(((Stock) stock).getLastTradePrice());
                        lastTradeTime.setText(((Stock) stock).getLastTradeTime());
                        change.setText(((Stock) stock).getChange());
                        yearRange.setText(((Stock) stock).getRange());
                    if(!error.equals(""))
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                    }
                }
                String stockName = stockText.getText().toString();
                if(!stockName.equals("")){
                    RetrieveStock stockToRetrieve = new RetrieveStock();
                    stockToRetrieve.execute(stockName);
                }
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // Can use .toString() or cast to String - both work
        outState.putString("symbol",((TextView)findViewById(R.id.symbol_result)).getText().toString());
        outState.putString("lastTradeTime",((TextView)findViewById(R.id.lastTradeTime_result)).getText().toString());
        outState.putString("lastTradePrice",((TextView)findViewById(R.id.lastTradePrice_result)).getText().toString());
        outState.putString("change",((TextView)findViewById(R.id.change_result)).getText().toString());
        outState.putString("yearRange",((TextView)findViewById(R.id.yearRange_result)).getText().toString());
        outState.putString("name",((TextView)findViewById(R.id.name_result)).getText().toString());
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
