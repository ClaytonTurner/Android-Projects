package com.android.claytonturner.database;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;


public class MainActivity extends ListActivity {
    Database_Sqliteopenhelper dbHelper = new Database_Sqliteopenhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = dbHelper.getReadableDatabase(); // change to writeable maybe
        displayListview(db);
        }

    private void displayListview(SQLiteDatabase db){
        final ListView listView = (ListView) findViewById(android.R.id.list);
        try {
            Cursor cursor = dbHelper.fetchAllBars(db);
            String[] from = new String[]{
                    Database_Sqliteopenhelper.key_name,
                    Database_Sqliteopenhelper.key_address,
                    Database_Sqliteopenhelper.key_phone
            };
            int[] to = new int[]{
                    R.id.name,
                    R.id.address,
                    R.id.phone_num
            };
            SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                    this, R.layout.row_layout, cursor, from, to, 0
            );
            listView.setAdapter(dataAdapter);
        }
        catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Log.e(this.getResources().getString(R.string.error),
                    this.getResources().getString(R.string.adapterError)
                            + "\n" + sw.toString());
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
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
