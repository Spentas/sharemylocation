package com.spentas.javad.sharemylocation.fragments;

import android.app.Application;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spentas.javad.sharemylocation.R;
import com.spentas.javad.sharemylocation.util.Const;
import com.spentas.javad.sharemylocation.util.LocationManager;

import java.util.HashMap;

/**
 * Created by javad on 10/16/2015.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    public static final String TAG = "map";
    private MapView mMapView;
    private GoogleMap googleMap;
    private HashMap<String,String> location;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map,container,false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        // latitude and longitude

        double latitude = 17.385044;
        double longitude = 78.486671;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng( latitude, longitude)).title("Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
//        googleMap.addMarker(marker);
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
//        googleMap.animateCamera(CameraUpdateFactory
//                .newCameraPosition(cameraPosition));
        googleMap.setMyLocationEnabled(true);
        getCurrentLocation();
        // Perform any camera updates here
        return v;
    }
   public Location getCurrentLocation() {
        Location myLocation  = googleMap.getMyLocation();

        location = new HashMap<>();
        if(myLocation!=null)
        {
            double dLatitude = myLocation.getLatitude();
            double dLongitude = myLocation.getLongitude();
            location.put(Const.LATITUDE,String.valueOf(dLatitude));
            location.put(Const.LONGITUDE,String.valueOf(dLongitude));
                    Log.i("APPLICATION", " : " + dLatitude);
            Log.i("APPLICATION"," : "+dLongitude);
            googleMap.addMarker(new MarkerOptions().position(
                    new LatLng(dLatitude, dLongitude)).title("My Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 8));

        }
        else
        {
            Toast.makeText(getContext(), "Unable to fetch the current location", Toast.LENGTH_SHORT).show();

        }
return myLocation;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();


    }


    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public HashMap<String, String> getLocation() {
        return location;
    }

    public void setLocation(HashMap<String, String> location) {
        this.location = location;
    }
}
