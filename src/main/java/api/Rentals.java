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
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://grade-logging-api.chenpan.ca/grade?course=%s&utorid=%s", course, utorid))
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
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
