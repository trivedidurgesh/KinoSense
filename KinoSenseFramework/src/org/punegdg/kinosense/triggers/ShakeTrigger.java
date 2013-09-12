/**
 * Copyright 2012 Pune-GDG (http://meetup.com/pune-gdg)

 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.punegdg.kinosense.triggers;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;
import org.punegdg.kinosense.triggers.framework.SensorBasedTrigger;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This Trigger is for the action when User Shakes the phone
 * 
 * @author "Amruta Deshpande"<deshpande.amruta22@gmail.com>
 */
public class ShakeTrigger implements SensorEventListener, SensorBasedTrigger {

    private float xAccel;
    private float yAccel;
    private float zAccel;

    /* And here the previous ones */
    private float xPreviousAccel;
    private float yPreviousAccel;
    private float zPreviousAccel;

    /* Used to suppress the first shaking */
    private boolean firstUpdate = true;

    /* What acceleration difference would we assume as a rapid movement? */
    private final float shakeThreshold = 1.5f;

    /* Has a shaking motion been started (one direction) */
    private boolean shakeInitiated = false;

    /**
     * Android's Application Context
     */
    private Context context = null;

    /**
     * Android's Sensor Manager
     */
    private SensorManager sensorManager = null;

    public void onCreate(final Context context, final SensorManager sensorManager) {
        this.context = context;
        this.sensorManager = sensorManager;
        sensorManager.registerListener(this.getSensorEventListener(), sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    public SensorEventListener getSensorEventListener() {

        return this;
    }

    public void onDestroy() {
        this.sensorManager.unregisterListener(this.getSensorEventListener());

    }

    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

    }

    public void onSensorChanged(final SensorEvent event) {
        this.updateAccelParameters(event.values[0], event.values[1], event.values[2]);
        if ((!this.shakeInitiated) && this.isAccelerationChanged()) {
            this.shakeInitiated = true;
        } else if ((this.shakeInitiated) && this.isAccelerationChanged()) {
            this.executeShakeAction();
        } else if ((this.shakeInitiated) && (!this.isAccelerationChanged())) {
            this.shakeInitiated = false;
        }

    }

    private void executeShakeAction() {
        Intent intent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);

        // intent.putExtra("trigger", "SHAKING");
        intent.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.DEVICE_SHAKING);
        this.context.sendBroadcast(intent);
    }

    private boolean isAccelerationChanged() {
        float deltaX = Math.abs(this.xPreviousAccel - this.xAccel);
        float deltaY = Math.abs(this.yPreviousAccel - this.yAccel);
        float deltaZ = Math.abs(this.zPreviousAccel - this.zAccel);
        return ((deltaX > this.shakeThreshold) && (deltaY > this.shakeThreshold))
                || ((deltaX > this.shakeThreshold) && (deltaZ > this.shakeThreshold))
                || ((deltaY > this.shakeThreshold) && (deltaZ > this.shakeThreshold));
    }

    private void updateAccelParameters(final float xNewAccel, final float yNewAccel, final float zNewAccel) {

        if (this.firstUpdate) {
            this.xPreviousAccel = xNewAccel;
            this.yPreviousAccel = yNewAccel;
            this.zPreviousAccel = zNewAccel;
            this.firstUpdate = false;
        } else {
            this.xPreviousAccel = this.xAccel;
            this.yPreviousAccel = this.yAccel;
            this.zPreviousAccel = this.zAccel;
        }
        this.xAccel = xNewAccel;
        this.yAccel = yNewAccel;
        this.zAccel = zNewAccel;
    }

}
