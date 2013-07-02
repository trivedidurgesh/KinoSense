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
 * 
 */
public class NewTriggerRuleActivity extends Activity {
	
	//Declaring The Button for the BACK , NEXT and CANCEL Actions
	private Button buttontriggerback,buttontriggernext,triggerbuttoncancel;
	
	//Declaring The check boxes
	CheckBox checkBoxlowbattery,checkBoxbatteryfull,checkBoxheadphoneconnected,checkBoxheadphonedisconnected,
			 checkBoxincomingcall,checkBoxshake,checkBoxsimcardchange,checkBoxunlockdevice,checkBoxwifidetected;
	private StringBuffer triggerText=new StringBuffer();
	String triggerrule;
	boolean triggerEnabled=true;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtrigger);

		//UI widget for Creating Trigger
		checkBoxlowbattery=(CheckBox)findViewById(R.id.checkBoxlowbattery);
		checkBoxbatteryfull=(CheckBox)findViewById(R.id.checkBoxbatteryfull);
		checkBoxheadphoneconnected=(CheckBox)findViewById(R.id.checkBoxheadphoneconnected);
		checkBoxheadphonedisconnected=(CheckBox)findViewById(R.id.checkBoxheadphonedisconnected);
		checkBoxincomingcall=(CheckBox)findViewById(R.id.checkBoxincomingcall);
		checkBoxshake=(CheckBox)findViewById(R.id.checkBoxshake);
		checkBoxsimcardchange=(CheckBox)findViewById(R.id.checkBoxsimcardchange);
		checkBoxunlockdevice=(CheckBox)findViewById(R.id.checkBoxunlockdevice);
		checkBoxwifidetected=(CheckBox)findViewById(R.id.checkBoxwifidetected);



		//Logic for selecting a  widget and creating Rule
		checkBoxlowbattery.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxlowbattery.isChecked()){
					triggerText.replace(0, triggerText.length(), " When Battery Low");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});

		checkBoxbatteryfull.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxbatteryfull.isChecked()){
					triggerText.replace(0, triggerText.length(), " When Battery Full");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxheadphoneconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxheadphoneconnected.isChecked()){
					triggerText.replace(0, triggerText.length(), " Headphone Connected");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxheadphonedisconnected.isChecked()){
					triggerText.replace(0, triggerText.length(), " Headphone Disconnected");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxheadphonedisconnected.isChecked()){
					triggerText.replace(0, triggerText.length(), " Headphone Disconnected");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxincomingcall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxincomingcall.isChecked()){
					triggerText.replace(0, triggerText.length(), "Call is Coming");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxshake.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxshake.isChecked()){
					triggerText.replace(0, triggerText.length(), "Phone is Shaked");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});

		checkBoxsimcardchange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxsimcardchange.isChecked()){
					triggerText.replace(0, triggerText.length(), "Sim Card is Changed");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxunlockdevice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxunlockdevice.isChecked()){
					triggerText.replace(0, triggerText.length(), "Phone is Unlocked");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});

		checkBoxwifidetected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxwifidetected.isChecked()){
					triggerText.replace(0, triggerText.length(), "Wifi Detected");
					buttontriggernext.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		/*
		 * Back Button to go back to the Action Selection Screen
		 */
		buttontriggerback=(Button)findViewById(R.id.buttontriggerback);
		buttontriggerback.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(NewTriggerRuleActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});


		/*
		 * Create Button to create the Rule and Switch the activity to ReviewRule 
		 */
		buttontriggernext=(Button)findViewById(R.id.buttonnext);
		buttontriggernext.setEnabled(false);
		buttontriggernext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent actionRuleIntent=new Intent(NewTriggerRuleActivity.this,NewActionRuleActivity.class);
				triggerrule=triggerText.toString();
				actionRuleIntent.putExtra("param1",triggerrule);
				Toast.makeText(getApplicationContext(), triggerrule, Toast.LENGTH_SHORT).show();
				actionRuleIntent.putExtra("param1",triggerrule);
				startActivity(actionRuleIntent);
			}
		});

		/*
		 * 
		 * Cancel Button to cancel the current selection of Trigger 
		 */
		triggerbuttoncancel=(Button)findViewById(R.id.triggerbuttoncancel);
		triggerbuttoncancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {								
				//code for changing State
				triggerText.replace(0, triggerText.length(), "");	
				buttontriggernext.setEnabled(false);
				unCheckBox();
				changeCheckBoxState(true);

			}
		});
	}

	public void changeCheckBoxState(boolean state) {
		checkBoxlowbattery.setEnabled(state);
		checkBoxbatteryfull.setEnabled(state);		
		checkBoxheadphoneconnected.setEnabled(state);		
		checkBoxheadphonedisconnected.setEnabled(state);
		checkBoxincomingcall.setEnabled(state);
		checkBoxshake.setEnabled(state);
		checkBoxsimcardchange.setEnabled(state);
		checkBoxunlockdevice.setEnabled(state);
		checkBoxwifidetected.setEnabled(state);
	}

	public void unCheckBox() {
		checkBoxlowbattery.setChecked(false);
		checkBoxbatteryfull.setChecked(false);		
		checkBoxheadphoneconnected.setChecked(false);		
		checkBoxheadphonedisconnected.setChecked(false);
		checkBoxincomingcall.setChecked(false);
		checkBoxshake.setChecked(false);
		checkBoxsimcardchange.setChecked(false);
		checkBoxunlockdevice.setChecked(false);
		checkBoxwifidetected.setChecked(false);
	}
}
