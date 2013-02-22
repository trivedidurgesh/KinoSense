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
import android.widget.ToggleButton;

/**
 * 
 * Activity for selecting Action for the rule. 
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * 
 */
public class NewActionRuleActivity extends Activity {
	Button buttonback,buttoncancel,buttonnext;
	ToggleButton toggleButtonwifi;
	CheckBox checkBoxsilent,checkBoxflight,checkBoxbeep,checkBoxsms; 
	StringBuffer  actionString=new StringBuffer("Set WiFi OFF");
	String actionrule;
	boolean checkenabled=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newrule);
		
		
		buttonback=(Button)findViewById(R.id.buttonback);
		buttonback.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mainActivityIntent=new Intent(NewActionRuleActivity.this,MainActivity.class);
				startActivity(mainActivityIntent);
				
			}
		});
		
		/**
		 * Declaring UI items for Actions 
		 */		
			
			toggleButtonwifi=(ToggleButton)findViewById(R.id.toggleButtonwifi);
			
			 //by default making Wifibutton focus, means if user just clicked on next button wifi off text will go to next activity
			toggleButtonwifi.setFocusable(true);
			toggleButtonwifi.requestFocus();
			checkBoxsilent=(CheckBox)findViewById(R.id.checkBoxsilent);
			checkBoxflight=(CheckBox)findViewById(R.id.checkBoxflight);
			checkBoxbeep=(CheckBox)findViewById(R.id.checkBoxbeep);
			checkBoxsms=(CheckBox)findViewById(R.id.checkBoxsms);
		
		/**
		 * Logic for creating Text on selection of particular Action
		 **/
				
				toggleButtonwifi.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						if(toggleButtonwifi.isChecked()){
							actionString.replace(0, actionString.length(), "Set Wifi ON");	
							//buttonnext.setEnabled(true);
							
							checkenabled=false;
							changeCheckBoxState(checkenabled);							
							
						}
						else{
							actionString.replace(0, actionString.length(), "Set WiFi OFF");									
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
						}
					}
				});			
				checkBoxsilent.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxsilent.isChecked()){
							actionString.replace(0, actionString.length(), "Put Phone on Silent");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
				
				checkBoxflight.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxflight.isChecked()){							
							actionString.replace(0, actionString.length(), "Put Phone on Flight Mode");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
				
				checkBoxbeep.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxbeep.isChecked()){							
							actionString.replace(0, actionString.length(), "Put Phone on Beep");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
				
				checkBoxsms.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxsms.isChecked()){							
							actionString.replace(0, actionString.length(), "Send SMS");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
		
		
		buttonnext=(Button)findViewById(R.id.buttonnext);		
		buttonnext.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {								
				//code for changing State
				Intent newTriggerRuleIntent=new Intent(NewActionRuleActivity.this,NewTriggerRuleActivity.class);
				actionrule=actionString.toString();
				newTriggerRuleIntent.putExtra("param1",actionrule);
				//Toast.makeText(getApplicationContext(), actionrule, Toast.LENGTH_SHORT).show();
				startActivity(newTriggerRuleIntent);				
			}
		});

		
		buttoncancel=(Button)findViewById(R.id.buttoncancel);
		buttoncancel.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {								
				//code for changing State
				actionString=null;
				//changeCheckBoxState(true);
				Intent intent=new Intent(NewActionRuleActivity.this,NewActionRuleActivity.class);
				startActivity(intent);
			}
		});
		
	}
	
	
	public void changeCheckBoxState(boolean state) {
			checkBoxsilent.setEnabled(state);
			checkBoxflight.setEnabled(state);
			checkBoxbeep.setEnabled(state);
			checkBoxsms.setEnabled(state);		
	}

}
