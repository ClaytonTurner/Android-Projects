package com.android.claytonturner.database;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;


public class MainActivity extends ListActivity {
    Database_Sqliteopenhelper dbHelper = new Database_Sqliteopenhelper(this);
    String phone;
    String address;
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
            registerForContextMenu(listView);
            //openContextMenu(view);
            //unregisterForContextMenu(view);
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
                address = ((TextView) view.findViewById(R.id.address)).getText().toString();
                phone = ((TextView) view.findViewById(R.id.phone_num)).getText().toString();
                openContextMenu(listView);
                /*Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position +"\n"+address+"\n"+phone, Toast.LENGTH_LONG)
                        .show();*/
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Action");
        menu.add(0, v.getId(), 0, "Call");
        menu.add(0, v.getId(), 0, "Find in Maps");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(phone.equals("")){
            return true; // sanity check
        }
        try{
            if(item.getTitle()=="Call"){
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }
            else if(item.getTitle()=="Find in Maps") {
                Intent mapsIntent = new Intent(Intent.ACTION_VIEW);
                String uriAddress = address.replaceAll(" ","+");
                mapsIntent.setType("geo:0,0?q="+uriAddress);
                /*Toast.makeText(getApplicationContext(),
                        uriAddress, Toast.LENGTH_LONG)
                        .show();*/
                startActivity(mapsIntent);
            }
            else
                return false;
            return true;
        }
        catch(Exception e){
            return true;
        }
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
