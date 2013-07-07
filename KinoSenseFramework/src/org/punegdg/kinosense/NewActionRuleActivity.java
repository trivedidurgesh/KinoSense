package org.punegdg.kinosense;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * 
 * Activity for selecting Action for the rule.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * 
 */
public class NewActionRuleActivity extends Activity
{
	Button buttonback, buttoncancel, buttoncreate;
	ToggleButton toggleButtonwifi;
	CheckBox checkBoxsilent, checkBoxflight, checkBoxbeep, checkBoxsms, checkBoxalarm, checkBoxshownotification,
			checkBoxvibrate;
	StringBuffer actionString = new StringBuffer("Set WiFi OFF");
	private StringBuffer ruleText = new StringBuffer();
	String actionrule;
	boolean checkenabled = true;
	boolean ischecked = false;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*
		 * <<<<<<< HEAD this.setContentView(R.layout.activity_newrule); this.buttonnext =
		 * (Button)this.findViewById(R.id.buttonnext); this.buttonnext.setEnabled(false);
		 * this.buttonnext.setVisibility(View.INVISIBLE); this.buttonback = (Button)this.findViewById(R.id.buttonback);
		 * this.buttonback.setOnClickListener(new OnClickListener() { public void onClick(View v) { // TODO Auto-generated
		 * method stub Intent mainActivityIntent = new Intent(NewActionRuleActivity.this, MainActivity.class);
		 * NewActionRuleActivity.this.startActivity(mainActivityIntent); =======
		 */
		Intent newActionRuleIntent = this.getIntent();
		final String str1 = newActionRuleIntent.getStringExtra("param1");
		this.setContentView(R.layout.activity_newrule);

		this.buttonback = (Button)this.findViewById(R.id.buttonback);
		this.buttonback.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent triggerIntent = new Intent(NewActionRuleActivity.this, NewTriggerRuleActivity.class);
				NewActionRuleActivity.this.startActivity(triggerIntent);
				// >>>>>>> fff703337db5cc8b56682c6c244e18ae6ff7d036
			}
		});

		// * this.checkBoxwifi = (CheckBox)this.findViewById(R.id.checkBoxwifi); this.checkBoxsilent =
		// * (CheckBox)this.findViewById(R.id.checkBoxsilent); this.checkBoxflight =
		// * (CheckBox)this.findViewById(R.id.checkBoxflight); this.checkBoxbeep =
		// * (CheckBox)this.findViewById(R.id.checkBoxbeep); this.checkBoxsms =
		// * (CheckBox)this.findViewById(R.id.checkBoxsms); /** Logic for creating Text on selection of particular Action
		// */
		//
		// this.checkBoxwifi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		// {
		// if ( NewActionRuleActivity.this.checkBoxwifi.isChecked() )
		// {
		// NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
		// "Put Wifi on ");
		// NewActionRuleActivity.this.checkenabled = false;
		// NewActionRuleActivity.this.buttonnext.setEnabled(true);
		// NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
		// }
		// else
		// {
		// NewActionRuleActivity.this.actionString = null;
		// Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
		// NewActionRuleActivity.this.startActivity(intent);
		// }
		// }
		// });
		//
		// this.checkBoxsilent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		// {
		// if ( NewActionRuleActivity.this.checkBoxsilent.isChecked() )
		// {
		// NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
		// "Put Phone on Silent");
		// NewActionRuleActivity.this.checkenabled = false;
		// NewActionRuleActivity.this.buttonnext.setEnabled(true);
		// NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
		// }
		// else
		// {
		// NewActionRuleActivity.this.actionString = null;
		// Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
		// NewActionRuleActivity.this.startActivity(intent);
		// }
		// }
		// });
		//
		// this.checkBoxflight.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		// {
		// if ( NewActionRuleActivity.this.checkBoxflight.isChecked() )
		// {
		// NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
		// "Put Phone on Flight Mode");
		// NewActionRuleActivity.this.checkenabled = false;
		// NewActionRuleActivity.this.buttonnext.setEnabled(true);
		// NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
		// }
		// else
		// {
		// NewActionRuleActivity.this.actionString = null;
		// Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
		// NewActionRuleActivity.this.startActivity(intent);
		// }
		// }
		// });
		//
		// this.checkBoxbeep.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		// {
		// if ( NewActionRuleActivity.this.checkBoxbeep.isChecked() )
		// {
		// NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
		// "Put Phone on Beep");
		// NewActionRuleActivity.this.checkenabled = false;
		// NewActionRuleActivity.this.buttonnext.setEnabled(true);
		// NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
		// }
		// else
		// {
		// NewActionRuleActivity.this.actionString = null;
		// Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
		// NewActionRuleActivity.this.startActivity(intent);
		// }
		// }
		// });
		//
		// this.checkBoxsms.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		// {
		// if ( NewActionRuleActivity.this.checkBoxsms.isChecked() )
		// {
		// NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
		// "Send SMS");
		// NewActionRuleActivity.this.checkenabled = false;
		// NewActionRuleActivity.this.buttonnext.setEnabled(true);
		// NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
		// }
		// else
		// {
		// NewActionRuleActivity.this.actionString = null;
		// Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
		// NewActionRuleActivity.this.startActivity(intent);
		// }
		// }
		// });
		//
		// this.buttonnext.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v)
		// {
		// // code for changing State
		// Intent newTriggerRuleIntent = new Intent(NewActionRuleActivity.this, NewTriggerRuleActivity.class);
		// NewActionRuleActivity.this.actionrule = NewActionRuleActivity.this.actionString.toString();
		// newTriggerRuleIntent.putExtra("param1", NewActionRuleActivity.this.actionrule);
		// NewActionRuleActivity.this.startActivity(newTriggerRuleIntent);
		// }
		// });
		//
		// }
		//
		//
		// public void changeCheckBoxState(boolean state)
		// {
		// this.checkBoxsilent.setEnabled(state);
		// this.checkBoxflight.setEnabled(state);
		// this.checkBoxbeep.setEnabled(state);
		// this.checkBoxsms.setEnabled(state);
		// =======

		/*
		 * Declaring UI items for Actions
		 */

		this.toggleButtonwifi = (ToggleButton)this.findViewById(R.id.toggleButtonwifi);

		// by default making Wifibutton focus, means if user just clicked on next button wifi off text will go to next
		// activity
		this.toggleButtonwifi.setFocusable(true);
		this.toggleButtonwifi.requestFocus();
		this.checkBoxsilent = (CheckBox)this.findViewById(R.id.checkBoxsilent);
		this.checkBoxflight = (CheckBox)this.findViewById(R.id.checkBoxflight);
		this.checkBoxsms = (CheckBox)this.findViewById(R.id.checkBoxsms);
		this.checkBoxalarm = (CheckBox)this.findViewById(R.id.checkBoxalarm);
		this.checkBoxshownotification = (CheckBox)this.findViewById(R.id.checkBoxshownotification);
		this.checkBoxvibrate = (CheckBox)this.findViewById(R.id.checkBoxvibrate);

		/**
		 * Logic for creating Text on selection of particular Action
		 **/

		this.toggleButtonwifi.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				if ( NewActionRuleActivity.this.toggleButtonwifi.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Set Wifi ON");
					// buttonnext.setEnabled(true);

					NewActionRuleActivity.this.checkenabled = false;
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);

				}
				else
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Set WiFi OFF");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
				}
			}
		});
		this.checkBoxsilent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxsilent.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Put Phone on Silent");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
					NewActionRuleActivity.this.toggleButtonwifi.setEnabled(false);
				}
			}
		});
		this.checkBoxflight.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxflight.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Put Phone on Flight Mode");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
					NewActionRuleActivity.this.toggleButtonwifi.setEnabled(false);
				}
			}
		});
		this.checkBoxsms.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxsms.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Send SMS");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
					NewActionRuleActivity.this.toggleButtonwifi.setEnabled(false);
				}
			}
		});
		this.checkBoxalarm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxalarm.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Start Alarm");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
					NewActionRuleActivity.this.toggleButtonwifi.setEnabled(false);
				}
			}
		});
		this.checkBoxshownotification.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxshownotification.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Show Notification");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
					NewActionRuleActivity.this.toggleButtonwifi.setEnabled(false);
				}
			}
		});
		this.checkBoxvibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxvibrate.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Vibrate");
					NewActionRuleActivity.this.checkenabled = false;
					// buttonnext.setEnabled(true);
					NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
					NewActionRuleActivity.this.toggleButtonwifi.setEnabled(false);
				}
			}
		});

		// Code for creating the new rule and navigate to the Rule Review View
		this.buttoncreate = (Button)this.findViewById(R.id.buttoncreate);
		this.buttoncreate.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// code for changing State
				Intent ruleReviewintent = new Intent(NewActionRuleActivity.this, RuleReviewActivity.class);
				NewActionRuleActivity.this.ruleText.append(str1);
				NewActionRuleActivity.this.actionrule = NewActionRuleActivity.this.actionString.toString();
				NewActionRuleActivity.this.ruleText.append(" " + NewActionRuleActivity.this.actionrule);
				String rule = NewActionRuleActivity.this.ruleText.toString();
				Toast.makeText(NewActionRuleActivity.this.getApplicationContext(), rule, Toast.LENGTH_SHORT).show();
				ruleReviewintent.putExtra("param2", rule);
				NewActionRuleActivity.this.startActivity(ruleReviewintent);
			}
		});

		// // Code for cancel the current selection
		// this.buttoncancel = (Button)this.findViewById(R.id.buttoncancel);
		// this.buttoncancel.setOnClickListener(new OnClickListener() {
		// public void onClick(View v)
		// {
		// // code for changing State
		// NewActionRuleActivity.this.actionString = null;
		// // changeCheckBoxState(true);
		// Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
		// NewActionRuleActivity.this.startActivity(intent);
		// }
		// });

	}


	public void changeCheckBoxState(boolean state)
	{
		this.checkBoxsilent.setEnabled(state);
		this.checkBoxflight.setEnabled(state);
		this.checkBoxsms.setEnabled(state);
		this.checkBoxalarm.setEnabled(state);
		this.checkBoxshownotification.setEnabled(state);
		this.checkBoxvibrate.setEnabled(state);
	}
}
