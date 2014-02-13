package de.mia.list;


import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class ListItemVideoActivity extends Activity {

	
	private static ProgressDialog progressDialog;
	
	VideoView videoView;
	
	//String videoPath = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
	String videoPath = "http://www.freeness.de/Media/zipp.mp4";
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_video);

		videoView = (VideoView) findViewById(R.id.VideoView);

		progressDialog = ProgressDialog.show(ListItemVideoActivity.this, "",
				"Buffering video...", true);
		progressDialog.setCancelable(true);

		playVideo();

	}

	private void playVideo() {
		try {
			getWindow().setFormat(PixelFormat.TRANSLUCENT);
			MediaController mediaController = new MediaController(
					ListItemVideoActivity.this);
			mediaController.setAnchorView(videoView);

			Uri video = Uri.parse(videoPath);
			videoView.setMediaController(mediaController);
			videoView.setVideoURI(video);
			videoView.requestFocus();
			videoView.setOnPreparedListener(new OnPreparedListener() {

				public void onPrepared(MediaPlayer mp) {
					progressDialog.dismiss();
					videoView.start();
				}
			});

		} catch (Exception e) {
			progressDialog.dismiss();
			System.out.println("Video Play Error :" + e.toString());
			finish();
		}

	}
}
