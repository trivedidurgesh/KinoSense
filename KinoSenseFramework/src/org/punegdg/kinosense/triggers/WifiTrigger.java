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

import java.util.List;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

/**
 * This Trigger scans Wifi Network, and broadcasts scan results
 * 
 * @author "Ashish Kalbhor"<ashish.kalbhor@gmail.com>
 */

public class WifiTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger
{

	/**
	 * Android's Application Context
	 */
	private Context context = null;

	/**
	 * Device WifiManager
	 */
	WifiManager wifimgr = null;

	/**
	 * Storage of Wifi scan result
	 */
	StringBuilder wifidata = new StringBuilder();

	List<ScanResult> wifiList;


	public void onCreate(Context context)
	{
		this.context = context;
		this.wifimgr = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		context.registerReceiver(this.getBroadCastReceiver(), filter);
		this.wifimgr.startScan();
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
		int i;
		this.wifidata = new StringBuilder();
		this.wifiList = this.wifimgr.getScanResults();
		for ( i = 0; i < this.wifiList.size(); i++ )
		{
			this.wifidata.append(new Integer(i + 1).toString() + ".");
			this.wifidata.append(this.wifiList.get(i));
			this.wifidata.append("\n\n");
		}
		if ( i < 1 )
		{
			this.wifidata.append('\0');
		}
		Intent bcIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
		bcIntent.putExtra("trigger", "WIFI_FOUND");
		// Broadcast the Wifi Details that are scanned each time
		bcIntent.putExtra("wifiData", this.wifidata.toString());
		context.sendBroadcast(bcIntent);
	}

}
