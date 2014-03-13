package de.mia.list;


import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class ListItemVideoActivity extends Activity {

	
	private static ProgressDialog progressDialog;
	
	VideoView videoView;
	String videoPath;
	
	//String videoPath = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";

	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_video);

		videoView = (VideoView) findViewById(R.id.VideoView);

		progressDialog = ProgressDialog.show(ListItemVideoActivity.this, "",
				"Buffering video...", true);
		progressDialog.setCancelable(true);

		int marker = getIntent().getIntExtra("videoPath",0);
		
		playVideo(marker);

	}

	private void playVideo(int videoMarker) {
		try {
			getWindow().setFormat(PixelFormat.TRANSLUCENT);
			MediaController mediaController = new MediaController(
					ListItemVideoActivity.this);
			mediaController.setAnchorView(videoView);
			
			switch(videoMarker) {
		    case 0:
		    	videoPath = "http://www.freeness.de/Media/zipp.mp4";
		        break;
		    case 1:
		    	videoPath = "http://www.freeness.de/video/video8.mp4";
		        break;
		    case 2:
		    	videoPath = "http://www.freeness.de/video/video5.mp4";
		        break;
		    case 3:
		    	videoPath = "http://www.freeness.de/video/video1.mp4";
		        break;
		    case 4:
		    	videoPath = "http://www.freeness.de/video/video3.mp4";
		        break;
		    case 5:
		    	videoPath = "http://www.freeness.de/video/video2.mp4";
		        break;
		    case 6:
		    	videoPath = "http://www.freeness.de/video/video4.mp4";
		        break;
		    case 7:
		    	videoPath = "http://www.freeness.de/video/video7.mp4";
		        break;
		    case 8:
		    	videoPath = "http://www.freeness.de/video/video9.mp4";
		        break;
		    case 9:
		    	videoPath = "http://www.freeness.de/video/video6.mp4";
		        break;
		    case 10:
		    	videoPath = "http://www.freeness.de/video/video10.mp4";
		        break;
		    case 11:
		    	videoPath = "http://www.freeness.de/video/video11.mp4";
		        break;
		    case 12:
		    	videoPath = "http://www.freeness.de/video/video12.3GP";
		        break;
		    case 13:
		    	videoPath = "http://www.freeness.de/video/video13.3GP";
		        break;
		    case 14:
		    	videoPath = "http://www.freeness.de/video/video14.3GP";
		        break;
		    case 15:
		    	videoPath = "http://www.freeness.de/video/video15.3GP";
		        break;
		        
		    default:
		    	videoPath = "http://www.freeness.de/Media/zipp.mp4";
		}
			
Log.w("video",videoPath);
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
