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
 * @author "Ashish Kalbhor" <ashish.kalbhor@gmail.com>
 * 
 */
public class NewTriggerRuleActivity extends Activity
{
	private Button buttontriggerback, buttontriggercreate;
	CheckBox checkBoxlowbattery, checkBoxbatteryfull, checkBoxhome, checkBoxoffice, checkBoxmeeting;
	private StringBuffer triggerText = new StringBuffer();
	private StringBuffer ruleText = new StringBuffer();
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
		Intent newTriggerRuleIntent = this.getIntent();
		final String str1 = newTriggerRuleIntent.getStringExtra("param1");
		this.setContentView(R.layout.activity_newtrigger);

		// UI widget for Creating Trigger
		this.checkBoxlowbattery = (CheckBox)this.findViewById(R.id.checkBoxlowbattery);
		this.checkBoxbatteryfull = (CheckBox)this.findViewById(R.id.checkBoxbatteryfull);
		this.checkBoxhome = (CheckBox)this.findViewById(R.id.checkBoxhome);
		this.checkBoxoffice = (CheckBox)this.findViewById(R.id.checkBoxoffice);
		this.checkBoxmeeting = (CheckBox)this.findViewById(R.id.checkBoxmeeting);

		this.buttontriggercreate = (Button)this.findViewById(R.id.buttontriggercreate);
		this.buttontriggercreate.setEnabled(false);
		this.buttontriggercreate.setVisibility(View.INVISIBLE);

		// Logic for selecting a widget and creating Rule
		this.checkBoxlowbattery.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxlowbattery.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" When Battery Low");
					NewTriggerRuleActivity.this.buttontriggercreate.setEnabled(true);
					NewTriggerRuleActivity.this.buttontriggercreate.setVisibility(View.VISIBLE);
				}
				else
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
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
					NewTriggerRuleActivity.this.buttontriggercreate.setEnabled(true);
					NewTriggerRuleActivity.this.buttontriggercreate.setVisibility(View.VISIBLE);
				}
				else
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
				}
			}
		});

		this.checkBoxhome.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxhome.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" When at Home");
					NewTriggerRuleActivity.this.buttontriggercreate.setEnabled(true);
					NewTriggerRuleActivity.this.buttontriggercreate.setVisibility(View.VISIBLE);
				}
				else
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
				}
			}
		});

		this.checkBoxoffice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxoffice.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" When at Office");
					NewTriggerRuleActivity.this.buttontriggercreate.setEnabled(true);
					NewTriggerRuleActivity.this.buttontriggercreate.setVisibility(View.VISIBLE);
				}
				else
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
				}
			}
		});

		this.checkBoxmeeting.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				if ( NewTriggerRuleActivity.this.checkBoxmeeting.isChecked() )
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(),
							" When at Meeting");
					NewTriggerRuleActivity.this.buttontriggercreate.setEnabled(true);
					NewTriggerRuleActivity.this.buttontriggercreate.setVisibility(View.VISIBLE);
				}
				else
				{
					NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
				}
			}
		});

		/*
		 * BAck Button to go back to the Action Selection Screen
		 */
		this.buttontriggerback = (Button)this.findViewById(R.id.buttontriggerback);
		this.buttontriggerback.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewTriggerRuleActivity.this, NewActionRuleActivity.class);
				NewTriggerRuleActivity.this.startActivity(intent);
			}
		});

		/*
		 * Create Button to create the Rule and Switch the activity to ReviewRule
		 */
		this.buttontriggercreate.setOnClickListener(new OnClickListener() {

			public void onClick(View v)
			{
				// TODO Auto-generated method stub

				Intent ruleReviewintent = new Intent(NewTriggerRuleActivity.this, RuleReviewActivity.class);
				NewTriggerRuleActivity.this.ruleText.append(str1);
				NewTriggerRuleActivity.this.ruleText.append("" + NewTriggerRuleActivity.this.triggerText);
				String rule = NewTriggerRuleActivity.this.ruleText.toString();
				ruleReviewintent.putExtra("param2", rule);
				NewTriggerRuleActivity.this.startActivity(ruleReviewintent);
			}
		});

	}

}
