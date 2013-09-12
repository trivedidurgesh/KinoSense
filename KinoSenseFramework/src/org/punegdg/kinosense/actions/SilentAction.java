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
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.preference.PreferenceManager;

/**
 * Action which can silent the phone and on the flip side restore the volume.
 * 
 * @author "Rohit Ghatol"<rohitsghatol@gmail.com>
 * @author "Apurva Bhoite"<bhoiteapurva@gmail.com>
 */

public class SilentAction implements AbstractAction
{

	/**
	 * Android Application Context
	 */
	private Context context = null;


	/**
	 * Audio Manager used to change the ringer volume
	 */

	public void onCreate(Context context)
	{
		this.context = context;
	}


	public void perform(Map<String, Object> data)
	{
		int lastVolume = 0; // Contains info of last volume
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.context);
		SharedPreferences.Editor editor = pref.edit();
		AudioManager audioManager = (AudioManager)this.context.getSystemService(Context.AUDIO_SERVICE);
		int action = (Integer)data.get(ActionIdConstants.ACTION_ID);
		if ( ActionIdConstants.PHONE_SILENT == action )
		{
			lastVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
			editor.putInt("volume", lastVolume);
			/*
			 * Store device's volume level.
			 */
			audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, AudioManager.FLAG_SHOW_UI
					+ AudioManager.FLAG_PLAY_SOUND);
			/*
			 * Device's volume set to zero.
			 */
			editor.commit();
		}
		else if ( ActionIdConstants.PHONE_RINGING == action )
		{
			int currentVolume = pref.getInt("volume", 7);
			audioManager.setStreamVolume(AudioManager.STREAM_RING, currentVolume, AudioManager.FLAG_SHOW_UI
					+ AudioManager.FLAG_PLAY_SOUND);
			/*
			 * Device's volume restored.
			 */
		}
	}


	public void onDestroy()
	{

	}

}