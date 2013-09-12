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
import android.net.wifi.WifiManager;

/**
 * Action which can change the Wifi state of the device to On or OFF. <code>
 * 		AbstractAction action = new WifiAction();
 * 		Map data = new HashMap();
 * 		data.put("WifiAction","ON");
 * 	 	action.perform(data);
 * </code>
 * 
 * @author "Ashish Kalbhor"<ashish.kalbhor@gmail.com>
 */

public class WifiAction implements AbstractAction {
    /**
     * Android Application Context
     */
    private Context context = null;

    /**
     * WifiManager to access the device Wifi actions
     */

    private WifiManager wifimgr;

    public void onCreate(final Context context) {
        this.context = context;
        this.wifimgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public void perform(final Map<String, Object> wifiData) {
        // Turn the Wifi State On or Off

        int action = (Integer) wifiData.get(ActionIdConstants.ACTION_ID);
        if (ActionIdConstants.WIFI_ON == action) {
            this.wifimgr.setWifiEnabled(true);
        } else if (ActionIdConstants.WIFI_OFF == action) {
            this.wifimgr.setWifiEnabled(false);
        }
    }

    public void onDestroy() {
        this.wifimgr = null;
    }

}
