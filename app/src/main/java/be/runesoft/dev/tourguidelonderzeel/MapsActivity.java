package be.runesoft.dev.tourguidelonderzeel;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private GoogleMap mMap;
    private LatLng venue;

    private ImageView toggleDesc;
    private TextView venueDesc;
    private GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener =
            new GoogleMap.OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    mMap.setMinZoomPreference(1);
                    return false;
                }
            };
    private GoogleMap.OnMyLocationClickListener onMyLocationClickListener =
            new GoogleMap.OnMyLocationClickListener() {
                @Override
                public void onMyLocationClick(@NonNull Location location) {

                    mMap.setMinZoomPreference(1);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venue, 16));
                }
            };

    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            mMap.setMyLocationEnabled(true);
        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(47.6739881, -122.121512);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(redmond));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        toggleDesc = findViewById(R.id.toggle_venue_description);
        venueDesc = findViewById(R.id.venue_description);
        venueDesc.setText(Html.fromHtml(intent.getStringExtra(MainActivity.EXTRA_VENUE_LONG_DESC)));
        venueDesc.setMovementMethod(new ScrollingMovementMethod());

        toggleDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleDesc.getContentDescription() == getResources().getString(R.string.chevron_up)) {
                    toggleDesc.setContentDescription(getResources().getString(R.string.chevron_down));
                    toggleDesc.setImageResource(R.drawable.chevron_down);
                    animateHeight(venueDesc, 750);

                } else {
                    toggleDesc.setContentDescription(getResources().getString(R.string.chevron_up));
                    toggleDesc.setImageResource(R.drawable.chevron_up);
                    animateHeight(venueDesc, -1000);
                    venueDesc.setHeight(0);
                }
            }
        });
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

        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(onMyLocationButtonClickListener);

        enableMyLocationIfPermitted();

        Intent intent = getIntent();
        String address = intent.getStringExtra(MainActivity.EXTRA_VENUE_ADDRESS);
        String name = intent.getStringExtra(MainActivity.EXTRA_VENUE_NAME);
        setTitle(name);
        Geocoder geocoder = new Geocoder(this);
        try {
            ArrayList<Address> addresses = (ArrayList<Address>) geocoder.getFromLocationName(address, 1);
            for (Address add : addresses) {
                if (add != null) {
                    double lng = add.getLongitude();
                    double lat = add.getLatitude();
                    venue = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(venue).title(name));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(venue, 16));
                    mMap.setOnMyLocationClickListener(onMyLocationClickListener);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void animateHeight(View view, int diff) {
        final View viewToIncreaseHeight = view;
        ValueAnimator anim = ValueAnimator.ofInt(viewToIncreaseHeight.getMeasuredHeight(), diff);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = viewToIncreaseHeight.getLayoutParams();
                layoutParams.height = val;
                viewToIncreaseHeight.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(500);
        anim.start();
    }
}
