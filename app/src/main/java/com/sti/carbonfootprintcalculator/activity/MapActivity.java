package com.sti.carbonfootprintcalculator.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sti.carbonfootprintcalculator.R;

public class MapActivity extends Activity {

    static final LatLng bacoor = new LatLng(14.412932, 120.972697);
    static final LatLng sineguelas = new LatLng(14.459319, 120.932316);

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        CameraPosition position = CameraPosition.builder()
                .target(bacoor)
                .zoom(13f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);


        if (map != null) {
//            Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
//                    .title("Hamburg"));
            map.addMarker(new MarkerOptions()
                    .position(sineguelas)
                    .title("Sineguelasan Barangay Hall")
                    .snippet("Sineguelasan Barangay Hall")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.rsz_location_map_pin_green5)));
        }

    }

}
