
package com.clicklabs.mapsroutes.Models.reverseGeocoding;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RevGeocodingMain {

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


        /**
         * @return The formattedAddress
         */
        public String getFormattedAddress() {
            return formattedAddress;
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

        public class Geometry {

            @SerializedName("bounds")
            @Expose
            private Bounds bounds;
            @SerializedName("location")
            @Expose
            private Location location;
            @SerializedName("location_type")
            @Expose
            private String locationType;
            @SerializedName("viewport")
            @Expose
            private Viewport viewport;


            public class Bounds {

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


            }

            public class Viewport {

                @SerializedName("northeast")
                @Expose
                private Northeast_ northeast;
                @SerializedName("southwest")
                @Expose
                private Southwest_ southwest;

                public class Southwest_ {

                    @SerializedName("lat")
                    @Expose
                    private Double lat;
                    @SerializedName("lng")
                    @Expose
                    private Double lng;


                }


                public class Northeast_ {

                    @SerializedName("lat")
                    @Expose
                    private Double lat;
                    @SerializedName("lng")
                    @Expose
                    private Double lng;


                }


            }


        }


    }


}
