package de.mia.list;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ListItemDetailActivity extends Activity {

	String image;
	String entfernung;
	String name;
	String level;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_item_detail);
		image = getIntent().getStringExtra("Image");
		entfernung = getIntent().getStringExtra("Entfernung");
		name = getIntent().getStringExtra("Name");
		level = getIntent().getStringExtra("Level");
		
		TextView t1 = (TextView)findViewById(R.id.textView1);
		TextView t2 = (TextView)findViewById(R.id.textView2);
		TextView t3 = (TextView)findViewById(R.id.textView3);
		
		t1.setText(entfernung);
		t2.setText(name);
		t3.setText(level);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_item_detail, menu);
		return true;
	}

}
