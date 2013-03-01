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
package org.punegdg.kinosense.triggerReceiver;

import java.util.HashMap;
import java.util.Map;

import org.punegdg.kinosense.actions.AbstractAction;
import org.punegdg.kinosense.actions.AlarmAction;
import org.punegdg.kinosense.actions.BrightnessAction;
import org.punegdg.kinosense.actions.FlightModeAction;
import org.punegdg.kinosense.actions.MusicAction;
import org.punegdg.kinosense.actions.NotificationAction;
import org.punegdg.kinosense.actions.SilentAction;
import org.punegdg.kinosense.actions.SmsAction;
import org.punegdg.kinosense.actions.VibrateAction;
import org.punegdg.kinosense.actions.WifiAction;
import org.punegdg.kinosense.eventsource.SensorService;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Receives all the Kino Sense Triggers and runs the rules to invoke the corresponding actions
 * 
 * @author "Rohit Ghatol" <rohitsghatol@gmail.com>
 * @author "Ashish Kalbhor" <ashish.kalbhor@gmail.com>
 */
public class TriggerReceiver extends BroadcastReceiver
{
	public static String ACTION_KINOSENSE_TRIGGER = "org.punegdg.kinosense.TRIGGER";

	/**
	 * Silent Action
	 */
	private AbstractAction silentAction = new SilentAction();

	/**
	 * Vibrate Action
	 */
	private AbstractAction vibrateAction = new VibrateAction();

	/**
	 * Wifi Action
	 */

	private AbstractAction wifiAction = new WifiAction();

	/**
	 * Flight Mode Action
	 */
	private AbstractAction flightaction = new FlightModeAction();

	/**
	 * Alarm Action
	 * */
	private AbstractAction alarmAction = new AlarmAction();

	/**
	 * SMS Action
	 */
	private AbstractAction smsAction = new SmsAction();

	/**
	 * Brightness Action
	 */
	private AbstractAction brightnessAction = new BrightnessAction();

	/**
	 * Notification Action
	 */
	private AbstractAction notifAction = new NotificationAction();


	/*
	 * (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent)
	{
		if ( intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED) )
		{
			Intent bootIntent = new Intent(context, SensorService.class);
			context.startService(bootIntent);
		}

		// -----------------------------
		// FIXME Fix the following
		// -----------------------------
		this.silentAction.onCreate(context);
		this.vibrateAction.onCreate(context);
		this.wifiAction.onCreate(context);
		this.flightaction.onCreate(context);
		this.alarmAction.onCreate(context);
		this.smsAction.onCreate(context);
		this.brightnessAction.onCreate(context);
		this.notifAction.onCreate(context);
		// -----------------------------

		String trigger = intent.getStringExtra("trigger");

		if ( "FLIPPED_DOWN".equals(trigger) )// Silent ON
		{
			Map<String, Object> silentData = new HashMap<String, Object>();
			silentData.put("action", "Silence");
			this.silentAction.perform(silentData);
			this.vibrateAction.perform(null);
		}
		if ( "FLIPPED_UP".equals(trigger) )// Silent OFF
		{
			Map<String, Object> silentData = new HashMap<String, Object>();
			silentData.put("action", "Restore");
			this.silentAction.perform(silentData);
		}
		if ( "POWER_CONNECTED".equals(trigger) )// Display Brightness Full
		{
			Map<String, Object> brightnessData = new HashMap<String, Object>();
			brightnessData.put("action", "HIGH");
			this.brightnessAction.perform(brightnessData);
		}
		if ( "POWER_DISCONNECTED".equals(trigger) )// Display Brightness Low
		{
			Map<String, Object> brightnessData = new HashMap<String, Object>();
			brightnessData.put("action", "LOW");
			this.brightnessAction.perform(brightnessData);
		}
		if ( "WIFI_FOUND".equals(trigger) )// Toast of SSID
		{
			String wifiInfo = intent.getStringExtra("wifiData");
			String[] InfoParts = wifiInfo.split(",");
			wifiInfo = InfoParts[0];
			//Showing only SSID
			Toast.makeText(context, wifiInfo, Toast.LENGTH_LONG).show();
		}
		if ( "SIMCARD_CHANGED".equals(trigger) )// Send SMS
		{
			Map<String, Object> smsData = new HashMap<String, Object>();
			smsData.put("action", "Sim Card Changed !");
			smsData.put("number", "8179373415");
			// FIXME Phone Number to be set through UI
			this.smsAction.perform(smsData);
		}
		if ( "PHONE_UNLOCKED".equals(trigger) )// Turn On WIFI
		{
			Map<String, Object> wifiData = new HashMap<String, Object>();
			wifiData.put("action", "ON");
			this.wifiAction.perform(wifiData);
		}
		if ( "PHONE_LOCKED".equals(trigger) )// Turn Off WIFI
		{
			Map<String, Object> wifiData = new HashMap<String, Object>();
			wifiData.put("action", "OFF");
			this.wifiAction.perform(wifiData);
		}
		if ( "BATTERY_LOW".equals(trigger) )// Notify with Brightness Low, Wifi Off
		{
			Map<String, Object> wifiData = new HashMap<String, Object>();
			Map<String, Object> brightnessData = new HashMap<String, Object>();
			Map<String, Object> notifyData = new HashMap<String, Object>();
			notifyData.put("message", "Low Battery ! Wifi has been Turned Off");
			this.notifAction.perform(notifyData);
			brightnessData.put("action", "LOW");
			this.brightnessAction.perform(brightnessData);
			wifiData.put("action", "OFF");
			this.wifiAction.perform(wifiData);
		}
		if ( "BATTERY_FULL".equals(trigger) )// Notify with Brightness High,Wifi On
		{
			Map<String, Object> wifiData = new HashMap<String, Object>();
			Map<String, Object> brightnessData = new HashMap<String, Object>();
			Map<String, Object> notifyData = new HashMap<String, Object>();
			notifyData.put("message", "Battery Level Restored !");
			this.notifAction.perform(notifyData);
			brightnessData.put("action", "HIGH");
			this.brightnessAction.perform(brightnessData);
			wifiData.put("action", "ON");
			this.wifiAction.perform(wifiData);
		}
		if ( "HEADSET_CONNECTED".equals(trigger) )// Start Music
		{
			context.startService(new Intent(context, MusicAction.class));
		}
		if ( "HEADSET_DISCONNECTED".equals(trigger) )// Stop Music
		{
			context.stopService(new Intent(context, MusicAction.class));
		}
		if ( "SHAKING".equals(trigger) )// Flight Mode Toggle
		{
			Map<String, Object> flightmodeData = new HashMap<String, Object>();
			flightmodeData.put("action", "ON");
			this.flightaction.perform(flightmodeData);
		}
		if ( "INCOMING_CALL".equals(trigger) )// Music Pause, Notify
		{
			Map<String, Object> notifyData = new HashMap<String, Object>();
			context.stopService(new Intent(context, MusicAction.class));
			String mNumber = intent.getStringExtra("number");
			notifyData.put("message", "Incoming Call from " + mNumber);
			this.notifAction.perform(notifyData);
		}

	}

}
