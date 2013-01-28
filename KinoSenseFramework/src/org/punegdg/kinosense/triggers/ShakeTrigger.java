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
 * 
 */
public class ShakeTrigger implements SensorEventListener,SensorBasedTrigger {

	
	private float xAccel;
	private float yAccel;
	private float zAccel;

	/* And here the previous ones */
	private float xPreviousAccel;
	private float yPreviousAccel;
	private float zPreviousAccel;

	/* Used to suppress the first shaking */
	private boolean firstUpdate = true;

	/*What acceleration difference would we assume as a rapid movement? */
	private final float shakeThreshold = 1.5f;
	
	/* Has a shaking motion been started (one direction) */
	private boolean shakeInitiated = false;

	
	/**
	 * Android's Application Context
	 */
	private Context context=null;


	/**
	 * Android's Sensor Manager
	 */
	private SensorManager sensorManager=null;


  


	public void onCreate(Context context, SensorManager sensorManager) {
		
		this.context = context;
		this.sensorManager = sensorManager;
		sensorManager.registerListener(this.getSensorEventListener(),
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

		
	}

	public SensorEventListener getSensorEventListener() {
		
		return this;
	}

	public void onDestroy() {
		sensorManager.unregisterListener(getSensorEventListener());
		
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
		
	}

	public void onSensorChanged(SensorEvent event) {
		updateAccelParameters(event.values[0],event.values[1], event.values[2]);   
        if ((!shakeInitiated) && isAccelerationChanged()) {                                      
	    shakeInitiated = true; 
	} else if ((shakeInitiated) && isAccelerationChanged()) {                              
	    executeShakeAction();
	} else if ((shakeInitiated) && (!isAccelerationChanged())) {                           
	    shakeInitiated = false;
	}
		
	}

	private void executeShakeAction() {
		Intent intent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);

		intent.putExtra("trigger", "SHAKING");
		context.sendBroadcast(intent);
	}

	private boolean isAccelerationChanged() {
		float deltaX = Math.abs(xPreviousAccel - xAccel);
		float deltaY = Math.abs(yPreviousAccel - yAccel);
		float deltaZ = Math.abs(zPreviousAccel - zAccel);
		return (deltaX > shakeThreshold && deltaY > shakeThreshold)
				|| (deltaX > shakeThreshold && deltaZ > shakeThreshold)
				|| (deltaY > shakeThreshold && deltaZ > shakeThreshold);
	}

	private void updateAccelParameters(float xNewAccel, float  yNewAccel, float zNewAccel) {
		
		if (firstUpdate) {  
		xPreviousAccel = xNewAccel;
		yPreviousAccel = yNewAccel;
		zPreviousAccel = zNewAccel;
		firstUpdate = false;
	} else {
		xPreviousAccel = xAccel;
		yPreviousAccel = yAccel;
		zPreviousAccel = zAccel;
	}
	xAccel = xNewAccel;
	yAccel = yNewAccel;
	zAccel = zNewAccel;
    }
		
	}
	