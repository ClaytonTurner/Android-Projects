package com.android.claytonturner.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {
    /*
    We need to be able to orient the screen on start of the application
    This allows us to iterate over the buttons and set attributes
    according to landscape or portrait by referencing these IDs and
    we can use the "is_portrait" variable described in layouts-land/port.xml
     */
    private static final int[] Buttons = {
        R.id.button0,
        R.id.button1,
        R.id.button2,
        R.id.button3,
        R.id.button4,
        R.id.button5,
        R.id.button6,
        R.id.button7,
        R.id.button8,
        R.id.button9,
        R.id.buttonC,
        R.id.buttonCE,
        R.id.buttonPlusMinus,
        R.id.buttonDivide,
        R.id.buttonPlus,
        R.id.buttonMinus,
        R.id.buttonMultiply,
        R.id.buttonDecimal,
        R.id.buttonEquals
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText result = (EditText) findViewById(R.id.Result);
        boolean isPortrait = getResources().getBoolean(R.bool.is_portrait);
        Button temp_button;
        int resultValue;
        if(savedInstanceState != null){ // restore state
            resultValue = 0;//change this
        }
        else{ // initial creation
            resultValue = 0;
        }

        result.setText(String.valueOf(resultValue));

        if(isPortrait){
            //System.out.println("portrait");
            for(int buttonId : Buttons){
                temp_button = (Button) findViewById(buttonId);
                temp_button.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.portraitText));
            }
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.portraitText));
        }
        else{//isLandscape
            //System.out.println("landscape");
            for(int buttonId : Buttons){
                temp_button = (Button) findViewById(buttonId);
                temp_button.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.landscapeText));
            }
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.landscapeText));
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
