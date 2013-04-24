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
 * Activity for selecting Action for the rule.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * 
 */
public class NewActionRuleActivity extends Activity
{
	Button buttonback, buttonnext;
	CheckBox checkBoxwifi, checkBoxsilent, checkBoxflight, checkBoxbeep, checkBoxsms;
	StringBuffer actionString = new StringBuffer();
	String actionrule;
	boolean checkenabled = true;
	boolean ischecked = false;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_newrule);

		this.buttonnext = (Button)this.findViewById(R.id.buttonnext);
		this.buttonnext.setEnabled(false);
		this.buttonnext.setVisibility(View.INVISIBLE);

		this.buttonback = (Button)this.findViewById(R.id.buttonback);
		this.buttonback.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent mainActivityIntent = new Intent(NewActionRuleActivity.this, MainActivity.class);
				NewActionRuleActivity.this.startActivity(mainActivityIntent);

			}
		});

		/**
		 * Declaring UI items for Actions
		 */
		this.checkBoxwifi = (CheckBox)this.findViewById(R.id.checkBoxwifi);
		this.checkBoxsilent = (CheckBox)this.findViewById(R.id.checkBoxsilent);
		this.checkBoxflight = (CheckBox)this.findViewById(R.id.checkBoxflight);
		this.checkBoxbeep = (CheckBox)this.findViewById(R.id.checkBoxbeep);
		this.checkBoxsms = (CheckBox)this.findViewById(R.id.checkBoxsms);

		/**
		 * Logic for creating Text on selection of particular Action
		 **/

		this.checkBoxwifi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxwifi.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Put Wifi on ");
					NewActionRuleActivity.this.checkenabled = false;
					NewActionRuleActivity.this.buttonnext.setEnabled(true);
					NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
				}
				else
				{
					NewActionRuleActivity.this.actionString = null;
					Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
					NewActionRuleActivity.this.startActivity(intent);
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
					NewActionRuleActivity.this.buttonnext.setEnabled(true);
					NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
				}
				else
				{
					NewActionRuleActivity.this.actionString = null;
					Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
					NewActionRuleActivity.this.startActivity(intent);
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
					NewActionRuleActivity.this.buttonnext.setEnabled(true);
					NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
				}
				else
				{
					NewActionRuleActivity.this.actionString = null;
					Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
					NewActionRuleActivity.this.startActivity(intent);
				}
			}
		});

		this.checkBoxbeep.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewActionRuleActivity.this.checkBoxbeep.isChecked() )
				{
					NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(),
							"Put Phone on Beep");
					NewActionRuleActivity.this.checkenabled = false;
					NewActionRuleActivity.this.buttonnext.setEnabled(true);
					NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
				}
				else
				{
					NewActionRuleActivity.this.actionString = null;
					Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
					NewActionRuleActivity.this.startActivity(intent);
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
					NewActionRuleActivity.this.buttonnext.setEnabled(true);
					NewActionRuleActivity.this.buttonnext.setVisibility(View.VISIBLE);
				}
				else
				{
					NewActionRuleActivity.this.actionString = null;
					Intent intent = new Intent(NewActionRuleActivity.this, NewActionRuleActivity.class);
					NewActionRuleActivity.this.startActivity(intent);
				}
			}
		});

		this.buttonnext.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// code for changing State
				Intent newTriggerRuleIntent = new Intent(NewActionRuleActivity.this, NewTriggerRuleActivity.class);
				NewActionRuleActivity.this.actionrule = NewActionRuleActivity.this.actionString.toString();
				newTriggerRuleIntent.putExtra("param1", NewActionRuleActivity.this.actionrule);
				NewActionRuleActivity.this.startActivity(newTriggerRuleIntent);
			}
		});

	}


	public void changeCheckBoxState(boolean state)
	{
		this.checkBoxsilent.setEnabled(state);
		this.checkBoxflight.setEnabled(state);
		this.checkBoxbeep.setEnabled(state);
		this.checkBoxsms.setEnabled(state);
	}

}
