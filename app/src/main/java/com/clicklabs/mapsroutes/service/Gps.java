package com.clicklabs.mapsroutes.service;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by hp- on 25-02-2016.
 */


public class Gps extends IntentService implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    LocationRequest locationRequest;
    LocationManager locationManager;
    GoogleApiClient mgoogleApiClient;
    Double newLat, newLng;

    public Gps(){
        super("gps");


    }

    @Override
    public void onCreate() {
        createLocationRequest();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("Tag", "Service started");
        return super.onStartCommand(intent, flags, startId);
    }

    public void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(100000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        mgoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        mgoogleApiClient.connect();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onLocationChanged(Location location) {
        newLat = location.getLatitude();
        newLng = location.getLongitude();
        Log.i("TAG Tracking lat", String.valueOf(newLat));
        Log.i("TAG Tracking lng", String.valueOf(newLng));
        //Toast.makeText(getApplicationContext(), "Location Update : " + newLat + " " + newLng, Toast.LENGTH_LONG).show();

        Intent locationIntent = new Intent(LOCATION_SERVICE);
//        locationIntent.setAction(LOCATION_SERVICE);
//        locationIntent.addCategory(Intent.CATEGORY_DEFAULT);
        locationIntent.putExtra("lat", newLat);
        locationIntent.putExtra("lng", newLng);
        sendBroadcast(locationIntent);

    }


    @Override
    public void onConnected(Bundle bundle) {
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
        Log.v("TAGService", String.valueOf((LocationServices.FusedLocationApi.requestLocationUpdates(mgoogleApiClient, locationRequest, this))));
        LocationServices.FusedLocationApi.requestLocationUpdates(mgoogleApiClient, locationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
