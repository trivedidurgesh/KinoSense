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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.punegdg.kinosense.actions.AbstractAction;
import org.punegdg.kinosense.actions.ActionIdConstants;
import org.punegdg.kinosense.actions.BrightnessAction;
import org.punegdg.kinosense.actions.FlightModeAction;
import org.punegdg.kinosense.actions.NotificationAction;
import org.punegdg.kinosense.actions.SilentAction;
import org.punegdg.kinosense.actions.SmsAction;
import org.punegdg.kinosense.actions.VibrateAction;
import org.punegdg.kinosense.actions.WifiAction;
import org.punegdg.kinosense.eventsource.SensorService;
import org.punegdg.kinosense.rule.Rule;
import org.punegdg.kinosense.rule.RuleManager;
import org.punegdg.kinosense.triggers.TriggerIdConstants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// import android.annotation.SuppressLint;

/**
 * Receives all the Kino Sense Triggers and runs the rules to invoke the corresponding actions
 * 
 * @author "Rohit Ghatol" <rohitsghatol@gmail.com>
 * @author "Ashish Kalbhor" <ashish.kalbhor@gmail.com>
 */
public class TriggerReceiver extends BroadcastReceiver
{
	public static String ACTION_KINOSENSE_TRIGGER = "org.punegdg.kinosense.TRIGGER";

	public static Boolean callReceived = false;
	public static Boolean smsSent = false;

	private Context context;

	// /**
	// * Vibrate Action
	// */
	// private AbstractAction vibrateAction = new VibrateAction();
	//
	// /**
	// * Wifi Action
	// */
	//
	// private AbstractAction wifiAction = new WifiAction();
	//
	// /**
	// * Flight Mode Action
	// */
	// private AbstractAction flightaction = new FlightModeAction();
	//
	// /**
	// * Alarm Action
	// * */
	// private AbstractAction alarmAction = new AlarmAction();
	//
	// /**
	// * SMS Action
	// */
	// private AbstractAction smsAction = new SmsAction();
	//
	// /**
	// * Notification Action
	// */
	// private AbstractAction notifAction = new NotificationAction();

	/**
	 * Rule list read from rule database
	 */
	private ArrayList<Rule> ruleList;


	/**
	 * 
	 */
	public TriggerReceiver()
	{
		this.ruleList = RuleManager.getInstance().createRules();
	}


	private void findAndPerformAction(int triggerId)
	{
		for ( Rule rule : this.ruleList )
		{
			if ( triggerId == rule.getTriggerId() )
			{
				this.performAction(rule);
			}
		}
	}


	/**
	 * @param actionId
	 */
	private void performAction(Rule rule)
	{
		int actionId = rule.getActionId();
		switch ( actionId )
		{
			case ActionIdConstants.PHONE_RINGING:
			case ActionIdConstants.PHONE_SILENT:
				AbstractAction silentAction = new SilentAction();
				silentAction.onCreate(this.context);
				Map<String, Object> silentData = new HashMap<String, Object>();
				silentData.put(ActionIdConstants.ACTION_ID, actionId);
				silentAction.perform(silentData);
				break;
			case ActionIdConstants.BRIGHTNESS_HIGH:
			case ActionIdConstants.BRIGHTNESS_LOW:
				AbstractAction brightnessAction = new BrightnessAction();
				brightnessAction.onCreate(this.context);
				Map<String, Object> brightnessData = new HashMap<String, Object>();
				brightnessData.put(ActionIdConstants.ACTION_ID, actionId);
				brightnessAction.perform(brightnessData);
				break;
			case ActionIdConstants.NOTIFICATION:
				AbstractAction notifAction = new NotificationAction();
				notifAction.onCreate(this.context);
				Map<String, Object> notifyData = new HashMap<String, Object>();
				notifyData.put("message", rule.getAdditionalInformation());
				notifAction.perform(notifyData);
				break;
			case ActionIdConstants.ALARM_SET:
				break;
			case ActionIdConstants.FLIGHT_MODE_OFF:
			case ActionIdConstants.FLIGHT_MODE_ON:
				AbstractAction flightaction = new FlightModeAction();
				flightaction.onCreate(this.context);
				Map<String, Object> flightmodeData = new HashMap<String, Object>();
				flightmodeData.put("action", actionId);
				flightaction.perform(flightmodeData);
				break;
			case ActionIdConstants.SMS_SEND:
				AbstractAction smsAction = new SmsAction();
				smsAction.onCreate(this.context);
				Map<String, Object> smsData = new HashMap<String, Object>();
				smsData.put("action", actionId);
				smsData.put("message", rule.getAdditionalInformation());
				// FIXME Phone Number to be set through UI
				smsAction.perform(smsData);
				break;
			case ActionIdConstants.VIBRATE_ACTION:
				AbstractAction vibrateAction = new VibrateAction();
				vibrateAction.onCreate(this.context);
				vibrateAction.perform(null);
				break;
			case ActionIdConstants.WIFI_OFF:
			case ActionIdConstants.WIFI_ON:
				AbstractAction wifiAction = new WifiAction();
				wifiAction.onCreate(this.context);
				Map<String, Object> wifiData = new HashMap<String, Object>();
				wifiData.put("action", actionId);
				wifiAction.perform(wifiData);
				break;
			default:
				break;
		}
	}


	/*
	 * (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent)
	{
		this.context = context;
		ruleList = RuleManager.getInstance().getRules(context);
		if ( intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED) )
		{
			Intent bootIntent = new Intent(context, SensorService.class);
			context.startService(bootIntent);
		}

		int triggerId = intent.getIntExtra(TriggerIdConstants.TIGGER_ID, -1);

		this.findAndPerformAction(triggerId);

		// String trigger = intent.getStringExtra("trigger");
		//
		// if ( "WIFI_FOUND".equals(trigger) )// Toast of SSID
		// {
		// String wifiInfo = intent.getStringExtra("wifiData");
		// String[] InfoParts = wifiInfo.split(",");
		// wifiInfo = InfoParts[0];
		// // Showing only SSID
		// Toast.makeText(context, wifiInfo, Toast.LENGTH_LONG).show();
		// }
		// if ( "SIMCARD_CHANGED".equals(trigger) )// Send SMS
		// {
		// Map<String, Object> smsData = new HashMap<String, Object>();
		// if ( !smsSent )
		// {
		// smsData.put("action", "Sim Card Changed !");
		// smsData.put("number", "8179373415");
		// // FIXME Phone Number to be set through UI
		// // this.smsAction.perform(smsData);
		// smsSent = true;
		// }
		// }
		// if ( "PHONE_UNLOCKED".equals(trigger) )// Turn On WIFI
		// {
		// Map<String, Object> wifiData = new HashMap<String, Object>();
		// wifiData.put("action", "ON");
		// this.wifiAction.perform(wifiData);
		// }
		// if ( "PHONE_LOCKED".equals(trigger) )// Turn Off WIFI
		// {
		// Map<String, Object> wifiData = new HashMap<String, Object>();
		// wifiData.put("action", "OFF");
		// this.wifiAction.perform(wifiData);
		// }
		// if ( "BATTERY_LOW".equals(trigger) )// Notify with Brightness Low, Wifi Off
		// {
		// Map<String, Object> wifiData = new HashMap<String, Object>();
		// Map<String, Object> brightnessData = new HashMap<String, Object>();
		// Map<String, Object> notifyData = new HashMap<String, Object>();
		// notifyData.put("message", "Low Battery ! Wifi has been Turned Off");
		// this.notifAction.perform(notifyData);
		// brightnessData.put("action", "LOW");
		// // this.brightnessAction.perform(brightnessData);
		// wifiData.put("action", "OFF");
		// this.wifiAction.perform(wifiData);
		// }
		// if ( "BATTERY_FULL".equals(trigger) )// Notify with Brightness High,Wifi On
		// {
		// Map<String, Object> wifiData = new HashMap<String, Object>();
		// Map<String, Object> brightnessData = new HashMap<String, Object>();
		// Map<String, Object> notifyData = new HashMap<String, Object>();
		// notifyData.put("message", "Battery Level Restored !");
		// this.notifAction.perform(notifyData);
		// brightnessData.put("action", "HIGH");
		// // this.brightnessAction.perform(brightnessData);
		// wifiData.put("action", "ON");
		// this.wifiAction.perform(wifiData);
		// }
		// if ( "HEADSET_CONNECTED".equals(trigger) )// Start Music
		// {
		// context.startService(new Intent(context, MusicAction.class));
		// }
		// if ( "HEADSET_DISCONNECTED".equals(trigger) )// Stop Music
		// {
		// context.stopService(new Intent(context, MusicAction.class));
		// }
		// if ( "INCOMING_CALL".equals(trigger) )// Music Pause, Notify
		// {
		// Map<String, Object> notifyData = new HashMap<String, Object>();
		// final String mNumber = intent.getStringExtra("number");
		//
		// if ( !callReceived )
		// {
		// String mAction = intent.getStringExtra("action");
		// if ( mAction.equals("rejected") )
		// {
		// notifyData.put("message", "Rejected call from  " + mNumber);
		// this.notifAction.perform(notifyData);
		// callReceived = false;
		// }
		// else if ( mAction.equals("received") )
		// {
		// notifyData.put("message", "Received call from  " + mNumber);
		// this.notifAction.perform(notifyData);
		// callReceived = false;
		// }
		// else if ( mAction.equals("disconnected") )
		// {
		// notifyData.put("message", "Disconnected call from  " + mNumber);
		// this.notifAction.perform(notifyData);
		// callReceived = false;
		// }
		// else
		// {
		// callReceived = true;
		// }
		// }
		// }

	}
}