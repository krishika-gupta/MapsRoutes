package com.clicklabs.mapsroutes.Utils;

import com.clicklabs.mapsroutes.Models.Route.Directions;
import com.clicklabs.mapsroutes.Models.geocoding.GeocodingMain;
import com.clicklabs.mapsroutes.Models.place.AutoComplete;
import com.clicklabs.mapsroutes.Models.reverseGeocoding.RevGeocodingMain;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp- on 29-02-2016.
 */
public class CommonData {
    public static AutoComplete autoComplete;
    public static GeocodingMain geocodingMain1;
    public static RevGeocodingMain revGeocodingMain;
    public static Directions directions;

    public static List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    public static AutoComplete getAutoComplete() {
        return autoComplete;
    }

    public static void setAutoComplete(AutoComplete autoComplete) {
        CommonData.autoComplete = autoComplete;
    }

    public static GeocodingMain getGeocodingMain1() {
        return geocodingMain1;
    }

    public static void setGeocodingMain1(GeocodingMain geocodingMain1) {
        CommonData.geocodingMain1 = geocodingMain1;
    }

    public static RevGeocodingMain getRevGeocodingMain() {
        return revGeocodingMain;
    }

    public static void setRevGeocodingMain(RevGeocodingMain revGeocodingMain) {
        CommonData.revGeocodingMain = revGeocodingMain;
    }

    public static Directions getDirections() {
        return directions;
    }

    public static void setDirections(Directions directions) {
        CommonData.directions = directions;
    }




}
