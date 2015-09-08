package com.example.aleks.brickwall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String CONSTANT_COLOR = "CONSTANT_COLOR";
    public static final String CONSTANT_COLOR_RETURN = "CONSTANT_COLOR_RETURN";
    public static final int CONSTANT_COLORREQUESTCODE = 42;

    public static final String SAVED_PREFERENCES = "SAVED_PREFERENCES";
    public static final String SAVED_COLOR = "SAVED_COLOR";

    private TextView tvRecordsTitle;
    private TextView tvScore1;
    private TextView tvScore2;
    private TextView tvScore3;
    private TextView tvScore4;
    private TextView tvCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("onCreate_MAIN", "======== onCreate has started. ========");
        tvRecordsTitle = (TextView)findViewById(R.id.tvRecordsTitle);
        tvScore1 = (TextView)findViewById(R.id.tvScore1);
        tvScore2 = (TextView)findViewById(R.id.tvScore2);
        tvScore3 = (TextView)findViewById(R.id.tvScore3);
        tvScore4 = (TextView)findViewById(R.id.tvScore4);
        tvCopy = (TextView)findViewById(R.id.tvCopy);

        Toast.makeText(this, "Current_COLOR: "+String.format("#%06X", (0xFFFFFF & tvRecordsTitle.getCurrentTextColor())), Toast.LENGTH_LONG).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("onActivityResult_MAIN", "======== onActivityResult has started. ========");
        if(requestCode == CONSTANT_COLORREQUESTCODE)
        {
            if(resultCode == RESULT_OK)
            {
                String returnValue  = data.getExtras().getString(CONSTANT_COLOR_RETURN);
                Toast.makeText(this, "Result: "+returnValue, Toast.LENGTH_LONG).show();
                tvRecordsTitle.setTextColor(Color.parseColor(returnValue));
//                tvScore1.setTextColor(Color.parseColor(returnValue));
                saveData();
            }
        }
    }

    public void onBtnInfoClick(View view) {
        Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(aboutIntent);
    }

    public void onBtnSoundClick(View view) {
        Intent colorPickerIntent = new Intent(MainActivity.this, ColorPickerActivity.class);
        startActivityForResult(colorPickerIntent, CONSTANT_COLORREQUESTCODE);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("onResume_MAIN", "======== onResume has started. ========");
        loadData();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("onPause_MAIN", "======== onPause has started. ========");
        saveData();
    }

    private void saveData()
    {
        SharedPreferences prefs = getSharedPreferences(SAVED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        int intColor = tvRecordsTitle.getCurrentTextColor();
        String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
        Log.d("onPause_SavedHexValue", "Hex Value: " + hexColor);
        editor.putString(SAVED_COLOR, hexColor);
        editor.apply();
    }

    private void loadData()
    {
        SharedPreferences prefs = getSharedPreferences(SAVED_PREFERENCES, MODE_PRIVATE);
        String saved_color = prefs.getString(SAVED_COLOR, "#FF0000");
        Log.d("onResume_SavedColor", "Hex Value: " + saved_color);
        tvRecordsTitle.setTextColor(Color.parseColor(saved_color));
        tvScore1.setTextColor(Color.parseColor(saved_color));
        tvScore2.setTextColor(Color.parseColor(saved_color));
        tvScore3.setTextColor(Color.parseColor(saved_color));
        tvScore4.setTextColor(Color.parseColor(saved_color));
        tvCopy.setTextColor(Color.parseColor(saved_color));
        Toast.makeText(this, "Current_COLOR: "+String.format("#%06X", (0xFFFFFF & tvRecordsTitle.getCurrentTextColor())), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("onStart_MAIN", "======== onStart has started. ========");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d("onRestart_MAIN", "======== onRestart has started. ========");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("onStop_MAIN", "======== onStop has started. ========");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("onDestroy_MAIN", "======== onDestroy has started. ========");
    }

    public void onBtnTwClick(View view) {
        Intent webIntent = new Intent(MainActivity.this, WebActivity.class);
        startActivity(webIntent);
    }

    public void onBtnFbClick(View view) {
        Intent mailIntent = new Intent(MainActivity.this, MailActivity.class);
        startActivity(mailIntent);
    }
}
