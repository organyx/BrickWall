package com.example.aleks.brickwall;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private List<Sensor> availableSensors;
    private int selectedSensor;
    private TextView tvSensorDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        availableSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        tvSensorDescription = (TextView) findViewById(R.id.tvSensorDescription);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensors, menu);
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
        selectedSensor = item.getItemId();
        updateDescriptionTextView();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.clear();
        int count = 0;
        for(Sensor s : availableSensors)
        {
            menu.add(0, count, 0, s.getType() + " " + s.getName());
            count++;
        }
        return true;
    }


    private void updateDescriptionTextView()
    {
        String text = "";
        Sensor sensor = availableSensors.get(selectedSensor);
        text += "Name: " + sensor.getName() + "\n\n";
        text += "Type: " + sensor.getType() + "\n\n";
        text += "Power: " + sensor.getPower()+ "\n\n";
        text += "Range: " + sensor.getMaximumRange() + "\n\n";
        text += "Resolution: " + sensor.getResolution() + "\n\n";

        if(sensor.getMinDelay() == 0)
            text += "Not a streaming sensor";
        else
            text += "Min Delay: " + ((double)sensor.getMinDelay()/1000000.0) + " seconds \n";

        tvSensorDescription.setText(text);

    }
}
