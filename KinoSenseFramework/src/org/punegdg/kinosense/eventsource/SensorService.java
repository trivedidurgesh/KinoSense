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
package org.punegdg.kinosense.eventsource;

import org.punegdg.kinosense.triggers.BatteryTrigger;
import org.punegdg.kinosense.triggers.FlipTrigger;
import org.punegdg.kinosense.triggers.HeadphoneTrigger;
import org.punegdg.kinosense.triggers.IncomingCallTrigger;
import org.punegdg.kinosense.triggers.PowerConnectedTrigger;
import org.punegdg.kinosense.triggers.ShakeTrigger;
import org.punegdg.kinosense.triggers.SimCardChangedTrigger;
import org.punegdg.kinosense.triggers.UnlockTrigger;
import org.punegdg.kinosense.triggers.WifiTrigger;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;
import org.punegdg.kinosense.triggers.framework.SensorBasedTrigger;

import android.app.Service;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

/**
 * Sensor Listener Service. Listens to various sensor and delegates to SensorBasedTrigger for processing the sensor
 * events
 * 
 * @author "Rohit Ghatol"<rohitsghatol@gmail.com>
 * 
 */
public class SensorService extends Service
{

	/**
	 * Android's Sensor Manager
	 */
	private SensorManager sensorMgr = null;

	/**
	 * The Trigger to handle the low level sensor events.
	 */

	private SensorBasedTrigger flipTrigger = new FlipTrigger();
	private SensorBasedTrigger shakeTrigger = new ShakeTrigger();

	private BroadCastReceiverBasedTrigger batteryTrigger = new BatteryTrigger();
	private BroadCastReceiverBasedTrigger headphoneTrigger = new HeadphoneTrigger();
	private BroadCastReceiverBasedTrigger powerConnectedTrigger = new PowerConnectedTrigger();
	private BroadCastReceiverBasedTrigger simcardChangedTrigger = new SimCardChangedTrigger();
	private BroadCastReceiverBasedTrigger unlockTrigger = new UnlockTrigger();
	private BroadCastReceiverBasedTrigger wifiTrigger = new WifiTrigger();
	private BroadCastReceiverBasedTrigger callTrigger = new IncomingCallTrigger();


	/*
	 * (non-Javadoc)
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.d("SensorService", "Service Started");
		this.sensorMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);

		this.flipTrigger.onCreate(this.getApplicationContext(), this.sensorMgr);
		this.shakeTrigger.onCreate(this.getApplicationContext(), this.sensorMgr);
		this.batteryTrigger.onCreate(this.getApplicationContext());
		this.headphoneTrigger.onCreate(this.getApplicationContext());
		this.powerConnectedTrigger.onCreate(this.getApplicationContext());
		this.simcardChangedTrigger.onCreate(this.getApplicationContext());
		this.unlockTrigger.onCreate(this.getApplicationContext());
		this.wifiTrigger.onCreate(this.getApplicationContext());
		this.callTrigger.onCreate(this.getApplicationContext());
	}


	/*
	 * (non-Javadoc)
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("SensorService", "Service Destroyed");
		this.flipTrigger.onDestroy();
		this.shakeTrigger.onDestroy();
		this.batteryTrigger.onDestroy();
		this.headphoneTrigger.onDestroy();
		this.powerConnectedTrigger.onDestroy();
		this.simcardChangedTrigger.onDestroy();
		this.unlockTrigger.onDestroy();
		this.wifiTrigger.onDestroy();
		this.callTrigger.onDestroy();
	}


	/*
	 * (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
