/**
 * Copyright 2012 Pune-GDG (http://meetup.com/pune-gdg)
 * 
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
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * This Trigger is for the action when the simcard is changed from the device.
 * 
 * @author "Apurva Bhoite"<bhoiteapurva@gmail.com>
 */
public class SimCardChangedTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger

{
	/**
	 * Android's Application Context
	 */

	private Context context = null;

	TelephonyManager telemamanger = null;
	String getSimSerialNumber;
	String getSimNumber;
	String SimSerialNo = "89916611521121277211"; // Hard coded for time beign.


	public void onCreate(Context context)
	{

		this.context = context;
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BOOT_COMPLETED); // should execute on BOOT_COMPLETED ideally
		context.registerReceiver(getBroadCastReceiver(), filter);

		TelephonyManager telemamanger = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		getSimSerialNumber = telemamanger.getSimSerialNumber();
		getSimNumber = telemamanger.getLine1Number();

		// TODO Auto-generated method stub

	}


	public BroadcastReceiver getBroadCastReceiver()
	{
		// TODO Auto-generated method stub
		return this;
	}


	public void onDestroy()
	{
		context.unregisterReceiver(getBroadCastReceiver());

	}


	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		Log.d("BroadCastReceiver", intent.toString());

		String action = intent.getAction();

		if ( action.equals(android.content.Intent.ACTION_BOOT_COMPLETED) )
		{
			if ( !getSimSerialNumber.equals(SimSerialNo) ) // check for simserial nos.
			{
				Intent bcIntent1 = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
				bcIntent1.putExtra("trigger", "SIMCARD_CHANGED");
				context.sendBroadcast(bcIntent1);
			}
			else
			{
				Intent bcIntent1 = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
				bcIntent1.putExtra("trigger", "SIMCARD_UNCHANGED");
				context.sendBroadcast(bcIntent1);
			}
		}

	}

}
