package de.mia.list;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	Button www;
	Button video;
	Button trainer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		www = (Button)findViewById(R.id.btn_gotoWebsite);
		video = (Button)findViewById(R.id.btn_powerProgramm);
		trainer = (Button)findViewById(R.id.btn_trainer);
		trainer.setOnClickListener(this);
		www.setOnClickListener(this);
		video.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == www){
			Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.freeness.de/Kurse.html"));
			startActivity(browser);
		}
		else if(v == video) {
			Intent intent = new Intent(getApplicationContext(), ListItemVideoActivity.class);
			intent.putExtra("videoPath", 0);
			startActivity(intent);
			
		}
		else if(v == trainer) {
			Intent intent = new Intent(getApplicationContext(), ListActivity.class);
			startActivity(intent);
		}
		
	}

}
