package com.clicklabs.mapsroutes.retrofit;






import com.clicklabs.mapsroutes.Config.config;

import retrofit.RestAdapter;

/**
 * Created by Krishika on 09-02-2016.
 */
public class RestClient {
    private static ApiService apiService = null;

    public static ApiService getApiService() {
        if (apiService == null) {

            // For object response which is default
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(config.getBaseURL()).build();


//            //For type string response
//            RestAdapter restAdapter = new RestAdapter.Builder()
//                    .setEndpoint(Config.getBaseURL())
//                    .setConverter(new StringConverter())    //converter for response type
//                    .build();


            apiService = restAdapter.create(ApiService.class);
        }
        return apiService;
    }


}
