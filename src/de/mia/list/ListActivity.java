package de.mia.list;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;



import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils.SimpleStringSplitter;
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
	 String data;
	 int dataChar = 0;
	 
	 ListView list;
     CustomAdapter adapter;
     public  ListActivity CustomListView = null;
     public  ArrayList<ListModel> ListViewValuesArr = new ArrayList<ListModel>();
      
     @Override
     protected void onCreate(Bundle savedInstanceState) {
    	 
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_list);
         CustomListView = this;
          
         /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
         Resources res =getResources();
         list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
          
         /**************** Create Custom Adapter *********/
         adapter=new CustomAdapter( CustomListView, ListViewValuesArr,res );
         list.setAdapter( adapter );
         
         //initPopUp(); 
         getDataFromDataStore();
     }
     
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.w("ele","onResume setDataList");
		setListData();
		//sortListViewEntfernung();
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
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}

 					HttpEntity entity = response.getEntity();
 					try {
 						InputStreamReader reader = new InputStreamReader(entity.getContent(), "utf8");
 						dataChar = reader.read();
 						while(dataChar > 0) {
 							data += (char)dataChar;
 							dataChar = reader.read();
 						}
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
 					client.close();

 				} catch (Exception e) {
 					e.printStackTrace();
 				}
 			}
 		});
 		thread.start();
 		try {
			thread.join();
			Log.w("ele",data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
  
     /****** Function to set data in ArrayList *************/
     public void setListData()
     {        
             SimpleStringSplitter sssn = new SimpleStringSplitter('\n');
             SimpleStringSplitter sss = new SimpleStringSplitter(':');
             int i = 0;
             //Log.w("ele",data);
             if(data == null){
            	 Log.w("ele","Data ist leer");
             }
             else {
            	 sssn.setString(data);
             
            	 while(sssn.hasNext()) {
            		 final ListModel model = new ListModel();
            		 i++;
				
            		 sss.setString( sssn.next() );
            		
            		 model.setName(sss.next());
            		
            		 model.setLon(sss.next());
            		
            		 model.setLat(sss.next());
            		
            		 model.setEntfernung(sss.next());
            		
            		 model.setLevel(sss.next());
            		
            		 sssn.next();
            		 model.setImage("pic"+i);
            		 ListViewValuesArr.add( model );
            	 }
			}    
     }  

     /********* Function to sort the string-value from "Entfernung" ******/
     public void sortListViewEntfernung() {
    	 
 		Comparator<ListModel> c = new Comparator<ListModel>() {        //comparator sagt ob die Werte in der Liste noch gedreht werden m�ssen oder nicht
 			@Override
 		/******** Function that compares two objects ******/
 			public int compare(ListModel o1, ListModel o2) {			 //vergleicht objekte aus listmodel
 				return o1.compareToEntfernung(o2);						//comparatar ruft jeweileigen listmodel auf und vergleicht 1 Objekt mit einem 2 Objekt und gibt das verh�ltnis mit in dem Wert zur�ck
 			}
 		};
 		Collections.sort(ListViewValuesArr, c);		//statische methode verlangt arrayliste
 		Log.w("ele","Sorting");
 		adapter.notifyDataSetChanged(); 			//Updated die Daten der Listview
 	}

     
 /******** Function to sort the string-value from "Level" ******/ 
 	public void sortListViewLevel() {
 		Comparator<ListModel> c = new Comparator<ListModel>() {		//comparator sagt ob die Werte in der jeweiligen Liste noch gedreht werden m�ssen oder nicht
 			@Override
 			public int compare(ListModel o1, ListModel o2) {		 //vergleicht objekte aus listmodel
 				return o1.compareToLevel(o2);						//comparatar ruft jeweileigen listmodel auf und vergleicht 1 Objekt mit einem 2 Objekt und gibt das verh�ltnis mit in dem Wert zur�ck
 			}
 		};
	 		Collections.sort(ListViewValuesArr, c);	//statische methode verlangt arrayliste
	 		adapter.notifyDataSetChanged();			//Updated die Daten der Listview
 	}

     /*****************  This function used by adapter ****************/
     public void onItemClick(int mPosition)
     {
         //ListModel tempValues = ( ListModel ) ListViewValuesArr.get(mPosition);

         //popUp();
    	 Intent intent = new Intent(getApplicationContext(), FreenessMap.class);
    	 ListModel tempModel = ( ListModel ) ListViewValuesArr.get(mPosition);
    	 intent.putExtra("lat", tempModel.getLat());
    	 intent.putExtra("lon", tempModel.getLon());
    	 intent.putExtra("name", tempModel.getName());
		 startActivity(intent);
         
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
        intent.putExtra("videoPath", mPosition+1);
		startActivity(intent);
		

     }

}
