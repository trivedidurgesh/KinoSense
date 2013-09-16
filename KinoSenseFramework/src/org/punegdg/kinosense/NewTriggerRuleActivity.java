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
 * Activity for selecting Trigger for the rule.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * @author "Ashish Kalbhor" <ashish.kalbhor@gmail.com>
 */
public class NewTriggerRuleActivity extends Activity {
    // Declaring The Button for the BACK , NEXT and CANCEL Actions
<<<<<<< HEAD
    private Button buttontriggernext, triggerbuttoncancel;
=======
    private Button buttontriggerback, buttontriggernext;
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5

    // Declaring The check boxes
    CheckBox checkBoxlowbattery, checkBoxbatteryfull, checkBoxheadphoneconnected, checkBoxheadphonedisconnected, checkBoxincomingcall, checkBoxshake,
    checkBoxsimcardchange, checkBoxunlockdevice, checkBoxwifidetected;
    private StringBuffer triggerText = new StringBuffer();
    String triggerrule;
    private static int triggerID;
    boolean triggerEnabled = true;

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_newtrigger);

        // UI widget for Creating Trigger
        this.checkBoxlowbattery = (CheckBox) this.findViewById(R.id.checkBoxlowbattery);
        this.checkBoxbatteryfull = (CheckBox) this.findViewById(R.id.checkBoxbatteryfull);
        this.checkBoxheadphoneconnected = (CheckBox) this.findViewById(R.id.checkBoxheadphoneconnected);
        this.checkBoxheadphonedisconnected = (CheckBox) this.findViewById(R.id.checkBoxheadphonedisconnected);
        this.checkBoxincomingcall = (CheckBox) this.findViewById(R.id.checkBoxincomingcall);
        this.checkBoxshake = (CheckBox) this.findViewById(R.id.checkBoxshake);
        this.checkBoxsimcardchange = (CheckBox) this.findViewById(R.id.checkBoxsimcardchange);
        this.checkBoxunlockdevice = (CheckBox) this.findViewById(R.id.checkBoxunlockdevice);
        this.checkBoxwifidetected = (CheckBox) this.findViewById(R.id.checkBoxwifidetected);
        this.checkBoxunlockdevice = (CheckBox) this.findViewById(R.id.checkBoxunlockdevice);
        this.checkBoxwifidetected = (CheckBox) this.findViewById(R.id.checkBoxwifidetected);

        // Logic for selecting a widget and creating Rule
        this.checkBoxlowbattery.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
                	setTrigger(TriggerIdConstants.BATTERY_LOW, " When Battery Low",NewTriggerRuleActivity.this.checkBoxlowbattery.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxlowbattery.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " When Battery Low");
                    triggerID = TriggerIdConstants.BATTERY_LOW;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxlowbattery.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });
        
        this.checkBoxbatteryfull.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
                setTrigger(TriggerIdConstants.BATTERY_FULL, " When Battery Full", NewTriggerRuleActivity.this.checkBoxbatteryfull.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxbatteryfull.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " When Battery Full");
                    triggerID = TriggerIdConstants.BATTERY_FULL;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxbatteryfull.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxheadphoneconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
            	setTrigger(TriggerIdConstants.HEADPHONE_CONNECTED, " Headphone Connected", NewTriggerRuleActivity.this.checkBoxheadphoneconnected.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxheadphoneconnected.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Headphone Connected");
                    triggerID = TriggerIdConstants.HEADPHONE_CONNECTED;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxheadphoneconnected.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
            	setTrigger(TriggerIdConstants.HEADPHONE_DISCONNECTED, " Headphone Disconnected", NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Headphone Disconnected");
                    triggerID = TriggerIdConstants.HEADPHONE_DISCONNECTED;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxincomingcall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
            	setTrigger(TriggerIdConstants.INCOMING_CALL, " Call is Coming", NewTriggerRuleActivity.this.checkBoxincomingcall.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxincomingcall.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Call is Coming");
                    triggerID = TriggerIdConstants.INCOMING_CALL;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxincomingcall.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxshake.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
            	setTrigger(TriggerIdConstants.DEVICE_SHAKING, " Phone is Shaked", NewTriggerRuleActivity.this.checkBoxshake.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxshake.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Phone is Shaked");
                    triggerID = TriggerIdConstants.DEVICE_SHAKING;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxshake.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxsimcardchange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
                setTrigger(TriggerIdConstants.SIM_CARD_CHANGED, " Sim Card is Changed", NewTriggerRuleActivity.this.checkBoxsimcardchange.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxsimcardchange.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Sim Card is Changed");
                    triggerID = TriggerIdConstants.SIM_CARD_CHANGED;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxsimcardchange.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxunlockdevice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
            	setTrigger(TriggerIdConstants.PHONE_UNLOCKED, " Phone is Unlocked", NewTriggerRuleActivity.this.checkBoxunlockdevice.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxunlockdevice.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Phone is Unlocked");
                    triggerID = TriggerIdConstants.PHONE_UNLOCKED;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxunlockdevice.setEnabled(true);

                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        this.checkBoxwifidetected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
<<<<<<< HEAD
            	setTrigger(TriggerIdConstants.WIFI_DETECTED, " Wifi Detected", NewTriggerRuleActivity.this.checkBoxwifidetected.isChecked());
=======
                if (NewTriggerRuleActivity.this.checkBoxwifidetected.isChecked()) {
                    NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), " Wifi Detected");
                    triggerID = TriggerIdConstants.WIFI_DETECTED;
                    NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
                    NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
                    NewTriggerRuleActivity.this.changeCheckBoxState(false);
                    NewTriggerRuleActivity.this.checkBoxwifidetected.setEnabled(true);
                } else {
                    NewTriggerRuleActivity.this.cancelSelection();
                }
            }
        });

        /*
         * Back Button to go back to the Action Selection Screen
         */
        this.buttontriggerback = (Button) this.findViewById(R.id.buttontriggerback);
        this.buttontriggerback.setOnClickListener(new OnClickListener() {

            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(NewTriggerRuleActivity.this, MainActivity.class);
                NewTriggerRuleActivity.this.startActivity(intent);
>>>>>>> f7df6587e869d8825c68c3da607f062addd144e5
            }
        });

        /*
         * Create Button to create the Rule and Switch the activity to ReviewRule
         */
        this.buttontriggernext = (Button) this.findViewById(R.id.buttonnext);
        this.buttontriggernext.setEnabled(false);
        this.buttontriggernext.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Intent actionRuleIntent = new Intent(NewTriggerRuleActivity.this, NewActionRuleActivity.class);
                NewTriggerRuleActivity.this.triggerrule = NewTriggerRuleActivity.this.triggerText.toString();
                actionRuleIntent.putExtra("triggerrule", NewTriggerRuleActivity.this.triggerrule);
                actionRuleIntent.putExtra("triggerID", triggerID);
                Toast.makeText(NewTriggerRuleActivity.this.getApplicationContext(), NewTriggerRuleActivity.this.triggerrule, Toast.LENGTH_SHORT)
                .show();
                NewTriggerRuleActivity.this.startActivity(actionRuleIntent);
            }
        });
    }

    public void changeCheckBoxState(final boolean state) {
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

    public void cancelSelection() {
        triggerID = -1;
        NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
        NewTriggerRuleActivity.this.buttontriggernext.setEnabled(false);
        NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.INVISIBLE);
        NewTriggerRuleActivity.this.changeCheckBoxState(true);
        NewTriggerRuleActivity.this.unCheckBox();
    }

    public void unCheckBox() {
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
    
    protected void onStart(){
    	super.onStart();
    	unCheckBox();
    }

    
    /**
	 * Set the Trigger values in NewTriggerRuleActivity's as per User Inputs.
	 * 
	 * @param actionId  -- int value from TriggerIdConstants.
	 * @param triggerText -- String Trigger Text.
	 * @param checked --  boolean Value of Trigger (Selected or Unselected).
	 */
    private void setTrigger(int actionId, String triggerText, boolean checked) {
		if(checked) {
			NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), triggerText);
            triggerID = actionId;
            NewTriggerRuleActivity.this.buttontriggernext.setEnabled(true);
            NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.VISIBLE);
		} else {
			triggerID = -1;
            NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
            NewTriggerRuleActivity.this.buttontriggernext.setEnabled(false);
            NewTriggerRuleActivity.this.buttontriggernext.setVisibility(View.INVISIBLE);
		}
	}
}
