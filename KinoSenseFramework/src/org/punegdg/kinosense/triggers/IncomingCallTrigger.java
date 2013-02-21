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
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * This Trigger is for detecting Incoming Call on the device.
 * <ul>
 * Phone Number is broadcasted
 * </ul>
 * 
 * @author "Sandeep Mane"<sandeepsmane@ymail.com>
 * 
 */

public class IncomingCallTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger
{
	/**
	 * Android Application Context
	 */
	private Context context = null;


	public void onCreate(Context context)
	{
		this.context = context;
		IntentFilter filter = new IntentFilter();
		/**
		 * Intent Action PHONE_STATE is registered in Manifest
		 */
		context.registerReceiver(this.getBroadCastReceiver(), filter);
	}


	public BroadcastReceiver getBroadCastReceiver()
	{
		return this;
	}


	public void onDestroy()
	{
		this.context.unregisterReceiver(this.getBroadCastReceiver());
	}


	@Override
	public void onReceive(Context context, Intent intent)
	{
		/**
		 * Telephony Manager to get Call Information
		 */
		TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		mPhoneStateListener phonelistener = new mPhoneStateListener();
		telephony.listen(phonelistener, PhoneStateListener.LISTEN_CALL_STATE);

		Bundle bundle = intent.getExtras();
		String incomingNumber = bundle.getString("incoming_number"); // Read Caller's Number

		Intent callIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
		// Broadcast TriggerName and PhoneNumber
		callIntent.putExtra("trigger", "INCOMING_CALL");
		callIntent.putExtra("number", incomingNumber);
		context.sendBroadcast(callIntent);
	}

	/**
	 * PhoneStateListener class to listen to phone state, and retrieve call information
	 * 
	 */
	public class mPhoneStateListener extends PhoneStateListener
	{
		@Override
		public void onCallStateChanged(int state, String incomingNumber)
		{

		}
	}

}
