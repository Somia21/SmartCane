package com.somia.fyp.NewLocationSharing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.somia.fyp.R;
import com.somia.fyp.UI.Activitys.SignInBlindUser;
import com.somia.fyp.UI.Activitys.SignInFamily;
import com.somia.fyp.utial.MySharedPref;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Somia on 6/1/2018.
 */

public class NewMapsFamily extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    TextView Longitude;
    TextView Latitude;
    TextView CurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_activty);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        String userUiqID = MySharedPref.getSavedObjectFromPreference(getApplicationContext(), MySharedPref.SHARD_PREF_AUDIO_BOOK,
                SignInFamily.FAMILY_PHONE_NUMBER, String.class);
        readDataBase(userUiqID);
    }

    private void readDataBase(String UID) {
        Longitude = (TextView) findViewById(R.id.longitude);
        Latitude = (TextView) findViewById(R.id.latitude);

        Log.d(TAG, "trying to read database");
        if (UID == null) {
            UID = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        }

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("MyLocation").child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                MyLocation myLocation = dataSnapshot.getValue(MyLocation.class);
                if (myLocation!=null)
                {
                    LatLng newLocation = new LatLng(myLocation.getLat(), myLocation.getLng());
                    mMap.addMarker(new MarkerOptions().position(newLocation));

                    mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomBy(11));

                    Longitude.setText(Double.toString(myLocation.getLng()));
                    Latitude.setText(Double.toString(myLocation.getLat()));
                    getAddress(myLocation.getLat(),myLocation.getLng());
                }else {
                    Toast.makeText(NewMapsFamily.this,"please enter location",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "database error:" + databaseError.getMessage());
            }
        });
    }

    public String getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            CurrentLocation=(TextView)findViewById(R.id.CurrentAddress);


        } catch (IOException e) {
            e.printStackTrace();

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return null;
    }
}
