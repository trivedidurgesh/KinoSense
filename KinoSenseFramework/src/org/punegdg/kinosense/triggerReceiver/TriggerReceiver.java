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
import org.punegdg.kinosense.actions.SilentAction;
import org.punegdg.kinosense.actions.SmsAction;
import org.punegdg.kinosense.actions.VibrateAction;
import org.punegdg.kinosense.actions.WifiAction;
import org.punegdg.kinosense.eventsource.SensorService;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Receives all the Kino Sense Triggers and runs the rules to invoke the corresponding actions
 * 
 * @author "Rohit Ghatol" <rohitsghatol@gmail.com>
 * 
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
		// -----------------------------

		String trigger = intent.getStringExtra("trigger");

		Map<String, Object> alarmData = new HashMap<String, Object>();
		// Map data object for Alarm Action

		if ( "FLIPPED_DOWN".equals(trigger) )
		{
			this.vibrateAction.perform(null);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("action", "Silence");
			this.silentAction.perform(data);

			Map<String, Object> wifiData = new HashMap<String, Object>();
			// Map data object for Wifi Action
			wifiData.put("wifiaction", "WIFI_OFF");
			// this.wifiAction.perform(wifiData);

			alarmData.put("alarmAction", "Off");
			// this.alarmAction.perform(alarmData);

			Map<String, Object> flightmodeData = new HashMap<String, Object>();
			// Map data object for Flight Mode Action
			flightmodeData.put("flightmode", "ON");
			this.flightaction.perform(flightmodeData);

		}
		else if ( "FLIPPED_UP".equals(trigger) )
		{
			this.vibrateAction.perform(null);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("action", "Restore");
			this.silentAction.perform(data);

			this.vibrateAction.perform(null);
			Map<String, Object> flightmodeData = new HashMap<String, Object>();
			// Map data object for Flight Mode Action
			flightmodeData.put("flightmode", "OFF");
			this.flightaction.perform(flightmodeData);
		}

		if ( "POWER_CONNECTED".equals(trigger) )
		{
			this.vibrateAction.perform(null);

			alarmData.put("alarmAction", "On");
			// this.alarmAction.perform(alarmData);

			Map<String, Object> brightnessData = new HashMap<String, Object>();
			brightnessData.put("brightnessAction", "LOW");
			this.brightnessAction.perform(brightnessData);
		}

		else if ( "POWER_DISCONNECTED".equals(trigger) )
		{
			Map<String, Object> brightnessData = new HashMap<String, Object>();
			brightnessData.put("brightnessAction", "HIGH");
			this.brightnessAction.perform(brightnessData);

			Map<String, Object> smsData = new HashMap<String, Object>();
			// Map data object for SMS Action
			smsData.put("action", "IsBusy");
			smsData.put("number", "9860868444");
			// FIXME Phone Number to be Changed later on
			this.smsAction.perform(smsData);

		}

		if ( "WIFI_FOUND".equals(trigger) )
		{
			String wifiData = intent.getStringExtra("wifiData");
			Toast.makeText(context, wifiData, Toast.LENGTH_LONG).show();
		}
		if ( "SIMCARD_CHANGED".equals(trigger) )
		{
			Toast.makeText(context, "Sim Changed", Toast.LENGTH_LONG).show();
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage("8149373415", null, "Sim Card Changed", null, null);
			this.vibrateAction.perform(null);
		}
		else if ( "SIMCARD_UNCHANGED".equals(trigger) )
		{
			Toast.makeText(context, "Sim UnChanged", Toast.LENGTH_LONG).show();
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage("8149373415", null, "Sim Card UNChanged", null, null);
			this.vibrateAction.perform(null);
		}
		if ( "PHONE_UNLOCKED".equals(trigger) )
		{
			Toast.makeText(context, "Phone Unlocked", Toast.LENGTH_LONG).show();
			this.vibrateAction.perform(null);
		}

		if ( "BATTERY_LOW".equals(trigger) )
		{
			Toast.makeText(context, "Battery Low", Toast.LENGTH_LONG).show();
			this.vibrateAction.perform(null);
		}
		else if ( "BATTERY_OKAY".equals(trigger) )
		{
			Toast.makeText(context, "Battery Okay", Toast.LENGTH_LONG).show();
			this.vibrateAction.perform(null);
		}

		if ( "HEADSET_CONNECTED".equals(trigger) )
		{
			Toast.makeText(context, "Headset Connected", Toast.LENGTH_SHORT).show();
			this.vibrateAction.perform(null);
		}
		else if ( "HEADSET_DISCONNECTED".equals(trigger) )
		{
			Toast.makeText(context, "Headset Disconnected", Toast.LENGTH_SHORT).show();
			this.vibrateAction.perform(null);
		}
		if ( "SHAKING".equals(trigger) )
		{
			Toast.makeText(context, "SHAKING STARTED", Toast.LENGTH_LONG).show();
			this.vibrateAction.perform(null);
		}
	}

}
