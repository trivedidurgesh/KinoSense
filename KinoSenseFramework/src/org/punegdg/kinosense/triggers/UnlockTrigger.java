package org.punegdg.kinosense.triggers;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * This Trigger is for the action when the device's screen is unlocked.
 * 
 * @author "Apurva Bhoite"<bhoiteapurva@gmail.com>
 */
public class UnlockTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger

{
    private Context context = null;

    public void onCreate(final Context context) {

        this.context = context;
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(this.getBroadCastReceiver(), filter);

    }

    public BroadcastReceiver getBroadCastReceiver() {
        // TODO Auto-generated method stub
        return this;
    }

    public void onDestroy() {
        this.context.unregisterReceiver(this.getBroadCastReceiver());
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Intent bcIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
        // bcIntent.putExtra("trigger", "PHONE_UNLOCKED");
        bcIntent.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.PHONE_UNLOCKED);
        context.sendBroadcast(bcIntent);
    }

}
