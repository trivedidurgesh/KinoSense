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
package org.punegdg.kinosense.eventsource;

import java.util.ArrayList;

import org.punegdg.kinosense.actions.ActionIdConstants;
import org.punegdg.kinosense.rule.Rule;
import org.punegdg.kinosense.rule.RuleManager;
import org.punegdg.kinosense.triggers.BatteryTrigger;
import org.punegdg.kinosense.triggers.FlipTrigger;
import org.punegdg.kinosense.triggers.HeadphoneTrigger;
import org.punegdg.kinosense.triggers.IncomingCallTrigger;
import org.punegdg.kinosense.triggers.PowerConnectedTrigger;
import org.punegdg.kinosense.triggers.ShakeTrigger;
import org.punegdg.kinosense.triggers.SignalStrengthTrigger;
import org.punegdg.kinosense.triggers.SimCardChangedTrigger;
import org.punegdg.kinosense.triggers.TriggerIdConstants;
import org.punegdg.kinosense.triggers.UnlockTrigger;
import org.punegdg.kinosense.triggers.WifiTrigger;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;
import org.punegdg.kinosense.triggers.framework.SensorBasedTrigger;

import android.app.Service;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

/**
 * Sensor Listener Service. Listens to various sensor and delegates to SensorBasedTrigger for processing the sensor events
 * 
 * @author "Rohit Ghatol"<rohitsghatol@gmail.com>
 */
public class SensorService extends Service {

    /**
     * Android's Sensor Manager
     */
    private SensorManager sensorMgr = null;

    /**
     * Rule list read from rule database
     */
    private ArrayList<Rule> ruleList;

    /**
     * The Trigger to handle the low level sensor events.
     */
    private SensorBasedTrigger flipTrigger;
    private SensorBasedTrigger shakeTrigger;
    private BroadCastReceiverBasedTrigger powerConnectedTrigger;
    private BroadCastReceiverBasedTrigger simTrigger;
    private BroadCastReceiverBasedTrigger unlockTrigger;
    private BroadCastReceiverBasedTrigger batteryTrigger;
    private BroadCastReceiverBasedTrigger wifiTrigger;
    private BroadCastReceiverBasedTrigger headphoneTrigger;
    private BroadCastReceiverBasedTrigger incomingCallTrigger;
    private BroadCastReceiverBasedTrigger signalStrengthTrigger;

    /*
     * (non-Javadoc)
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();
        this.ruleList = RuleManager.getInstance().getRules(this.getApplicationContext());
        Log.d("SensorService", "Service Started, initialising triggers");
        this.sensorMgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        for (Rule rule : this.ruleList) {
            this.triggerEvent(rule.getTriggerId());
        }
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {

        super.onStartCommand(intent, flags, startId);
        this.triggerEvent(intent.getIntExtra(ActionIdConstants.TRIGGERID, 0));
        return START_REDELIVER_INTENT;
    }

    private void triggerEvent(final int trigger) {
        switch (trigger) {
        case TriggerIdConstants.DEVICE_FLIPPED_DOWN:
        case TriggerIdConstants.DEVICE_FLIPPED_UP:
            if (null == this.flipTrigger) {
                this.flipTrigger = new FlipTrigger();
                this.flipTrigger.onCreate(this.getApplicationContext(), this.sensorMgr);
            }
            break;
        case TriggerIdConstants.POWER_CONNECTED_TRIGGER:
        case TriggerIdConstants.POWER_DISCONNECTED_TRIGGER:
            if (null == this.powerConnectedTrigger) {
                this.powerConnectedTrigger = new PowerConnectedTrigger();
                this.powerConnectedTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.HEADPHONE_CONNECTED:
        case TriggerIdConstants.HEADPHONE_DISCONNECTED:
            if (null == this.headphoneTrigger) {
                this.headphoneTrigger = new HeadphoneTrigger();
                this.headphoneTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.DEVICE_SHAKING:
            if (null == this.shakeTrigger) {
                this.shakeTrigger = new ShakeTrigger();
                this.shakeTrigger.onCreate(this.getApplicationContext(), this.sensorMgr);
            }
            break;
        case TriggerIdConstants.INCOMING_CALL:
            if (null == this.incomingCallTrigger) {
                this.incomingCallTrigger = new IncomingCallTrigger();
                this.incomingCallTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.PHONE_LOCKED:
        case TriggerIdConstants.PHONE_UNLOCKED:
            if (null == this.unlockTrigger) {
                this.unlockTrigger = new UnlockTrigger();
                this.unlockTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.SIGNAL_STRENGTH_GOOD:
        case TriggerIdConstants.SIGNAL_STRENGTH_LOW:
            if (null == this.signalStrengthTrigger) {
                this.signalStrengthTrigger = new SignalStrengthTrigger();
                this.signalStrengthTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.BATTERY_LOW:
        case TriggerIdConstants.BATTERY_NORMAL:
            if (null == this.batteryTrigger) {
                this.batteryTrigger = new BatteryTrigger();
                this.batteryTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.SIM_INSERTED:
        case TriggerIdConstants.SIM_REMOVED:
            if (null == this.simTrigger) {
                this.simTrigger = new SimCardChangedTrigger();
                this.simTrigger.onCreate(this.getApplicationContext());
            }
            break;
        case TriggerIdConstants.WIFI_DETECTED:
            if (null == this.wifiTrigger) {
                this.wifiTrigger = new WifiTrigger();
                this.wifiTrigger.onCreate(this.getApplicationContext());
            }
            break;
        default:
            break;
        }
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SensorService", "Service Destroyed");
    }

    /*
     * (non-Javadoc)
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(final Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

}
