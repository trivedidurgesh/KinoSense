package org.punegdg.kinosense;

import org.punegdg.kinosense.actions.ActionIdConstants;
import org.punegdg.kinosense.eventsource.SensorService;
import org.punegdg.kinosense.rule.RuleManager;

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
import android.widget.ToggleButton;

/**
 * Activity for selecting Action for the rule.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 */
public class NewActionRuleActivity extends Activity {
    ToggleButton toggleButtonwifi;
    CheckBox checkBoxWifiON, checkBoxWifiOFF, checkBoxsilent, checkBoxflight, checkBoxbeep, checkBoxsms, checkBoxalarm, checkBoxshownotification,
            checkBoxvibrate;
    StringBuffer actionString = new StringBuffer("Set WiFi OFF");
    private static int actionID;
    private static int triggerID;
    private StringBuffer ruleText = new StringBuffer();
    private String additionInfo = "";
    private String enabled = "true";
    String actionrule;
    boolean checkenabled = true;
    boolean ischecked = false;
    private boolean actionSelected;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        this.getActionBar().setDisplayHomeAsUpEnabled(true);
        this.getActionBar().setTitle(R.string.actionlist);

        Intent newActionRuleIntent = this.getIntent();
        this.ruleText.append(newActionRuleIntent.getStringExtra("triggerrule"));
        triggerID = newActionRuleIntent.getIntExtra("triggerID", 0);
        this.setContentView(R.layout.activity_newrule);

        /**
         * Declaring UI items for Actions
         */
        // Initializing The Check Boxes
        this.checkBoxWifiON = (CheckBox) this.findViewById(R.id.checkBoxWifiOn);
        this.checkBoxWifiOFF = (CheckBox) this.findViewById(R.id.checkBoxWifiOff);
        this.checkBoxsilent = (CheckBox) this.findViewById(R.id.checkBoxsilent);
        this.checkBoxflight = (CheckBox) this.findViewById(R.id.checkBoxflight);
        this.checkBoxsms = (CheckBox) this.findViewById(R.id.checkBoxsms);
        this.checkBoxalarm = (CheckBox) this.findViewById(R.id.checkBoxalarm);
        this.checkBoxshownotification = (CheckBox) this.findViewById(R.id.checkBoxshownotification);
        this.checkBoxvibrate = (CheckBox) this.findViewById(R.id.checkBoxvibrate);

        /**
         * Logic for creating Text on selection of particular Action
         **/
        this.checkBoxWifiON.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxWifiON.isChecked(), "Set WiFi ON", ActionIdConstants.WIFI_ON);
            }
        });

        this.checkBoxWifiOFF.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxWifiOFF.isChecked(), "Set WiFi OFF",
                        ActionIdConstants.WIFI_OFF);
            }
        });

        this.checkBoxsilent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxsilent.isChecked(), "Put Phone on Silent",
                        ActionIdConstants.PHONE_SILENT);
            }
        });
        this.checkBoxflight.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxflight.isChecked(), "Put Phone on Flight Mode",
                        ActionIdConstants.FLIGHT_MODE_ON);
            }
        });
        this.checkBoxsms.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxsms.isChecked(), "Send SMS", ActionIdConstants.SMS_SEND);
            }
        });
        this.checkBoxalarm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxalarm.isChecked(), "Start Alarm",
                        ActionIdConstants.FLIGHT_MODE_ON);
            }
        });
        this.checkBoxshownotification.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxshownotification.isChecked(), "Show Notification",
                        ActionIdConstants.NOTIFICATION);
            }
        });
        this.checkBoxvibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                NewActionRuleActivity.this.setAction(NewActionRuleActivity.this.checkBoxvibrate.isChecked(), "Vibrate",
                        ActionIdConstants.VIBRATE_ACTION);
            }
        });

    }

    private void setAction(final boolean isChecked, final String actionString, final int actionId) {
        if (isChecked) {
            NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), actionString);
            actionID = actionId;
            NewActionRuleActivity.this.checkenabled = false;
            NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
            NewActionRuleActivity.this.checkBoxvibrate.setEnabled(true);
            NewActionRuleActivity.this.actionSelected = true;
        } else {
            NewActionRuleActivity.this.CancelSelection();
        }
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
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
        case R.id.action_create:
            if (this.actionSelected) {
                this.startRuleReviewActivity();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 
     */
    private void startRuleReviewActivity() {
        NewActionRuleActivity.this.ruleText.append("," + NewActionRuleActivity.this.actionString.toString());
        String rule = NewActionRuleActivity.this.ruleText.toString();
        boolean isCreated = NewActionRuleActivity.this.createRule();
        if (isCreated) {
            Toast.makeText(NewActionRuleActivity.this.getApplicationContext(), rule, Toast.LENGTH_SHORT).show();
            Intent restartService = new Intent(NewActionRuleActivity.this, SensorService.class);
            restartService.putExtra(ActionIdConstants.TRIGGERID, triggerID);
            restartService.putExtra(ActionIdConstants.ACTION_STATE, NewActionRuleActivity.this.enabled);
            NewActionRuleActivity.this.startService(restartService);
            Intent ruleReviewintent = new Intent(NewActionRuleActivity.this, RuleReviewActivity.class);
            NewActionRuleActivity.this.startActivity(ruleReviewintent);
        } else {
            Toast.makeText(NewActionRuleActivity.this.getApplicationContext(), R.string.message_rule_already_exist, Toast.LENGTH_LONG).show();
        }
    }

    public void CancelSelection() {
        NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "");
        actionID = -1;
        NewActionRuleActivity.this.changeCheckBoxState(true);
        NewActionRuleActivity.this.unCheckBox();
        this.actionSelected = false;
    }

    public void changeCheckBoxState(final boolean state) {
        this.checkBoxWifiON.setEnabled(state);
        this.checkBoxWifiOFF.setEnabled(state);
        this.checkBoxsilent.setEnabled(state);
        this.checkBoxflight.setEnabled(state);
        this.checkBoxsms.setEnabled(state);
        this.checkBoxalarm.setEnabled(state);
        this.checkBoxshownotification.setEnabled(state);
        this.checkBoxvibrate.setEnabled(state);
    }

    public void unCheckBox() {
        this.checkBoxWifiON.setChecked(false);
        this.checkBoxWifiOFF.setChecked(false);
        this.checkBoxsilent.setChecked(false);
        this.checkBoxflight.setChecked(false);
        this.checkBoxsms.setChecked(false);
        this.checkBoxalarm.setChecked(false);
        this.checkBoxshownotification.setChecked(false);
        this.checkBoxvibrate.setChecked(false);
    }

    private boolean createRule() {
        RuleManager rulemanager = RuleManager.getInstance();
        boolean result = false;
        if ((actionID != -1) || (triggerID != -1)) {
            result = rulemanager.createRule(NewActionRuleActivity.triggerID, NewActionRuleActivity.actionID, this.ruleText.toString(),
                    this.additionInfo, this.enabled, this.getApplicationContext());
        }
        return result;
    }
}
