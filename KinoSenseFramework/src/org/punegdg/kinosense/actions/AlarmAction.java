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
import android.provider.AlarmClock;
import android.widget.Toast;

/**
 * 
 * Action to set an Alarm at a particular time on the Device. <code>
 * 		Map<String, Object> alarmData = new HashMap<String, Object>();
 * 			alarmData.put("alarmTime", "Time in 24hr format");
 * 			alarmAction.perform(alarmData);
 * </code>
 * 
 * @author "Ashish Kalbhor"<ashish.kalbhor@gmail.com>
 * 
 */

public class AlarmAction implements AbstractAction
{
	/**
	 * Android Application Context
	 */
	private Context context = null;


	public void onCreate(Context context)
	{
		this.context = context;
	}


	public void perform(Map<String, Object> alarmData)
	{
		String AlarmTime = (String)alarmData.get("alarmTime");
		String AlarmHours = AlarmTime.substring(0, 2); // Get Hours from data
		String AlarmMins = AlarmTime.substring(2, 4); // Get Minutes from data

		Toast.makeText(this.context, "Alarm has been Set for " + AlarmHours + ":" + AlarmMins, Toast.LENGTH_SHORT).show();
		Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
		alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(AlarmHours));
		alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(AlarmMins));

		this.context.startActivity(alarmIntent);
	}


	public void onDestroy()
	{

	}

}
