package org.punegdg.kinosense;

import org.punegdg.kinosense.triggers.TriggerIdConstants;
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

/**
 * 
 * Activity for selecting Trigger for the rule.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * @author "Ashish Kalbhor" <ashish.kalbhor@gmail.com>
 * 
 */
public class NewTriggerRuleActivity extends Activity
{

	// Declaring The Button for the BACK , NEXT and CANCEL Actions
	private Button buttontriggerback, buttontriggernext, triggerbuttoncancel;

	// Declaring The check boxes
	CheckBox checkBoxlowbattery, checkBoxbatteryfull, checkBoxheadphoneconnected, checkBoxheadphonedisconnected,
			checkBoxincomingcall, checkBoxshake, checkBoxsimcardchange, checkBoxunlockdevice, checkBoxwifidetected;
	private StringBuffer triggerText = new StringBuffer();
	String triggerrule;
	private static int triggerID;
	boolean triggerEnabled = true;

	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_newtrigger);

		// UI widget for Creating Trigger
		this.checkBoxlowbattery = (CheckBox)this.findViewById(R.id.checkBoxlowbattery);
		this.checkBoxbatteryfull = (CheckBox)this.findViewById(R.id.checkBoxbatteryfull);
		this.checkBoxheadphoneconnected = (CheckBox)this.findViewById(R.id.checkBoxheadphoneconnected);
		this.checkBoxheadphonedisconnected = (CheckBox)this.findViewById(R.id.checkBoxheadphonedisconnected);
		this.checkBoxincomingcall = (CheckBox)this.findViewById(R.id.checkBoxincomingcall);
		this.checkBoxshake = (CheckBox)this.findViewById(R.id.checkBoxshake);
		this.checkBoxsimcardchange = (CheckBox)this.findViewById(R.id.checkBoxsimcardchange);
		this.checkBoxunlockdevice = (CheckBox)this.findViewById(R.id.checkBoxunlockdevice);
		this.checkBoxwifidetected = (CheckBox)this.findViewById(R.id.checkBoxwifidetected);
		this.checkBoxunlockdevice=(CheckBox)findViewById(R.id.checkBoxunlockdevice);
		this.checkBoxwifidetected=(CheckBox)findViewById(R.id.checkBoxwifidetected);
		
		// Logic for selecting a widget and creating Rule
		this.checkBoxlowbattery.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxlowbattery.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " When Battery Low");
					triggerID = TriggerIdConstants.BATTERY_LOW;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		this.checkBoxbatteryfull.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxbatteryfull.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " When Battery Full");
						triggerID = TriggerIdConstants.BATTERY_FULL;
						buttontriggernext.setEnabled(true);
						buttontriggernext.setVisibility(View.VISIBLE);					
					}
					else
					{
						triggerID = -1;
						triggerText.replace(0, triggerText.length(), "");
						buttontriggernext.setEnabled(false);
						buttontriggernext.setVisibility(View.INVISIBLE);
					}
			}
		});

		this.checkBoxheadphoneconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxheadphoneconnected.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Headphone Connected");
					triggerID = TriggerIdConstants.HEADPHONE_CONNECTED;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);					
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		this.checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Headphone Disconnected");
					triggerID = TriggerIdConstants.HEADPHONE_DISCONNECTED;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);					
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});		

		this.checkBoxincomingcall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxincomingcall.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Call is Coming");
					triggerID = TriggerIdConstants.INCOMING_CALL;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);					
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		this.checkBoxshake.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxshake.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Phone is Shaked");
					triggerID = TriggerIdConstants.DEVICE_SHAKING;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);					
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		this.checkBoxsimcardchange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxsimcardchange.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Sim Card is Changed");
					triggerID = TriggerIdConstants.SIM_CARD_CHANGED;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);					
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		this.checkBoxunlockdevice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxunlockdevice.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Phone is Unlocked");
					triggerID = TriggerIdConstants.PHONE_UNLOCKED;	
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);				
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		this.checkBoxwifidetected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxwifidetected.isChecked() )
				{
					triggerText.replace(0, triggerText.length(), " Wifi Detected");
					triggerID = TriggerIdConstants.WIFI_DETECTED;
					buttontriggernext.setEnabled(true);
					buttontriggernext.setVisibility(View.VISIBLE);					
				}
				else
				{
					triggerID = -1;
					triggerText.replace(0, triggerText.length(), "");
					buttontriggernext.setEnabled(false);
					buttontriggernext.setVisibility(View.INVISIBLE);
				}
			}
		});

		/*
		 * Back Button to go back to the Action Selection Screen
		 */
		this.buttontriggerback = (Button)this.findViewById(R.id.buttontriggerback);
		this.buttontriggerback.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewTriggerRuleActivity.this, MainActivity.class);
				NewTriggerRuleActivity.this.startActivity(intent);
			}
		});

		/*
		 * Create Button to create the Rule and Switch the activity to ReviewRule
		 */
		this.buttontriggernext = (Button)this.findViewById(R.id.buttonnext);
		this.buttontriggernext.setEnabled(false);
		this.buttontriggernext.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent actionRuleIntent=new Intent(NewTriggerRuleActivity.this,NewActionRuleActivity.class);
				triggerrule=triggerText.toString();
				actionRuleIntent.putExtra("triggerrule",triggerrule);				
				actionRuleIntent.putExtra("triggerID", triggerID);
				Toast.makeText(getApplicationContext(), triggerrule, Toast.LENGTH_SHORT).show();
				startActivity(actionRuleIntent);
			}
		});

		/*
		 * Cancel Button to cancel the current selection of Trigger
		 */
		this.triggerbuttoncancel = (Button)this.findViewById(R.id.triggerbuttoncancel);
		this.triggerbuttoncancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// code for changing State
				triggerText.replace(0, triggerText.length(), "");	
				buttontriggernext.setEnabled(false);
				unCheckBox();
				changeCheckBoxState(true);
			}
		});

	}


	public void changeCheckBoxState(boolean state)
	{
		this.checkBoxlowbattery.setEnabled(state);
		this.checkBoxbatteryfull.setEnabled(state);
		this.checkBoxheadphoneconnected.setEnabled(state);
		this.checkBoxheadphonedisconnected.setEnabled(state);
		this.checkBoxincomingcall.setEnabled(state);
		this.checkBoxshake.setEnabled(state);
		this.checkBoxsimcardchange.setEnabled(state);
		this.checkBoxunlockdevice.setEnabled(state);
		this.checkBoxwifidetected.setEnabled(state);
	}


	public void unCheckBox()
	{
		this.checkBoxlowbattery.setChecked(false);
		this.checkBoxbatteryfull.setChecked(false);
		this.checkBoxheadphoneconnected.setChecked(false);
		this.checkBoxheadphonedisconnected.setChecked(false);
		this.checkBoxincomingcall.setChecked(false);
		this.checkBoxshake.setChecked(false);
		this.checkBoxsimcardchange.setChecked(false);
		this.checkBoxunlockdevice.setChecked(false);
		this.checkBoxwifidetected.setChecked(false);
	}
}
