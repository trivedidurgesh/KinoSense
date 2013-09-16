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

/**
 * @author saurabhg
 */
public class ActionIdConstants {

    /**
     * Private constructor to restrict creating instance of this class
     */
    private ActionIdConstants() {
    }

    public static final String ACTION_ID = "ACTION_ID";

    public static final int BRIGHTNESS_LOW = 0;
    public static final int BRIGHTNESS_HIGH = 1;
    public static final int PHONE_SILENT = 2;
    public static final int PHONE_RINGING = 3;
    public static final int VIBRATE_ACTION = 4;
    public static final int WIFI_ON = 5;
    public static final int WIFI_OFF = 6;
    public static final int FLIGHT_MODE_ON = 7;
    public static final int FLIGHT_MODE_OFF = 8;
    public static final int ALARM_SET = 9;
    public static final int SMS_SEND = 10;
    public static final int NOTIFICATION = 11;
    public static final String DISABLEACTION = "disableaction";
    public static final String TRIGGERID = "triggerID";
    public static final String ACTIONID = "actionID";
    public static final String ACTION_STATE = "state";
}
