import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Getter {
    HttpRequest request;
    HttpClient client = HttpClient.newHttpClient();
    HttpResponse<String> response = null;


    public String getID(String name) {
        JsonObject obj;

        request = HttpRequest.newBuilder().uri(URI.create("https://marvelsapi.com/api/search_player/" + name)).GET().build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("DENIED " + e.getMessage());
        }

        //System.out.println("Server status: " + response.statusCode());

        obj = JsonParser.parseString(response.body()).getAsJsonObject();
        return obj.get("uid").getAsString();
    }

    public JsonObject getData(String input, String usecase) {
        String uid;

        if(input.matches("[0-9]+")) {
            uid = input;
        } else {
            uid = getID(input);
        }
        if (usecase.equals("profile")) {
            request = HttpRequest.newBuilder().uri(URI.create("https://marvelsapi.com/api/player/profile/" + uid)).GET().build();
        }
        if (usecase.equals("career")) {
            request = HttpRequest.newBuilder().uri(URI.create("https://marvelsapi.com/api/player/" + uid + "/match-history")).GET().build();
        }

        System.out.println("Player UID: " + uid);
        System.out.println("Searching for player data...");
        System.out.println();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("INTERRUPTED " + e.getMessage());
        }
        return JsonParser.parseString(response.body()).getAsJsonObject();
    }
}
