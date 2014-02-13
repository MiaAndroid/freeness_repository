package de.mia.list;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends Activity {

	 LinearLayout layoutOfPopup;
	 PopupWindow popupMessage;
	 Button insidePopupButton;
	 Button toGoogleMaps;
	 TextView popupText;
	
	 ListView list;
     CustomAdapter adapter;
     public  ListActivity CustomListView = null;
     public  ArrayList<ListModel> ListViewValuesArr = new ArrayList<ListModel>();
      
     @Override
     protected void onCreate(Bundle savedInstanceState) {


         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_list);
          
         CustomListView = this;
          
         getDataFromDataStore();
         
         
         /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
         setListData();
          
         Resources res =getResources();
         list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
          
         /**************** Create Custom Adapter *********/
         adapter=new CustomAdapter( CustomListView, ListViewValuesArr,res );
         list.setAdapter( adapter );
         
         initPopUp();
     }
     
     public void getDataFromDataStore() {
    	 Thread thread = new Thread(new Runnable() {
 			@Override
 			public void run() {
 				try {
 					AndroidHttpClient client = AndroidHttpClient
 							.newInstance("userAgent");
 					HttpGet request = new HttpGet(
 							"http://freenessspot.appspot.com/freenessspot");

 					HttpResponse response = null;
 					try {
 						response = client.execute(request);
 						Log.w("info", "request: " + request);
 						Log.w("info", "response: " + response);
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}

 					HttpEntity entity = response.getEntity();
 					Log.w("info", "entity: " + entity);

 					try {
 						InputStreamReader reader = new InputStreamReader(entity
 								.getContent(), "utf8");
 					} catch (UnsupportedEncodingException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IllegalStateException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}

 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});

 		thread.start();
     }
  
     /****** Function to set data in ArrayList *************/
     public void setListData()
     {
          
         for (int i = 1; i < 10; i++) {
              
             final ListModel sched = new ListModel();
                  
               /******* Firstly take data in model object ******/
                sched.setEntfernung(""+i+""+i+""+i+"m");
                sched.setImage("pic"+i);
                sched.setName("Kuechenmagnet");
                sched.setLevel(""+i);
                 
             /******** Take Model Object in ArrayList **********/
                ListViewValuesArr.add( sched );
         }
          
     }
     

     /*****************  This function used by adapter ****************/
     public void onItemClick(int mPosition)
     {
         ListModel tempValues = ( ListModel ) ListViewValuesArr.get(mPosition);

         popUp();
        // SHOW ALERT                 
        //Intent intent = new Intent(getApplicationContext(), PopUpDescription.class);
       /* intent.putExtra("Image", tempValues.getImage());
        intent.putExtra("Entfernung", tempValues.getEntfernung());
        intent.putExtra("Name", tempValues.getName());
        intent.putExtra("Level", tempValues.getLevel());*/
		//startActivity(intent);

     }
     
     @SuppressWarnings("deprecation")
	public void initPopUp() {
    	 //Deklariert Variablen 
     	 //popupButton = (Button)this.findViewById(R.id.popupbutton);
         
    	 //popupText = new TextView(this);
    	 //popupText.setPadding(0, 0, 0, 20);
    	
    	 insidePopupButton = new Button(this);
    	 toGoogleMaps = new Button(this);
    	 insidePopupButton.setLayoutParams(new LayoutParams(600,150) );
    	 
    	 
         layoutOfPopup = new LinearLayout(this);
         //layoutOfPopup.addView(popupText);
         
        
         //So soll der Popup aussehen 
         layoutOfPopup.setOrientation(1);
         layoutOfPopup.addView(insidePopupButton);
         layoutOfPopup.addView(toGoogleMaps);
         toGoogleMaps.setText("show Spot !");
         
         popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT);
         popupMessage.setContentView(layoutOfPopup);
         
         insidePopupButton.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {
 				// TODO Auto-generated method stub
 				 popupMessage.dismiss();
 			}
 		});    
         
         toGoogleMaps.setOnClickListener(new OnClickListener() {
  			
  			@Override
  			public void onClick(View v) {
  				// TODO Auto-generated method stub
  				Intent intent = new Intent(getApplicationContext(), FreenessMap.class);
  				startActivity(intent);
  				 
  			}
  		});  
     }
     
     public void popUp() {
         insidePopupButton.setText("Der Kuechenmagnet fördert die Beweglichkeit der Hüfte und das Hand-Auge Koordination Spiel");
         popupMessage.showAsDropDown(list,450,-250); 
        // popupMessage.sh
        
     }
     
     /*****************  This function used by adapter ****************/
     public void onImageClick(int mPosition)
     {
         ListModel tempValues = ( ListModel ) ListViewValuesArr.get(mPosition);


        // SHOW ALERT                 
        Intent intent = new Intent(getApplicationContext(), ListItemVideoActivity.class);
        intent.putExtra("Image", tempValues.getImage());
        intent.putExtra("Entfernung", tempValues.getEntfernung());
        intent.putExtra("Name", tempValues.getName());
        intent.putExtra("Level", tempValues.getLevel());
		startActivity(intent);

     }

}
