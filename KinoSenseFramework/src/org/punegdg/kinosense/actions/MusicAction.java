package org.punegdg.kinosense.actions;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

/**
 * Action which can start and stop music playback.
 * 
 * <code>
 * 		AbstractAction action = new MusicAction();
 * 		// Start music
 * 		context.startService(new Intent(context, MusicAction.class));
 * 		// Stop music
 * 		context.stopService(new Intent(context, MusicAction.class));
 * </code>
 * 
 * @author "Apurva Bhoite"<bhoiteapurva@gmail.com>
 * 
 */
public class MusicAction extends Service
{
	private MediaPlayer mediaplayer = null;


	@Override
	public void onCreate()
	{
		super.onCreate();
		Uri uri = Uri.parse("/sdcard/external_sd/AyeUdiUdi.mp3"); // address of the song, can vary.
		this.mediaplayer = MediaPlayer.create(this, uri);
		this.mediaplayer.setLooping(false);
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
		this.mediaplayer.stop();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		this.mediaplayer.start();
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
	}


	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

}
