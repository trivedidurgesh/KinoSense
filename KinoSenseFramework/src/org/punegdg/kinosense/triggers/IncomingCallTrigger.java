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
 * @author "Ashish Kalbhor" <ashish.kalbhor@gmail.com>
 */

public class IncomingCallTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger {
    /**
     * Android Application Context
     */
    private Context context = null;

    /**
     * Call state flags
     */
    public static boolean wasRinging = false;
    public static boolean wasRejected = false;
    public static boolean wasReceived = false;
    public static boolean wasDisconnected = false;

    /**
     * Telephony manager
     */
    TelephonyManager telephony = null;

    mPhoneStateListener phonelistener;

    /**
     * Incoming call's Phone number
     */
    static String incomingNumber = null;

    public void onCreate(final Context context) {
        this.context = context;
        IntentFilter filter = new IntentFilter();
        /**
         * Intent Action PHONE_STATE is registered in Manifest
         */
        context.registerReceiver(this.getBroadCastReceiver(), filter);
    }

    public BroadcastReceiver getBroadCastReceiver() {
        return this;
    }

    public void onDestroy() {
        this.context.unregisterReceiver(this.getBroadCastReceiver());
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        /**
         * Telephony Manager to get Call Information
         */
        this.telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.phonelistener = new mPhoneStateListener();
        this.telephony.listen(this.phonelistener, PhoneStateListener.LISTEN_CALL_STATE);

        Bundle bundle = intent.getExtras();
        String incomingNumber = bundle.getString("incoming_number"); // Read Caller's Number

        if (bundle.getString("incoming_number") != null) {
            String incomingMNumber = bundle.getString("incoming_number"); // Read Caller's Number
            incomingNumber = incomingMNumber;
        }

        Intent callIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
        // Broadcast TriggerName and PhoneNumber
        // callIntent.putExtra("trigger", "INCOMING_CALL");
        intent.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.INCOMING_CALL);
        callIntent.putExtra("number", incomingNumber);

        /**
         * Handling 3 phases after getting an Incoming call
         * <ul>
         * <li>Call Received</li>
         * <li>Call Rejected/Missed</li>
         * <li>Call Disconnected after receiving</li>
         * </ul>
         */

        if (wasRejected) {
            callIntent.putExtra("action", "rejected");
            context.sendBroadcast(callIntent);
        }
        if (wasReceived) {
            callIntent.putExtra("action", "received");
            context.sendBroadcast(callIntent);
        }
        if (wasDisconnected) {
            callIntent.putExtra("action", "disconnected");
            context.sendBroadcast(callIntent);
        }

    }

    /**
     * PhoneStateListener class to listen to phone state, and retrieve call information
     */
    public class mPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(final int state, final String incomingNumber) {
            switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                wasDisconnected = false;
                wasRejected = false;
                wasRinging = true;
                break;
            case TelephonyManager.CALL_STATE_IDLE:

                if (wasReceived)// Call was received, and now disconnected
                {
                    wasDisconnected = true;
                    wasReceived = false;
                } else if (wasRinging)// Phone was ringing, and now missed the call
                {
                    wasRejected = true;
                }
                wasRinging = false;
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if (wasRinging)// Phone was ringing, and now call is received
                {
                    wasReceived = true;
                }
                break;
            default:
                break;
            }
        }
    }

}
