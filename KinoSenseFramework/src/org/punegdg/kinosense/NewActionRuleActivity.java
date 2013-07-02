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
public class NewActionRuleActivity extends Activity {
	Button buttonback,buttoncancel,buttoncreate;
	ToggleButton toggleButtonwifi;
	CheckBox checkBoxsilent,checkBoxflight,checkBoxbeep,checkBoxsms,checkBoxalarm,checkBoxshownotification,checkBoxvibrate; 
	StringBuffer  actionString=new StringBuffer("Set WiFi OFF");
	private StringBuffer ruleText=new StringBuffer();
	String actionrule;
	boolean checkenabled=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent newActionRuleIntent = getIntent();
		final String str1 = newActionRuleIntent.getStringExtra("param1");	
		setContentView(R.layout.activity_newrule);
		
		
		buttonback=(Button)findViewById(R.id.buttonback);
		buttonback.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent triggerIntent=new Intent(NewActionRuleActivity.this,NewTriggerRuleActivity.class);
				startActivity(triggerIntent);				
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
			checkBoxsms=(CheckBox)findViewById(R.id.checkBoxsms);
			checkBoxalarm=(CheckBox)findViewById(R.id.checkBoxalarm);
			checkBoxshownotification=(CheckBox)findViewById(R.id.checkBoxshownotification);
			checkBoxvibrate=(CheckBox)findViewById(R.id.checkBoxvibrate);			
		
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
				checkBoxalarm.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxalarm.isChecked()){							
							actionString.replace(0, actionString.length(), "Start Alarm");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
				checkBoxshownotification.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxshownotification.isChecked()){							
							actionString.replace(0, actionString.length(), "Show Notification");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
				checkBoxvibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {					
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(checkBoxvibrate.isChecked()){							
							actionString.replace(0, actionString.length(), "Vibrate");
							checkenabled=false;
							//buttonnext.setEnabled(true);
							changeCheckBoxState(checkenabled);	
							toggleButtonwifi.setEnabled(false);
						}						
					}
				});
		
				//Code for creating the new rule and navigate to the Rule Review View
				buttoncreate=(Button)findViewById(R.id.buttoncreate);		
				buttoncreate.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {								
				//code for changing State
				Intent ruleReviewintent=new Intent(NewActionRuleActivity.this,RuleReviewActivity.class);
				ruleText.append(str1);
				actionrule=actionString.toString();
				ruleText.append(" "+ actionrule );
				String rule=ruleText.toString();
				Toast.makeText(getApplicationContext(), rule, Toast.LENGTH_SHORT).show();
				ruleReviewintent.putExtra("param2",rule);
				startActivity(ruleReviewintent);
			}
		});

				//Code for cancel the current selection
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
			checkBoxsms.setEnabled(state);
			checkBoxalarm.setEnabled(state);
			checkBoxshownotification.setEnabled(state);
			checkBoxvibrate.setEnabled(state);
	}

}
