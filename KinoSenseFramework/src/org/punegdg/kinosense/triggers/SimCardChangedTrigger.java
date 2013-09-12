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

import org.punegdg.kinosense.database.DatabaseHandler;
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
public class SimCardChangedTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger {
    /**
     * Android's Application Context
     */
    private Context context = null;

    /**
     * Database Handler Object
     */
    DatabaseHandler dbHandler;

    TelephonyManager telemamanger = null;
    String getSimSerialNumber;
    String getSimNumber;
    String SimSerialNo = "89916611521121277211"; // Hard coded for time beign.

    public void onCreate(final Context context) {

        this.context = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED); // should execute on BOOT_COMPLETED ideally
        context.registerReceiver(this.getBroadCastReceiver(), filter);
        this.dbHandler = new DatabaseHandler(context);

        TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.getSimSerialNumber = telemamanger.getSimSerialNumber();
        this.getSimNumber = telemamanger.getLine1Number();

        this.dbHandler.addSimSerial(this.getSimSerialNumber); // Insert in database
    }

    public BroadcastReceiver getBroadCastReceiver() {
        return this;
    }

    public void onDestroy() {
        this.context.unregisterReceiver(this.getBroadCastReceiver());
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.d("BroadCastReceiver", intent.toString());

        String action = intent.getAction();

        if (action.equals(android.content.Intent.ACTION_POWER_DISCONNECTED)) {
            if (!this.dbHandler.getSimSerial().equals(this.SimSerialNo)) // check for simserial nos.
            {
                Intent bcIntent1 = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
                // bcIntent1.putExtra("trigger", "SIMCARD_CHANGED");
                bcIntent1.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.SIM_CARD_CHANGED);
                context.sendBroadcast(bcIntent1);
            } else {
                Intent bcIntent1 = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
                // bcIntent1.putExtra("trigger", "SIMCARD_UNCHANGED");
                bcIntent1.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.SIM_CARD_CHANGED);
                context.sendBroadcast(bcIntent1);
            }
        }

    }

}
