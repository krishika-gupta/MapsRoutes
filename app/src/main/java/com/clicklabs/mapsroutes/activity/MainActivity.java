package com.clicklabs.mapsroutes.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.clicklabs.mapsroutes.Models.geocoding.GeocodingMain;
import com.clicklabs.mapsroutes.Models.place.AutoComplete;
import com.clicklabs.mapsroutes.Models.reverseGeocoding.RevGeocodingMain;
import com.clicklabs.mapsroutes.Models.Route.Directions;
import com.clicklabs.mapsroutes.R;
import com.clicklabs.mapsroutes.Utils.CommonData;
import com.clicklabs.mapsroutes.retrofit.RestClient;
import com.clicklabs.mapsroutes.service.Gps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity implements OnMapReadyCallback{
    GoogleMap myGoogleMap;
    AutoCompleteTextView searchPlaceAuc;
    Button OkButton;
    Double latForGPS, lngForGPS;
    Double lat,lng;
    Marker myLocation,destinationMarker;
    String gpsLoc;
    String destination;
    Intent intent;
    MyReciever myReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        intent = new Intent(MainActivity.this, Gps.class);
        startService(intent);
        myReceiver = new MyReciever();
        registerReceiver(myReceiver, new IntentFilter(LOCATION_SERVICE));
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    protected void onResume() {
        registerReceiver(myReceiver, new IntentFilter(LOCATION_SERVICE));
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }


    private void init() {
        searchPlaceAuc = (AutoCompleteTextView) findViewById(R.id.auc_search_place);
        searchPlaceAuc.addTextChangedListener(MyAutoCompleteTextWatcher);
        OkButton = (Button) findViewById(R.id.btn_ok);
        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination = searchPlaceAuc.getText().toString();
                callBackForGeocoding();
                callBackForRoute();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myGoogleMap = googleMap;

    }

    TextWatcher MyAutoCompleteTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            callBackForAutoComplete(s.toString());
        }
    };


    //Server call to get entered AutoComplete Latitude and Longitude




    private void callBackForReverseGeocoding(final Marker myLocation) {
        RestClient.getApiService().reverseGeoCoding(myLocation.getPosition().latitude + "," + myLocation.getPosition().longitude, Google_places_Server_Key, new Callback<RevGeocodingMain>() {
            @Override
            public void success(RevGeocodingMain revGeocodingMain, Response response) {
                Log.i("TAG REVERSE GEOCODING", "SUCCESS");
                Log.i("TAG  LATLNG", myLocation.getPosition().latitude + "," + myLocation.getPosition().longitude);
                CommonData.setRevGeocodingMain(revGeocodingMain);
                Log.i("TAG MYLOCATION NAME", revGeocodingMain.getResults().get(1).getFormattedAddress());
                gpsLoc = revGeocodingMain.getResults().get(1).getFormattedAddress();
                Log.v("GETTAG", latForGPS.toString());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG Reverse","Failure");
            }


        });


    }
    private void callBackForAutoComplete(String input) {
        RestClient.getApiService().placeAuto(input, Google_places_Server_Key, new Callback<AutoComplete>() {
            @Override
            public void success(AutoComplete autoComplete, Response response) {
                Log.i("TAG AUTOCOMPLETE", "SUCCESS");
                CommonData.setAutoComplete(autoComplete);
                List<String> places = new ArrayList<String>();
                for (int i = 0; i < CommonData.getAutoComplete().getPredictions().size(); i++) {
                    places.add(CommonData.getAutoComplete().getPredictions().get(i).getDescription());
                }

                ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.text_layout, places);
                searchPlaceAuc.setThreshold(2);
                searchPlaceAuc.setAdapter(myAdapter);

                searchPlaceAuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String text = String.valueOf(parent.getItemAtPosition(position));
                        Log.i("TAG AUTOCOMPLETE TEXT", String.valueOf(parent.getItemAtPosition(position)));
                        searchPlaceAuc.setText(text);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG AUTOCOMPLETE","FAILURE");

            }
        });


    }

    private void callBackForGeocoding() {
        RestClient.getApiService().getLatLng(String.valueOf(searchPlaceAuc.getText()), Google_places_Server_Key, new Callback<GeocodingMain>() {
            @Override
            public void success(GeocodingMain geocodingMain, Response response) {
                Log.i("TAG GeoCode", "SUCCESS");
                CommonData.setGeocodingMain1(geocodingMain);
                lat = CommonData.getGeocodingMain1().getResults().get(0).getGeometry().getLocation().getLat();
                lng = CommonData.getGeocodingMain1().getResults().get(0).getGeometry().getLocation().getLng();
                Log.i("TAG GeoCode Lat ", lat.toString());
                Log.i("TAG GeoCode Lng ", lng.toString());
                if(myGoogleMap!=null)
                destinationMarker = myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).title(String.valueOf(lat)));

            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG GEOCODING","FAILURE");

            }
        });

    }

    private void callBackForRoute() {
        RestClient.getApiService().getroute(gpsLoc, destination,Google_places_Server_Key, new Callback<Directions>() {
            @Override
            public void success(Directions directions, Response response) {
                Log.i("TAG ROUTE", "SUCCESS");
                PolylineOptions polylineOptions=new PolylineOptions();
                List<LatLng> myLatLngList = CommonData.decodePoly(directions.getRoutes().get(0).getOverviewPolyline().getPoints());

                polylineOptions.width(5).color(Color.RED);
                polylineOptions.addAll(myLatLngList);
                myGoogleMap.addPolyline(polylineOptions);

            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("TAG ROUTE","FAILURE");

            }
        });

    }

    public class MyReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("TAG", "inRecieve");
            Bundle extras = intent.getExtras() ;
            latForGPS = extras.getDouble("lat");
            lngForGPS = extras.getDouble("lng");
            Log.v("TAGGPS", latForGPS + "," + lngForGPS + "");
            if(myGoogleMap!=null) {
                myLocation = myGoogleMap.addMarker(new MarkerOptions().position(new LatLng(latForGPS, lngForGPS)).title(String.valueOf(latForGPS + "," + lngForGPS)));
                myGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation.getPosition(), 14));
                myLocation.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                callBackForReverseGeocoding(myLocation);
            }
        }
    }

}


