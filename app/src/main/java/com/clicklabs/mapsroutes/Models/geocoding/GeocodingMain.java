
package com.clicklabs.mapsroutes.Models.geocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class GeocodingMain {

    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    public class Result {

        @SerializedName("address_components")
        @Expose
        private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
        @SerializedName("formatted_address")
        @Expose
        private String formattedAddress;
        @SerializedName("geometry")
        @Expose
        private Geometry geometry;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<String>();


        public Geometry getGeometry() {
            return geometry;
        }

        public class Geometry {

            @SerializedName("location")
            @Expose
            private Location location;
            @SerializedName("location_type")
            @Expose
            private String locationType;
            @SerializedName("viewport")
            @Expose
            private Viewport viewport;

            /**
             * @return The location
             */
            public Location getLocation() {
                return location;
            }

            public class Viewport {

                @SerializedName("northeast")
                @Expose
                private Northeast northeast;
                @SerializedName("southwest")
                @Expose
                private Southwest southwest;

                public class Northeast {

                    @SerializedName("lat")
                    @Expose
                    private Double lat;
                    @SerializedName("lng")
                    @Expose
                    private Double lng;


                }

                public class Southwest {

                    @SerializedName("lat")
                    @Expose
                    private Double lat;
                    @SerializedName("lng")
                    @Expose
                    private Double lng;


                }


            }

            public class Location {

                @SerializedName("lat")
                @Expose
                private Double lat;
                @SerializedName("lng")
                @Expose
                private Double lng;

                /**
                 * @return The lat
                 */
                public Double getLat() {
                    return lat;
                }

                public Double getLng() {
                    return lng;
                }


            }
        }

        public class AddressComponent {

            @SerializedName("long_name")
            @Expose
            private String longName;
            @SerializedName("short_name")
            @Expose
            private String shortName;
            @SerializedName("types")
            @Expose
            private List<String> types = new ArrayList<String>();


        }




    }

}

