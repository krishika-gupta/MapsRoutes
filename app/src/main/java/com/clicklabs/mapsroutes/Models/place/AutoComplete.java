
package com.clicklabs.mapsroutes.Models.place;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class AutoComplete {

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = new ArrayList<Prediction>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * 
     * @return
     *     The predictions
     */
    public List<Prediction> getPredictions() {
        return predictions;
    }

    public class Prediction {

        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("matched_substrings")
        @Expose
        private List<MatchedSubstring> matchedSubstrings = new ArrayList<MatchedSubstring>();
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("reference")
        @Expose
        private String reference;
        @SerializedName("terms")
        @Expose
        private List<Term> terms = new ArrayList<Term>();
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<String>();

        /**
         *
         * @return
         *     The description
         */
        public String getDescription() {
            return description;
        }

        public String getId() {
            return id;
        }

        /**
         *
         * @param id
         *     The id
         */
        public void setId(String id) {
            this.id = id;
        }

        public class MatchedSubstring {

            @SerializedName("length")
            @Expose
            private Integer length;
            @SerializedName("offset")
            @Expose
            private Integer offset;


        }
        public class Term {

            @SerializedName("offset")
            @Expose
            private Integer offset;
            @SerializedName("value")
            @Expose
            private String value;


        }


    }

}
