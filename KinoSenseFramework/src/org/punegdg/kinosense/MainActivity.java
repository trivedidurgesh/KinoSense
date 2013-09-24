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
package org.punegdg.kinosense;

import org.punegdg.kinosense.eventsource.SensorService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Currently this Activity is bootstrap to number of things in the PoC state e.g like the Service which listens to Sensor events and code which
 * registers various broadcast recievers.
 * 
 * @author "Rohit Ghatol"<rohitsghatol@gmail.com>
 */

public class MainActivity extends Activity {

    /*
     * Declaration of the widget items
     */
    private Button buttonnewrule, buttonreviewrule;

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Intent startServiceIntent = new Intent(this.getApplicationContext(), SensorService.class);
        this.startService(startServiceIntent);

        this.buttonnewrule = (Button) this.findViewById(R.id.buttonnewrule);
        this.buttonreviewrule = (Button) this.findViewById(R.id.buttonreviewrule);
        /*
         * Switching Activity to Creating new Rule
         */
        this.buttonnewrule.setOnClickListener(new OnClickListener() {

            public void onClick(final View v) {

                Intent newruleintent = new Intent(MainActivity.this, NewTriggerRuleActivity.class);
                MainActivity.this.startActivity(newruleintent);
            }
        });
        /*
         * Switching Activity to Reviewing Rule
         */
        this.buttonreviewrule.setOnClickListener(new OnClickListener() {

            public void onClick(final View v) {

                Intent ruleReviewintent = new Intent(MainActivity.this, RuleReviewActivity.class);
                ruleReviewintent.putExtra("param1", "");
                MainActivity.this.startActivity(ruleReviewintent);
            }
        });
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onStop()
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        this.moveTaskToBack(true);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        MainActivity.this.finish();

    }
}