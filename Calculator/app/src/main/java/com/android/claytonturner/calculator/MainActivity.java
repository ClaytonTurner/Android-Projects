package com.android.claytonturner.calculator;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    /*
    We need to be able to orient the screen on start of the application
    This allows us to iterate over the buttons and set attributes
    according to landscape or portrait by referencing these IDs and
    we can use the "is_portrait" variable described in layouts-land/port.xml
     */
    private CalculatorEngine engine;
    private static final String serialized_name = "calc_serial.bin";
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
        boolean isPortrait = getResources().getBoolean(R.bool.is_portrait);
        if(isPortrait)
            setContentView(R.layout.activity_main_portrait);
        else
            setContentView(R.layout.activity_main_landscape);
        final EditText result = (EditText) findViewById(R.id.Result);
        Button temp_button;
        int resultValue = 0;
        final List ops = Arrays.asList("+", "-", "/", "*");
        if(savedInstanceState != null){ // restore state
            try {
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(
                                new File(getApplicationContext().getFilesDir(),serialized_name)
                        )
                );
                engine = (CalculatorEngine) ois.readObject();
            }
            catch(Exception e){
                Log.v("Serialization loading error: ", e.getMessage());
            }

        }
        else{ // initial creation
            engine = new CalculatorEngine();
            engine.insert("0".charAt(0));
        }

        result.setText(String.valueOf(resultValue));

        // setting text sizes
        if(isPortrait){
            for(int buttonId : Buttons){
                temp_button = (Button) findViewById(buttonId);
                temp_button.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.portraitText));
            }
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.portraitText));
        }
        else{//isLandscape
            for(int buttonId : Buttons){
                temp_button = (Button) findViewById(buttonId);
                temp_button.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.landscapeText));
            }
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP,getResources().getInteger(R.integer.landscapeText));
        }

        // setting event listeners
        for(int buttonId : Buttons){
            temp_button = (Button) findViewById(buttonId);
            temp_button.setOnClickListener(new View.OnClickListener(){
               public void onClick(View v){
                   Button b = (Button) v;
                   String buttonText = b.getText().toString();
                   if(buttonText.matches("\\d|\\.")) {//if a digit or decimal
                       if(result.getText().toString().equals("0"))
                            result.setText(buttonText);
                       else
                            result.setText(result.getText()+buttonText);
                       engine.insert(buttonText.charAt(0));
                   }
                   else if(buttonText.equals("C")){
                       engine.clear();
                       result.setText("0");
                   }
                   else if(buttonText.equals("CE")){
                       engine.clearEntry();
                       result.setText("0");
                   }
                   else if(buttonText.equals("+/-")){
                       engine.toggleSign();
                       result.setText(engine.getDisplay());
                   }
                   else if(ops.contains(buttonText)){
                       /*
                       No specification given for behavior for ops in regard to...
                       text when an operation is hit.
                       Defaulting to setting to zero so
                       the program reflects that it is actually reacting to the user
                        */
                       result.setText("0");
                       if(buttonText.equals("+")){
                           engine.perform(Operation.ADD);
                       }
                       else if(buttonText.equals("-")){
                           engine.perform(Operation.SUBTRACT);
                       }
                       else if(buttonText.equals("/")){
                           engine.perform(Operation.DIVIDE);
                       }
                       else if(buttonText.equals("*")){
                           engine.perform(Operation.MULTIPLY);
                       }
                       else{
                           Log.wtf("wtf - ops","Reached onclick in ops but found no value");
                       }
                   }
                   else if(buttonText.equals("=")){
                       engine.perform(Operation.EQUALS);
                       result.setText(engine.getDisplay());
                   }
                   else{
                       Log.wtf("wtf - onclick","Button clicked that isn't programmed");
                   }
               }
            });
        }
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(
                            new File(getApplicationContext().getFilesDir(),serialized_name)
                    )
            );
            oos.writeObject(engine);
            oos.close();
        }
        catch(Exception e){
            Log.v("Serialization saving error: ", e.getMessage());
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
