package de.mia.list;

import java.util.concurrent.ExecutionException;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import de.mia.route.Routing;
import de.mia.route.RoutingListener;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FreenessMap extends FragmentActivity implements RoutingListener,
		OnMapClickListener, OnClickListener, OnMarkerClickListener {
	static final LatLng LETTE = new LatLng(52.497, 13.340);
	static final LatLng LETTE1 = new LatLng(52.497, 13.341);
	/*
	static final LatLng LETTE2 = new LatLng(52.497, 13.343);
	static final LatLng LETTE3 = new LatLng(52.497, 13.342);*/
	
	LatLng lette; 
	String name;

	private GoogleMap mMap;
	private Polyline currentPolyLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		FragmentManager fmanager = getSupportFragmentManager();
		Fragment fragment = fmanager.findFragmentById(R.id.map);
		SupportMapFragment supportmapfragment = (SupportMapFragment) fragment;
		mMap = supportmapfragment.getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setOnMarkerClickListener(this);
		
		String str = getIntent().getStringExtra("lat");
		String lat = str.replace(",",".");
		lat = lat.substring(1);
		
		str = getIntent().getStringExtra("lon");
		String lon = str.replace(",",".");
		lon = lon.substring(1);
		
		name = getIntent().getStringExtra("name");
		Log.w("ele",lat+":"+lon);
		lette = new LatLng(Float.valueOf(lat), Float.valueOf(lon));
		
		initializePosition();
		addMarker();

	}
	
	

	private void addMarker() {
		mMap.addMarker(new MarkerOptions().position(lette)
				.title("Freeness-Spot").snippet(name)
				.icon(BitmapDescriptorFactory.fromAsset("icon.png")));
		/*Marker letteMarker = mMap.addMarker(new MarkerOptions()
				.position(LETTE1).title("Freeness-Station")
				.snippet("Uebung: Klimmzuege")
				.icon(BitmapDescriptorFactory.fromAsset("icon.png")));
		Marker letteMarker1 = mMap.addMarker(new MarkerOptions()
		.position(LETTE2).title("Freeness-Station")
		.snippet("Uebung: Klimmzuege")
		.icon(BitmapDescriptorFactory.fromAsset("icon.png")));
		Marker letteMarker2 = mMap.addMarker(new MarkerOptions()
		.position(LETTE3).title("Freeness-Station")
		.snippet("Uebung: Klimmzuege")
		.icon(BitmapDescriptorFactory.fromAsset("icon.png")));
		
		/*
		Marker letteMarker1 = mMap.addMarker(new MarkerOptions()
				.position(new LatLng(52.506609, 13.342698))
				.title("Freeness-Station").snippet("Uebung: Klimmzuege")
				.icon(BitmapDescriptorFactory.fromAsset("icon.png")));
		Marker bremenMarker1 = mMap.addMarker(new MarkerOptions()
				.position(new LatLng(53.050024, 8.731016))
				.title("Freeness-Station").snippet("Uebung: Klimmzuege")
				.icon(BitmapDescriptorFactory.fromAsset("icon.png")));*/

	}

	private void initializePosition() {
		// zum Viktoria-Luise-Platz mit Zoom 15
		// mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LETTE, 14.0f));
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LETTE, 14.0f));

	}

	@Override
	public void onRoutingStart() {
		// The Routing Request starts
	}

	@Override
	public void onRoutingFailure() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRoutingSuccess(PolylineOptions mPolyOptions) {
		if (currentPolyLine != null) {
			currentPolyLine.remove();
		}
		PolylineOptions polyoptions = new PolylineOptions();
		polyoptions.color(Color.RED);
		polyoptions.width(10);
		polyoptions.addAll(mPolyOptions.getPoints());
		currentPolyLine = mMap.addPolyline(polyoptions);
	}

	@Override
	public void onMapClick(LatLng point) {
		LatLng start = LETTE;
		LatLng end = new LatLng(52.496088, 13.1);

		Location location = mMap.getMyLocation();
		if (location != null) {
			LatLng myLocation = new LatLng(location.getLatitude(),
					location.getLongitude());
			end = myLocation;
		}

		Routing routing = new Routing(Routing.TravelMode.WALKING);
		routing.registerListener(this);
		routing.execute(start, end);

	}

	@Override
	public void onClick(View v) {
		Log.w("ele","click");
		LatLng start = LETTE;
		LatLng end = LETTE1;

		Location location = mMap.getMyLocation();
		System.out.println(location);
		if (location != null) {
			LatLng myLocation = new LatLng(location.getLatitude(),
					location.getLongitude());
			start = myLocation;
		}

		Routing routing = new Routing(Routing.TravelMode.WALKING);
		routing.registerListener(this);
		routing.execute(start, end);

	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		System.out.println("markerClick");
		LatLng start = LETTE;
		LatLng end = LETTE1;

		Location location = mMap.getMyLocation();
		if (location != null) {
			System.out.println(location);

			LatLng myLocation = new LatLng(location.getLatitude(),
					location.getLongitude());
			start = myLocation;
			end = marker.getPosition();

			Routing routing = new Routing(Routing.TravelMode.WALKING);
			routing.registerListener(this);
			routing.execute(start, end);
			int distance = 0;
			try {
				distance = routing.get().getLength();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			marker.setTitle(marker.getTitle() + " Entfernung: " + distance);

		} else {
			System.out.println("Position konnte nicht ermittelt werden.");
		}
		return false;
	}

}

