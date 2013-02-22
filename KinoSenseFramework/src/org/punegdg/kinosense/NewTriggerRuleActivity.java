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

/**
 * 
 * Activity for selecting Trigger for the rule. 
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * 
 */
public class NewTriggerRuleActivity extends Activity {
	private Button buttontriggerback,buttontriggercreate,triggerbuttoncancel;
	CheckBox checkBoxlowbattery,checkBoxbatteryfull,checkBoxhome,checkBoxoffice,checkBoxmeeting;
	private StringBuffer triggerText=new StringBuffer();
	private StringBuffer ruleText=new StringBuffer();
	boolean triggerEnabled=true;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent newTriggerRuleIntent = getIntent();
		final String str1 = newTriggerRuleIntent.getStringExtra("param1");		
		setContentView(R.layout.activity_newtrigger);
		
		
		
		//UI widget for Creating Trigger
		checkBoxlowbattery=(CheckBox)findViewById(R.id.checkBoxlowbattery);
		checkBoxbatteryfull=(CheckBox)findViewById(R.id.checkBoxbatteryfull);
		checkBoxhome=(CheckBox)findViewById(R.id.checkBoxhome);
		checkBoxoffice=(CheckBox)findViewById(R.id.checkBoxoffice);
		checkBoxmeeting=(CheckBox)findViewById(R.id.checkBoxmeeting);
		
		
		//Logic for selecting a  widget and creating Rule
		checkBoxlowbattery.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxlowbattery.isChecked()){
					triggerText.replace(0, triggerText.length(), " When Battery Low");
					buttontriggercreate.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxbatteryfull.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxbatteryfull.isChecked()){
					triggerText.replace(0, triggerText.length(), " When Battery Full");
					buttontriggercreate.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxhome.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxhome.isChecked()){
					triggerText.replace(0, triggerText.length(), " When at Home");
					buttontriggercreate.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
				
		checkBoxoffice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxoffice.isChecked()){
					triggerText.replace(0, triggerText.length(), " When at Office");
					buttontriggercreate.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		checkBoxmeeting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(checkBoxmeeting.isChecked()){
					triggerText.replace(0, triggerText.length(), " When at Meeting");
					buttontriggercreate.setEnabled(true);
					triggerEnabled=false;					
					changeCheckBoxState(triggerEnabled);
				}
			}
		});
		
		/*
		 * BAck Button to go back to the Action Selection Screen
		 */
		buttontriggerback=(Button)findViewById(R.id.buttontriggerback);
		buttontriggerback.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(NewTriggerRuleActivity.this,NewActionRuleActivity.class);
				startActivity(intent);
			}
		});
		
		
		/*
		 * Create Button to create the Rule and Switch the activity to ReviewRule 
		 */
		buttontriggercreate=(Button)findViewById(R.id.buttontriggercreate);
		buttontriggercreate.setEnabled(false);
		buttontriggercreate.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent ruleReviewintent=new Intent(NewTriggerRuleActivity.this,RuleReviewActivity.class);
				ruleText.append(str1);
				ruleText.append(""+ triggerText );
				String rule=ruleText.toString();
				//Toast.makeText(getApplicationContext(), rule, Toast.LENGTH_SHORT).show();
				ruleReviewintent.putExtra("param2",rule);
				startActivity(ruleReviewintent);
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
				unCheckBox();
				changeCheckBoxState(true);
				
			}
		});
	}
	
	public void changeCheckBoxState(boolean state) {
		checkBoxlowbattery.setEnabled(state);
		checkBoxbatteryfull.setEnabled(state);
		checkBoxhome.setEnabled(state);
		checkBoxoffice.setEnabled(state);	
		checkBoxmeeting.setEnabled(state);
	}
	
	public void unCheckBox() {
		checkBoxlowbattery.setChecked(false);
		checkBoxbatteryfull.setChecked(false);
		checkBoxhome.setChecked(false);
		checkBoxoffice.setChecked(false);
		checkBoxmeeting.setChecked(false);
	}
}
