package org.punegdg.kinosense;

import org.punegdg.kinosense.triggers.TriggerIdConstants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    // Declaring The check boxes
    CheckBox checkBoxlowbattery, checkBoxbatteryfull, checkBoxheadphoneconnected, checkBoxheadphonedisconnected, checkBoxincomingcall, checkBoxshake,
            checkBoxsimcardchange, checkBoxunlockdevice, checkBoxwifidetected;
    private StringBuffer triggerText = new StringBuffer();
    String triggerrule;
    private static int triggerID;
    boolean triggerEnabled = true;
    boolean triggerSelected;

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_newtrigger);

        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setTitle(R.string.triggerlist);

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
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.BATTERY_LOW, " When Battery Low",
                        NewTriggerRuleActivity.this.checkBoxlowbattery.isChecked());
            }
        });

        this.checkBoxbatteryfull.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.BATTERY_FULL, " When Battery Full",
                        NewTriggerRuleActivity.this.checkBoxbatteryfull.isChecked());
            }
        });

        this.checkBoxheadphoneconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.HEADPHONE_CONNECTED, " Headphone Connected",
                        NewTriggerRuleActivity.this.checkBoxheadphoneconnected.isChecked());
            }
        });

        this.checkBoxheadphonedisconnected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.HEADPHONE_DISCONNECTED, " Headphone Disconnected",
                        NewTriggerRuleActivity.this.checkBoxheadphonedisconnected.isChecked());
            }
        });

        this.checkBoxincomingcall.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.INCOMING_CALL, " Call is Coming",
                        NewTriggerRuleActivity.this.checkBoxincomingcall.isChecked());
            }
        });

        this.checkBoxshake.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.DEVICE_SHAKING, " Phone is Shaked",
                        NewTriggerRuleActivity.this.checkBoxshake.isChecked());
            }
        });

        this.checkBoxsimcardchange.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.SIM_CARD_CHANGED, " Sim Card is Changed",
                        NewTriggerRuleActivity.this.checkBoxsimcardchange.isChecked());
            }
        });

        this.checkBoxunlockdevice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.PHONE_UNLOCKED, " Phone is Unlocked",
                        NewTriggerRuleActivity.this.checkBoxunlockdevice.isChecked());
            }
        });

        this.checkBoxwifidetected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewTriggerRuleActivity.this.setTrigger(TriggerIdConstants.WIFI_DETECTED, " Wifi Detected",
                        NewTriggerRuleActivity.this.checkBoxwifidetected.isChecked());
            }
        });

    }

    private void startActionRuleActivity() {
        Intent actionRuleIntent = new Intent(NewTriggerRuleActivity.this, NewActionRuleActivity.class);
        NewTriggerRuleActivity.this.triggerrule = NewTriggerRuleActivity.this.triggerText.toString();
        actionRuleIntent.putExtra("triggerrule", NewTriggerRuleActivity.this.triggerrule);
        actionRuleIntent.putExtra("triggerID", triggerID);
        Toast.makeText(NewTriggerRuleActivity.this.getApplicationContext(), NewTriggerRuleActivity.this.triggerrule, Toast.LENGTH_SHORT).show();
        NewTriggerRuleActivity.this.startActivity(actionRuleIntent);
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

    @Override
    protected void onStart() {
        super.onStart();
        this.unCheckBox();
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.trigger_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case R.id.action_next:
            if (this.triggerSelected) {
                this.startActionRuleActivity();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Set the Trigger values in NewTriggerRuleActivity's as per User Inputs.
     * 
     * @param actionId
     *            -- int value from TriggerIdConstants.
     * @param triggerText
     *            -- String Trigger Text.
     * @param checked
     *            -- boolean Value of Trigger (Selected or Unselected).
     */
    private void setTrigger(final int actionId, final String triggerText, final boolean checked) {
        if (checked) {
            NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), triggerText);
            triggerID = actionId;
            this.triggerSelected = true;
        } else {
            triggerID = -1;
            NewTriggerRuleActivity.this.triggerText.replace(0, NewTriggerRuleActivity.this.triggerText.length(), "");
            this.triggerSelected = false;
        }
    }
}
