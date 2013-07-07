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

		// Logic for selecting a widget and creating Rule
		this.checkBoxlowbattery.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxlowbattery.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" When Battery Low");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxbatteryfull.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxbatteryfull.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" When Battery Full");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxheadphoneconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxheadphoneconnected.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" Headphone Connected");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" Headphone Disconnected");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" Headphone Disconnected");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxincomingcall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxincomingcall.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							"Call is Coming");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxshake.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxshake.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							"Phone is Shaked");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxsimcardchange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxsimcardchange.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							"Sim Card is Changed");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxunlockdevice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxunlockdevice.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							"Phone is Unlocked");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
				}
			}
		});

		this.checkBoxwifidetected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxwifidetected.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							"Wifi Detected");
					NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
					NewTriggerRuleActivity.this.triggerEnabled = false;
					NewTriggerRuleActivity.this.changeCheckBoxState(NewTriggerRuleActivity.this.triggerEnabled);
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

				Intent actionRuleIntent = new Intent(NewTriggerRuleActivity.this, NewActionRuleActivity.class);
				NewTriggerRuleActivity.this.triggerrule = NewTriggerRuleActivity.this.triggerText.toString();
				actionRuleIntent.putExtra("param1", NewTriggerRuleActivity.this.triggerrule);
				Toast.makeText(NewTriggerRuleActivity.this.getApplicationContext(),
						NewTriggerRuleActivity.this.triggerrule, Toast.LENGTH_SHORT).show();
				actionRuleIntent.putExtra("param1", NewTriggerRuleActivity.this.triggerrule);
				NewTriggerRuleActivity.this.startActivity(actionRuleIntent);
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
				NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
				NewTriggerRuleActivity.this.buttontriggernext.setEnabled(false);
				NewTriggerRuleActivity.this.unCheckBox();
				NewTriggerRuleActivity.this.changeCheckBoxState(true);
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
