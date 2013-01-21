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
import android.util.Log;

/**
 * This Trigger is for the action when Battery is low
 * 
 * @author "Apurva Bhoite"<bhoiteapurva@gmail.com>
 */
public class BatteryTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger
{
	/**
	 * Android's Application Context
	 */
	private Context context = null;


	public void onCreate(Context context)
	{
		this.context = context;
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
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

		Log.d("BroadCastReceiver", intent.toString());
		String action = intent.getAction();
		int rawlevel = intent.getIntExtra("level", -1);
		int scale = intent.getIntExtra("scale", -1);
		int level = -1;
		if ( rawlevel >= 0 && scale > 0 )
		{
			level = rawlevel * 100 / scale;
		}

		if ( level > 20 )
		{
			Intent bcIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			bcIntent.putExtra("trigger", "BATTERY_OKAY");
			context.sendBroadcast(bcIntent);
		}
		else
		{
			Intent bcIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			bcIntent.putExtra("trigger", "BATTERY_LOW");
			context.sendBroadcast(bcIntent);
		}

	}

}
