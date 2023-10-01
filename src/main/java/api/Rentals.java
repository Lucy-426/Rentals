package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Rentals {
    private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/";
    // load API_TOKEN from env variable.
    private static final String API_TOKEN = "ea2ec50d07946af90b2090156a03e236";

    @Override
    public Grade getGrade(String utorid, String course) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/detail?address1=4529%20Winona%20Court&address2=Denver%2C%20CO")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("apikey", "870e26a0ffcc29cbb6b8bc86012a8c28")

                .build();

        Response response = client.newCall(request).execute();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                JSONObject grade = responseBody.getJSONObject("grade");
                return Grade.builder()
                        .utorid(grade.getString("utorid"))
                        .course(grade.getString("course"))
                        .grade(grade.getInt("grade"))
                        .build();
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
