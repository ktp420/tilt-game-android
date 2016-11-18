package org.hitlabnz.sensor_fusion_demo.orientationProvider;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class AccelerometerProvider extends OrientationProvider {
    private float[] accelerometerValues;
    private float[] magnitudeValues;

    public AccelerometerProvider(SensorManager sensorManager) {
        super(sensorManager);
        this.magnitudeValues = new float[]{1.0f, 1.0f, 1.0f};
        this.accelerometerValues = new float[3];
        this.sensorList.add(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            this.accelerometerValues = event.values.clone();
        }
        if (this.accelerometerValues != null) {
            float[] i = new float[16];
            SensorManager.getRotationMatrix(this.currentOrientationRotationMatrix.matrix, i, this.accelerometerValues,
                    this.magnitudeValues);
            this.currentOrientationQuaternion.setRowMajor(this.currentOrientationRotationMatrix.matrix);
        }
    }
}