package org.punegdg.kinosense;

import org.punegdg.kinosense.actions.ActionIdConstants;
import org.punegdg.kinosense.eventsource.SensorService;
import org.punegdg.kinosense.rule.RuleManager;

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
 * Activity for selecting Action for the rule.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 */
public class NewActionRuleActivity extends Activity {
    Button buttoncancel, buttoncreate;
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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        /*
         * <<<<<<< HEAD this.setContentView(R.layout.activity_newrule); this.buttonnext = (Button)this.findViewById(R.id.buttonnext);
         * this.buttonnext.setEnabled(false); this.buttonnext.setVisibility(View.INVISIBLE); this.buttonback =
         * (Button)this.findViewById(R.id.buttonback); this.buttonback.setOnClickListener(new OnClickListener() { public void onClick(View v) { //
         * TODO Auto-generated method stub Intent mainActivityIntent = new Intent(NewActionRuleActivity.this, MainActivity.class);
         * NewActionRuleActivity.this.startActivity(mainActivityIntent); =======
         */
        Intent newActionRuleIntent = this.getIntent();
        this.ruleText.append(newActionRuleIntent.getStringExtra("triggerrule"));
        triggerID = newActionRuleIntent.getIntExtra("triggerID", 0);
        this.setContentView(R.layout.activity_newrule);

        // Code for creating the new rule and navigate to the Rule Review View
        this.buttoncreate = (Button) this.findViewById(R.id.buttoncreate);
        this.buttoncreate.setEnabled(false);
        this.buttoncreate.setVisibility(View.INVISIBLE);
        this.buttoncancel = (Button) this.findViewById(R.id.buttoncancel);

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
                if (NewActionRuleActivity.this.checkBoxWifiON.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Set WiFi ON");
                    actionID = ActionIdConstants.WIFI_ON;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxWifiON.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });

        this.checkBoxWifiOFF.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxWifiOFF.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Set WiFi OFF");
                    actionID = ActionIdConstants.WIFI_OFF;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxWifiOFF.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });

        this.checkBoxsilent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxsilent.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Put Phone on Silent");
                    actionID = ActionIdConstants.PHONE_SILENT;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxsilent.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });
        this.checkBoxflight.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxflight.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Put Phone on Flight Mode");
                    actionID = ActionIdConstants.FLIGHT_MODE_ON;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxflight.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });
        this.checkBoxsms.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxsms.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Send SMS");
                    actionID = ActionIdConstants.SMS_SEND;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxsms.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });
        this.checkBoxalarm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxalarm.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Start Alarm");
                    actionID = ActionIdConstants.FLIGHT_MODE_ON;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxalarm.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });
        this.checkBoxshownotification.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxshownotification.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Show Notification");
                    actionID = ActionIdConstants.NOTIFICATION;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxshownotification.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });
        this.checkBoxvibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (NewActionRuleActivity.this.checkBoxvibrate.isChecked()) {
                    NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "Vibrate");
                    actionID = ActionIdConstants.VIBRATE_ACTION;
                    NewActionRuleActivity.this.checkenabled = false;
                    NewActionRuleActivity.this.changeCheckBoxState(NewActionRuleActivity.this.checkenabled);
                    NewActionRuleActivity.this.checkBoxvibrate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setEnabled(true);
                    NewActionRuleActivity.this.buttoncreate.setVisibility(View.VISIBLE);
                } else {
                    NewActionRuleActivity.this.CancelSelection();
                }
            }
        });

        // Code for creating the new rule and navigate to the Rule Review View
        this.buttoncreate.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                NewActionRuleActivity.this.ruleText.append("," + NewActionRuleActivity.this.actionString.toString());
                String rule = NewActionRuleActivity.this.ruleText.toString();
                Toast.makeText(NewActionRuleActivity.this.getApplicationContext(), rule, Toast.LENGTH_SHORT).show();
                NewActionRuleActivity.this.createRule();
                Intent restartService = new Intent(NewActionRuleActivity.this, SensorService.class);
                restartService.putExtra(ActionIdConstants.TRIGGERID, triggerID);
                restartService.putExtra(ActionIdConstants.ACTION_STATE, NewActionRuleActivity.this.enabled);
                NewActionRuleActivity.this.startService(restartService);
                Intent ruleReviewintent = new Intent(NewActionRuleActivity.this, RuleReviewActivity.class);
                NewActionRuleActivity.this.startActivity(ruleReviewintent);

            }
        });

    }

    public void CancelSelection() {
        NewActionRuleActivity.this.actionString.replace(0, NewActionRuleActivity.this.actionString.length(), "");
        actionID = -1;
        NewActionRuleActivity.this.changeCheckBoxState(true);
        NewActionRuleActivity.this.unCheckBox();
        NewActionRuleActivity.this.buttoncreate.setEnabled(false);
        NewActionRuleActivity.this.buttoncreate.setVisibility(View.INVISIBLE);
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

    private void createRule() {
        RuleManager rulemanager = RuleManager.getInstance();
        if ((actionID != -1) || (triggerID != -1)) {
            rulemanager.createRules(NewActionRuleActivity.triggerID, NewActionRuleActivity.actionID, this.ruleText.toString(), this.additionInfo,
                    this.enabled, this.getApplicationContext());
        }
    }
}
