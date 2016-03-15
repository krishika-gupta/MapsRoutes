
package com.clicklabs.mapsroutes.Models.Route;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Directions {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = new ArrayList<GeocodedWaypoint>();
    @SerializedName("routes")
    @Expose
    private List<Route> routes = new ArrayList<Route>();
    @SerializedName("status")
    @Expose
    private String status;


    /**
     * 
     * @return
     *     The routes
     */
    public List<Route> getRoutes() {
        return routes;
    }

    public class GeocodedWaypoint {

        @SerializedName("geocoder_status")
        @Expose
        private String geocoderStatus;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<String>();


    }
    public class Route {

        @SerializedName("bounds")
        @Expose
        private Bounds bounds;
        @SerializedName("copyrights")
        @Expose
        private String copyrights;
        @SerializedName("legs")
        @Expose
        private List<Leg> legs = new ArrayList<Leg>();
        @SerializedName("overview_polyline")
        @Expose
        private OverviewPolyline overviewPolyline;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("warnings")
        @Expose
        private List<Object> warnings = new ArrayList<Object>();
        @SerializedName("waypoint_order")
        @Expose
        private List<Object> waypointOrder = new ArrayList<Object>();


        /**
         *
         * @return
         *     The overviewPolyline
         */
        public OverviewPolyline getOverviewPolyline() {
            return overviewPolyline;
        }

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



        public class Leg {

            @SerializedName("distance")
            @Expose
            private Distance distance;
            @SerializedName("duration")
            @Expose
            private Duration duration;
            @SerializedName("end_address")
            @Expose
            private String endAddress;
            @SerializedName("end_location")
            @Expose
            private EndLocation endLocation;
            @SerializedName("start_address")
            @Expose
            private String startAddress;
            @SerializedName("start_location")
            @Expose
            private StartLocation startLocation;
            @SerializedName("steps")
            @Expose
            private List<Step> steps = new ArrayList<Step>();
            @SerializedName("via_waypoint")
            @Expose
            private List<Object> viaWaypoint = new ArrayList<Object>();


            public class StartLocation {

                @SerializedName("lat")
                @Expose
                private Double lat;
                @SerializedName("lng")
                @Expose
                private Double lng;


            }

            public class EndLocation {

                @SerializedName("lat")
                @Expose
                private Double lat;
                @SerializedName("lng")
                @Expose
                private Double lng;


            }

            public class Distance {

                @SerializedName("text")
                @Expose
                private String text;
                @SerializedName("value")
                @Expose
                private Integer value;



            }
            public class Duration {

                @SerializedName("text")
                @Expose
                private String text;
                @SerializedName("value")
                @Expose
                private Integer value;



            }
            public class Step {

                @SerializedName("distance")
                @Expose
                private Distance_ distance;
                @SerializedName("duration")
                @Expose
                private Duration_ duration;
                @SerializedName("end_location")
                @Expose
                private EndLocation_ endLocation;
                @SerializedName("html_instructions")
                @Expose
                private String htmlInstructions;
                @SerializedName("polyline")
                @Expose
                private Polyline polyline;
                @SerializedName("start_location")
                @Expose
                private StartLocation_ startLocation;
                @SerializedName("travel_mode")
                @Expose
                private String travelMode;
                @SerializedName("maneuver")
                @Expose
                private String maneuver;

                public class Distance_ {

                    @SerializedName("text")
                    @Expose
                    private String text;
                    @SerializedName("value")
                    @Expose
                    private Integer value;


                }

                public class Duration_ {

                    @SerializedName("text")
                    @Expose
                    private String text;
                    @SerializedName("value")
                    @Expose
                    private Integer value;


                }

                public class EndLocation_ {

                    @SerializedName("lat")
                    @Expose
                    private Double lat;
                    @SerializedName("lng")
                    @Expose
                    private Double lng;


                }
                public class Polyline {

                    @SerializedName("points")
                    @Expose
                    private String points;


                }
                public class StartLocation_ {

                    @SerializedName("lat")
                    @Expose
                    private Double lat;
                    @SerializedName("lng")
                    @Expose
                    private Double lng;
                }
            }
        }

        public class OverviewPolyline {

            @SerializedName("points")
            @Expose
            private String points;

            /**
             *
             * @return
             *     The points
             */
            public String getPoints() {
                return points;
            }
        }
    }
}
