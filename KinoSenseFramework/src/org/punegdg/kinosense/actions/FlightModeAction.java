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
package org.punegdg.kinosense.actions;

import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Flight Mode action turns ON the Flight mode of device by switching off the device radios.
 * 
 * @author "Ashish Kalbhor"<ashish.kalbhor@gmail.com>
 * 
 * 
 */
public class FlightModeAction implements AbstractAction
{
	/**
	 * Android Application Context
	 */
	private Context context = null;
	/**
	 * Flag to check current state of Flight Mode
	 */
	private boolean isEnabled = false;


	public void onCreate(Context context)
	{
		this.context = context;
		// get existing state of Flight Mode
		this.isEnabled = Settings.System.getInt(this.context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) == 1;
	}


	public void perform(Map<String, Object> flightmodedata)
	{
		String action = (String)flightmodedata.get("flightmode");

		/**
		 * Turn Airplane Mode On
		 */
		if ( "ON".equals(action) )
		{
			if ( this.isEnabled == false )// Enable Flight mode only if already disabled
			{
				Settings.System.putInt(this.context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 1);

				Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
				intent.putExtra("state", true);
				this.context.sendBroadcast(intent);

				Toast.makeText(this.context, "Flight Mode On !", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(this.context, "Flight Mode Already On !", Toast.LENGTH_SHORT).show();
			}
		}

		/**
		 * Turn Airplane Mode Off
		 */
		if ( "OFF".equals(action) )
		{
			if ( this.isEnabled == true ) // Disable only if already enabled
			{
				Settings.System.putInt(this.context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);

				Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
				intent.putExtra("state", false);
				this.context.sendBroadcast(intent);

				Toast.makeText(this.context, "Flight Mode Off !", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(this.context, "Flight Mode Already Off !", Toast.LENGTH_SHORT).show();
			}
		}

	}


	public void onDestroy()
	{
	}

}
