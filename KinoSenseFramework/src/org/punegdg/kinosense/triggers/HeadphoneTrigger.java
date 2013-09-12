package org.punegdg.kinosense.triggers;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;
import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * This Trigger is for the action when User plugin the headphones
 * 
 * @author "Amruta Deshpande"<deshpande.amruta22@gmail.com>
 * 
 */

public class HeadphoneTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger
{

	/**
	 * Android's Application Context
	 */
	private Context context = null;


	public void onCreate(Context context)
	{
		this.context = context;
		IntentFilter inf = new IntentFilter();
		inf.addAction(Intent.ACTION_HEADSET_PLUG);
		context.registerReceiver(this.getBroadCastReceiver(), inf);
	}


	public BroadcastReceiver getBroadCastReceiver()
	{
		return this;
	}


	public void onDestroy()
	{
		this.context.unregisterReceiver(this.getBroadCastReceiver());
	}


	@Override
	public void onReceive(Context context, Intent intent)
	{

		if ( intent.getExtras().getInt("state") == 0 )
		// Headset Disconnected
		{
			Intent bcHIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			//bcHIntent.putExtra("trigger", "HEADSET_DISCONNECTED");
			bcHIntent.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.HEADPHONE_DISCONNECTED);
			context.sendBroadcast(bcHIntent);

		}
		else
		// Headset Connected
		{
			Intent bcHIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			//bcHIntent.putExtra("trigger", "HEADSET_CONNECTED");
			bcHIntent.putExtra(TriggerIdConstants.TIGGER_ID, TriggerIdConstants.HEADPHONE_CONNECTED);
			context.sendBroadcast(bcHIntent);
		}
	}	
	/*
	 * (non-Javadoc)
	 * @see android.content.BroadcastReceiver#peekService(android.content.Context, android.content.Intent)
	 */
	@Override
	public IBinder peekService(Context myContext, Intent service)
	{
		// TODO Auto-generated method stub
		return super.peekService(myContext, service);
	}
}