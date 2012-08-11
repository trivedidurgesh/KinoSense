/**
 * Copyright 2012 Pune-GDG (http://meetup.com/pune-gdg)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.punegdg.kinosense.triggerReceiver;

import org.punegdg.kinosense.actions.BaseAction;
import org.punegdg.kinosense.actions.SilentAction;
import org.punegdg.kinosense.actions.VibrateAction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Receives all the Kino Sense Triggers and runs the rules to invoke the
 * corresponding actions
 * 
 * @author "Rohit Ghatol" <rohitsghatol@gmail.com>
 * 
 */
public class TriggerReceiver extends BroadcastReceiver {
	public static String ACTION_KINOSENSE_TRIGGER = "org.punegdg.kinosense.TRIGGER";

	/** 
	 * Silent Action 
	 */
	private BaseAction silentAction = new SilentAction();
	
	/**
	 * Vibrate Action
	 */
	private BaseAction vibrateAction = new VibrateAction();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {

		// -----------------------------
		// FIXME Fix the following
		// -----------------------------
		silentAction.onCreate(context);
		vibrateAction.onCreate(context);

		// -----------------------------

		String trigger = intent.getStringExtra("trigger");
		if ("FLIPPED_DOWN".equals(trigger)) {
			vibrateAction.perform(null, null);
			silentAction.perform("Silence", null);
		} else if ("FLIPPED_UP".equals(trigger)) {
			vibrateAction.perform(null, null);
			silentAction.perform("Restore", null);
		}
		
		if ("POWER_CONNECTED".equals(trigger)) {
			vibrateAction.perform(null, null);
			
		} else if ("POWER_DISCONNECTED".equals(trigger)) {
			vibrateAction.perform(null, null);
			
		}
	}

}
