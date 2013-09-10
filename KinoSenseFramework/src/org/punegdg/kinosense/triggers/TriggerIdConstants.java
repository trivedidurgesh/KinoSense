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

/**
 * @author saurabhg
 * 
 */
public class TriggerIdConstants
{

    /**
     * Private constructor to restrict creating instance of this class
     */
    private TriggerIdConstants()
    {
    }

    public static final String TIGGER_ID = "TRIGGER_ID";

    public static final int POWER_CONNECTED_TRIGGER = 0;
    public static final int POWER_DISCONNECTED_TRIGGER = 1;
    public static final int HEADPHONE_CONNECTED = 2;
    public static final int HEADPHONE_DISCONNECTED = 3;
    public static final int DEVICE_FLIPPED_DOWN = 4;
    public static final int DEVICE_FLIPPED_UP = 5;
    public static final int DEVICE_SHAKING = 6;
    public static final int SIM_INSERTED = 7;
    public static final int SIM_REMOVED = 8;
    public static final int PHONE_LOCKED = 9;
    public static final int PHONE_UNLOCKED = 10;
    public static final int INCOMING_CALL = 11;
    public static final int BATTERY_LOW = 12;
    public static final int BATTERY_NORMAL = 13;
    public static final int WIFI_DETECTED = 14;
    public static final int SIGNAL_STRENGTH_LOW = 15;
    public static final int SIGNAL_STRENGTH_GOOD = 16;
    public static final int BATTERY_FULL = 17;
    public static final int SIM_CARD_CHANGED = 18;

}