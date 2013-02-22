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
import org.punegdg.kinosense.triggers.BatteryTrigger;
import org.punegdg.kinosense.triggers.HeadphoneTrigger;
import org.punegdg.kinosense.triggers.IncomingCallTrigger;
import org.punegdg.kinosense.triggers.PowerConnectedTrigger;
import org.punegdg.kinosense.triggers.SimCardChangedTrigger;
import org.punegdg.kinosense.triggers.UnlockTrigger;
import org.punegdg.kinosense.triggers.WifiTrigger;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Currently this Activity is bootstrap to number of things in the PoC state e.g like the Service which listens to
 * Sensor events and code which registers various broadcast recievers.
 * 
 * @author "Rohit Ghatol"<rohitsghatol@gmail.com>
 * 
 */
public class MainActivity extends Activity
{

	/**
	 * Power Connected Disconnected BroadCastReceiver
	 */
	private BroadCastReceiverBasedTrigger bbTrigger = new PowerConnectedTrigger();
	private final BroadCastReceiverBasedTrigger simTrigger = new SimCardChangedTrigger();
	private final BroadCastReceiverBasedTrigger unTrigger = new UnlockTrigger();
	private final BroadCastReceiverBasedTrigger batTrigger = new BatteryTrigger();
	private BroadCastReceiverBasedTrigger wifiTrigger = new WifiTrigger();
	private BroadCastReceiverBasedTrigger hpTrigger = new HeadphoneTrigger();
	private BroadCastReceiverBasedTrigger callTrigger = new IncomingCallTrigger();

	/*
	 * Declaration of the widget items
	 */
	 Button buttonnewrule, buttonreviewrule;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);

		this.bbTrigger.onCreate(this.getApplicationContext());
		this.simTrigger.onCreate(this.getApplicationContext());
		this.unTrigger.onCreate(this.getApplicationContext());
		this.batTrigger.onCreate(this.getApplicationContext());
		this.wifiTrigger.onCreate(this.getApplicationContext());
		this.hpTrigger.onCreate(this.getApplicationContext());
		this.callTrigger.onCreate(this.getApplicationContext());

		Intent startServiceIntent = new Intent(this.getApplicationContext(), SensorService.class);
		this.startService(startServiceIntent);

		buttonnewrule=(Button)findViewById(R.id.buttonnewrule);
		buttonreviewrule=(Button)findViewById(R.id.buttonreviewrule);
		/*
		 * Switching Activity to Creating new Rule
		 */
		buttonnewrule.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent newruleintent=new Intent(MainActivity.this,NewActionRuleActivity.class);
				startActivity(newruleintent);
			}
		});
		/*
		 * Switching Activity to Reviewing  Rule
		 */
		buttonreviewrule.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent ruleReviewintent=new Intent(MainActivity.this,RuleReviewActivity.class);
				ruleReviewintent.putExtra("param1","");
				startActivity(ruleReviewintent);
			}
		});
	}


	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		this.getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop()
	{
		super.onStop();
		this.bbTrigger.onDestroy();
		this.wifiTrigger.onDestroy();
		this.simTrigger.onDestroy();
		this.unTrigger.onDestroy();
		this.batTrigger.onDestroy();
		this.hpTrigger.onDestroy();
		this.callTrigger.onDestroy();
	}

}
