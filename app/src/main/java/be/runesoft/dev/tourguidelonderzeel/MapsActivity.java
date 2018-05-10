package be.runesoft.dev.tourguidelonderzeel;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent intent = getIntent();
        String address = intent.getStringExtra(MainActivity.EXTRA_VENUE_ADDRESS);
        String name = intent.getStringExtra(MainActivity.EXTRA_VENUE_NAME);
        Geocoder geocoder = new Geocoder(this);
        try {
            ArrayList<Address> addresses = (ArrayList<Address>) geocoder.getFromLocationName(address, 1);
            for (Address add : addresses) {
                if (add != null) {
                    double lng = add.getLongitude();
                    double lat = add.getLatitude();
                    LatLng venue = new LatLng(lat, lng);
                    googleMap.addMarker(new MarkerOptions().position(venue).title(name));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venue, 16));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
