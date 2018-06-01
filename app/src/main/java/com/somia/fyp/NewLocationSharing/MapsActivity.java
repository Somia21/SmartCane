package com.somia.fyp.NewLocationSharing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.somia.fyp.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
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

        //readDataBase(null);
    }
}
//    private void readDataBase(String UID){
//        Log.d(TAG,"trying to read database");
//        if (UID==null){
//            UID= Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                    Settings.Secure.ANDROID_ID);
//        }
//        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("MyLocation").child(UID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                MyLocation myLocation = dataSnapshot.getValue(MyLocation.class);
//                LatLng newLocation = new LatLng(myLocation.getLat(), myLocation.getLng());
//                mMap.addMarker(new MarkerOptions().position(newLocation));
//
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));
//                mMap.animateCamera(CameraUpdateFactory.zoomBy(11));
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(TAG,"database error:"+databaseError.getMessage());
//            }
//        });
//    }
//}
