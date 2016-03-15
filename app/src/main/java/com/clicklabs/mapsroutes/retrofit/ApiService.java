package com.clicklabs.mapsroutes.retrofit;




import com.clicklabs.mapsroutes.Models.place.AutoComplete;
import com.clicklabs.mapsroutes.Models.Route.Directions;
import com.clicklabs.mapsroutes.Models.geocoding.GeocodingMain;
import com.clicklabs.mapsroutes.Models.reverseGeocoding.RevGeocodingMain;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by Krishika on 09-02-2016.
 */
public interface ApiService {
    @POST("/AutoComplete/autocomplete/json")
    void placeAuto(@Query("input") String input, @Query("key") String key, Callback<AutoComplete> callback);

    @POST("/geocode/json")
    void getLatLng(@Query("address") String address, @Query("key") String key, Callback<GeocodingMain> callback);

    @POST("/directions/json")
    void getroute(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key, Callback<Directions> callback);

    @POST("/geocode/json")
    void reverseGeoCoding(@Query("latlng") String latLng, @Query("key") String key, Callback<RevGeocodingMain> callback);














}
