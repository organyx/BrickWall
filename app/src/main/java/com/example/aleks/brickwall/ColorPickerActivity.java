package com.example.aleks.brickwall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ColorPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        Bundle myBundle = getIntent().getExtras();
        if(myBundle!=null && myBundle.containsKey(MainActivity.CONSTANT_COLOR))
        {
            int color = myBundle.getInt(MainActivity.CONSTANT_COLOR);
            Toast.makeText(this, "Color: "+color, Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "No Color", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
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

    public void onBtnRedClick(View view) {
        boolean good = true;
        if (good)
        {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.CONSTANT_COLOR_RETURN, "#FF0000");
            Log.d("COLOR_PICKER", "RETURN COLOR btnRed " + returnIntent.getStringExtra(MainActivity.CONSTANT_COLOR_RETURN));
            setResult(RESULT_OK, returnIntent);
        }
        else
        {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
        }
        finish();
    }

    public void onBtnGreenClick(View view) {
        boolean good = true;
        if (good)
        {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.CONSTANT_COLOR_RETURN, "#33CC33");
            Log.d("COLOR_PICKER", "RETURN COLOR btnGreen " + returnIntent.getStringExtra(MainActivity.CONSTANT_COLOR_RETURN));
            setResult(RESULT_OK, returnIntent);
        }
        else
        {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
        }
        finish();
    }

    public void onBtnBlueClick(View view) {
        boolean good = true;
        if (good)
        {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.CONSTANT_COLOR_RETURN, "#0099FF");
            Log.d("COLOR_PICKER", "RETURN COLOR btnBlue " + returnIntent.getStringExtra(MainActivity.CONSTANT_COLOR_RETURN));
            setResult(RESULT_OK, returnIntent);
        }
        else
        {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
        }
        finish();
    }


}
