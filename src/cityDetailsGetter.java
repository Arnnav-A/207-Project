import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

/**
 * This (temporary) class gets the JSON details of a city using open trip map API.
 */
public class cityDetailsGetter {
    private static final String API_TOKEN = System.getenv("API_TOKEN");
    private static final String city = "Toronto";
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://api.opentripmap.com/0.1/en/places/geoname?name=%s&apikey=%s", city, API_TOKEN))
                .build();
        System.out.println(request);
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }
}
