package com.example.sensor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;

    private Sensor mSensorAmbient;
    private Sensor mSensorPreassure;
    private Sensor mSensorMagnetic;
    private Sensor mSensorHumidity;


    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;

    private TextView mTextSensorAmbient;
    private TextView mTextSensorPreassure;
    private TextView mTextSensorMagnetic;
    private TextView mTextSensorHumidity;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : sensorList){
            sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
        }
        TextView sensorTextview = findViewById(R.id.sensor_list);
        sensorTextview.setText(sensorText);

        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);

        mTextSensorAmbient = findViewById(R.id.label_ambient);
        mTextSensorPreassure = findViewById(R.id.label_preassure);
        mTextSensorMagnetic = findViewById(R.id.label_magnetic);
        mTextSensorHumidity = findViewById(R.id.label_humidity);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        mSensorAmbient = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorPreassure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorMagnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        String sensorerror = "no sensor";
        if (mSensorLight == null){
            mTextSensorLight.setText(sensorerror);
        }
        if (mSensorProximity == null){
            mTextSensorProximity.setText(sensorerror);
        }

        if (mSensorAmbient == null){
            mTextSensorAmbient.setText(sensorerror);
        }
        if (mSensorPreassure == null){
            mTextSensorPreassure.setText(sensorerror);
        }
        if (mSensorMagnetic == null){
            mTextSensorMagnetic.setText(sensorerror);
        }
        if (mSensorHumidity == null){
            mTextSensorHumidity.setText(sensorerror);
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorProximity != null){
            mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null){
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mSensorAmbient != null){
            mSensorManager.registerListener(this, mSensorAmbient, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPreassure != null){
            mSensorManager.registerListener(this, mSensorPreassure, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorMagnetic != null){
            mSensorManager.registerListener(this, mSensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorHumidity != null){
            mSensorManager.registerListener(this, mSensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int sensorType = event.sensor.getType();
        float currentValue = event.values[0];

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                changeBackgroundColor(currentValue);

                mTextSensorLight.setText(String.format("Light sensor: %s", currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(String.format("Proximity sensor: %s", currentValue));
                break;


            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTextSensorAmbient.setText(String.format("Ambient sensor: %s", currentValue));
                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPreassure.setText(String.format("Preassure sensor: %s", currentValue));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mTextSensorMagnetic.setText(String.format("Magnetic sensor: %s", currentValue));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorHumidity.setText(String.format("Humidity sensor: %s", currentValue));
                break;

            default:
                //do nothing
        }
    }

    private void changeBackgroundColor(float currentValue) {
        ConstraintLayout layout = findViewById(R.id.layout_constraint);
        if (currentValue <= 40000 && currentValue >= 20000) layout.setBackgroundColor(Color.RED);
        else if (currentValue < 20000 && currentValue > 10) layout.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

