package com.example.madprojecttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddAlert extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    EditText txttitle, txtdesc;
    Button btnsend;
    Alert alert;
    DatabaseReference dbRef;
    double lat,lng;
    String datetime;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report__news__form);


        SessionManagement sessionManagement=new SessionManagement(AddAlert.this);
        user=sessionManagement.getSession();

        txttitle = findViewById(R.id.title);
        txtdesc = findViewById(R.id.alertdescription);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-YYYY hh:mm a");
        datetime=simpleDateFormat.format(calendar.getTime());


        alert = new Alert();

        btnsend = findViewById(R.id.sendalert);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(AddAlert.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION);

                } else {
                    getCurrentlocation();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            getCurrentlocation();
        } else {
            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        }

    }

    private void getCurrentlocation() {

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

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
        LocationServices.getFusedLocationProviderClient(AddAlert.this).
                requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(AddAlert.this).removeLocationUpdates(this);

                        if(locationResult!=null&&locationResult.getLocations().size()>0){

                            int latestLocationIndex=locationResult.getLocations().size()-1;
                            lat=locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            lng=locationResult.getLocations().get(latestLocationIndex).getLongitude();
                           // Toast.makeText(getApplicationContext(), String.valueOf(lat), Toast.LENGTH_SHORT).show();

                            if(alert.TitleLengthvalid(txttitle.getText().toString().trim())) {
                                dbRef= FirebaseDatabase.getInstance().getReference().child("Alert");
                                alert.setTitle(txttitle.getText().toString().trim());
                                alert.setDescription(txtdesc.getText().toString().trim());
                                alert.setDate(datetime);
                                alert.setLattitude(lat);
                                alert.setLongtitude(lng);
                                alert.setNic(user);

                                dbRef.child(alert.getTitle()).setValue(alert);
                                Toast.makeText(getApplicationContext(), "Successfully sent", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Title maximum length is 25", Toast.LENGTH_SHORT).show();
                            }






                        }
                    }
                }, Looper.getMainLooper());


    }
}