package com.android.claytonturner.buttoncounter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements SensorEventListener{
    private int counter;
    //sensor detection
    //adapted from: http://androidcodeexamples.blogspot.com/2011/12/shake-listener-demoexample.html
    private SensorManager sensor_manager;
    private long lastUpdate;
    Boolean bool = false, bool1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sensor
        sensor_manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor_manager.registerListener(this,sensor_manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        lastUpdate = System.currentTimeMillis();

        //typical behavior
        Button count_button = (Button) findViewById(R.id.count_button);
        Button clear_button = (Button) findViewById(R.id.clear_button);
        final TextView text = (TextView) findViewById(R.id.textView);
        if(savedInstanceState != null){
            counter = savedInstanceState.getInt("count");
        }
        else{
            counter = 0;
        }
        text.setText(String.valueOf(counter));

        count_button.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               counter++;
               text.setText(String.valueOf(counter));
           }
        });
        clear_button.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               counter = 0;
               text.setText(String.valueOf(counter));
           }
        });
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("count", counter);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final TextView text = (TextView) findViewById(R.id.textView);
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            // Movement
            float x = values[0];
            float y = values[1];
            float z = values[2];

            float accsqr = (x * x + y * y + z * z)
                    / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            //debug statement
            //System.out.println("accsqr is:- " + accsqr);
            long actualTime = System.currentTimeMillis();
            if (accsqr >= 5) //
            {
                bool1 = false;
                if (bool == false) {
                    if (actualTime - lastUpdate < 1000) {
                        return;
                    }
                    lastUpdate = actualTime;
                    //Here Define Method for OnStartShaking
                    counter = 0;
                    text.setText(String.valueOf(counter));
                    bool = true;
                }
            } else {
                bool = false;
                if (bool1 == false) {
                    if (actualTime - lastUpdate < 4000) {
                        return;
                    }
                    lastUpdate = actualTime;
                    //Here Define Method for OnStopShaking

                    bool1 = true;
                }
            }
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

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
