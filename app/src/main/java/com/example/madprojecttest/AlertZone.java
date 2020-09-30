package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlertZone extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    DatabaseReference redRef;
    private ArrayList<Alert> alerts;
    Alert alert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_zone);
        alerts=new ArrayList<>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
       mapFragment.getMapAsync(this);
        Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {

                    redRef= FirebaseDatabase.getInstance().getReference().child("Alert");
                    redRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot spot:snapshot.getChildren()){
                                if(spot.hasChildren()){
                                    alert=new Alert();
                                    alert.setTitle(spot.child("title").getValue().toString());
                                    alert.setLattitude(Double.parseDouble(spot.child("lattitude").getValue().toString()));
                                    alert.setLongtitude(Double.parseDouble(spot.child("longtitude").getValue().toString()));
                                   // Toast.makeText(getApplicationContext(),String.valueOf(alert.getLattitude()),Toast.LENGTH_SHORT).show();
                                    alerts.add(alert);
                                }else {
                                    Toast.makeText(getApplicationContext(), "Nothing to display", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    double latitude = location.getLatitude();
                    double longtitude = location.getLongitude();

                    LatLng latLng=new LatLng(latitude, longtitude);
                    Geocoder geocoder=new Geocoder(getApplicationContext());
                    try {
                    List<Address> address = geocoder.getFromLocation(latitude,longtitude,1);
                    String str=address.get(0).getAddressLine(0)+",";
                        str+=address.get(0).getSubLocality()+",";
                    mMap.addMarker(new MarkerOptions().position(latLng).title("your location").snippet(str).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.5f));

                    //int i=0;

                    for(Alert spot:alerts){
                       // LatLng latLng=new LatLng(spot.getLattitude(),spot.getLongtitude());
                        address = geocoder.getFromLocation(spot.getLattitude(),spot.getLongtitude(), 1);
                        String str1=address.get(0).getAddressLine(0)+",";
                        str1+=address.get(0).getSubLocality()+",";
                        mMap.addMarker(new MarkerOptions().position(new LatLng(spot.getLattitude(),spot.getLongtitude())).title(spot.getTitle()).snippet(str1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(spot.getLattitude(),spot.getLongtitude()),10.5f));
                    }



                      /*  LatLng loc1 = new LatLng(6.875756, 79.861006);
                        LatLng loc2=new LatLng(6.874875, 79.861065);
                        LatLng loc3=new LatLng(6.874697, 79.861896);
                        LatLng my=new LatLng(6.876785, 79.860480);
                        mMap.addMarker(new MarkerOptions().position(loc1).title("Man found with gun").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.addMarker(new MarkerOptions().position(loc2).title("A Handbag is stolen").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.addMarker(new MarkerOptions().position(loc3).title("Fire accident").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        mMap.addMarker(new MarkerOptions().position(my).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc1));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc1,10.5f));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc2));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc2,10.5f));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc3));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc3,10.5f));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(my));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(my,10.5f));
                    */

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT);
        }
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

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}