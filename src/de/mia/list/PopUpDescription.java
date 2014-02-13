package de.mia.list;
 

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
 
/**
 * Diese Klasse soll ein PopUp erzeugen, bei einem Klick auf den PopupButton.
 * Dieser Button wird in der xml (activity_main) deklariert. Beim dr?cken auf den Button
 * wir der Popup erzeugt und klappt auf. In dem Popup stehen Informationen drinnen und  
 * auch ein Button zum schlie?en.
 */

public class PopUpDescription extends Activity implements OnClickListener {
    LinearLayout layoutOfPopup;
    PopupWindow popupMessage;
    Button popupButton, insidePopupButton;
    TextView popupText;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_description);
        init();
        
    }
    
    
/**
  * Die init Mehtode baut erstens die vern?pfung zum Button(PopupButton)
  * erstellt ein Textfeld , baut ein insideButton und ein  layout f?r den Popup   
  **/
    @SuppressWarnings("deprecation")
    public void init() {
        //Deklariert Variablen 
    	popupButton = (Button)this.findViewById(R.id.popupbutton);
        popupText = new TextView(this);
        insidePopupButton = new Button(this);
        layoutOfPopup = new LinearLayout(this);
        //Setzt auf den Insidebutton ein text
        insidePopupButton.setText("OK");
        //Setzt ein Text in dem PopupFenster
        popupText.setText("Das ist ein PopUpFenster, um es zu zumachen druecke ok");
        popupText.setPadding(0, 0, 0, 20);
        //So soll der Popup aussehen 
        layoutOfPopup.setOrientation(1);
        layoutOfPopup.addView(popupText);
        layoutOfPopup.addView(insidePopupButton);
        
        insidePopupButton.setOnClickListener(this);
        //Layout der Message 
        popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);
        //popupMessage.setContentView(layoutOfPopup);
        popupButton.setOnClickListener(this);
        
        //popupMessage.showAsDropDown(popupButton);
        
    }
 
 
    @Override
    public void onClick(View v) {
           // popupMessage.dismiss();
            popupMessage.showAsDropDown(popupButton);
    }
}
