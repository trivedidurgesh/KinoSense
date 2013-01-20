package org.punegdg.kinosense.triggers;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class UnlockTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger

{
	private Context context = null;


	public void onCreate(Context context)
	{

		this.context = context;
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_USER_PRESENT);
		context.registerReceiver(this.getBroadCastReceiver(), filter);

	}


	public BroadcastReceiver getBroadCastReceiver()
	{
		// TODO Auto-generated method stub
		return this;
	}


	public void onDestroy()
	{
		this.context.unregisterReceiver(this.getBroadCastReceiver());

	}


	@Override
	public void onReceive(Context context, Intent intent)
	{
		Intent bcIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
		bcIntent.putExtra("trigger", "PHONE_UNLOCKED");
		context.sendBroadcast(bcIntent);

	}

}
