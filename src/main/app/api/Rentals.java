package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Rentals {
    // Root URL - can later adapt so that for various functions, we attach ending (i.e. .../property/detail)
    private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/";
    private static final String API_TOKEN = "ea2ec50d07946af90b2090156a03e236";


    // Request 1: get the list of listings from longitude and latitude search
    // Request 2: find the details for each listing from request 1 based on attom id (see which is the best way to do this
    // If the first endpoint (assessement) can give both listings and details then just one request is good
    // If not, look into property basic profile or profile detail (find the one that is most detailed)
    // Then create csv file and save listings (figure out the columns)

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/detail?address1=4529%20Winona%20Court&address2=Denver%2C%20CO")
                .get()
                //.addHeader("accept", "application/json")
                .addHeader("apikey", API_TOKEN)

                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);

            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
