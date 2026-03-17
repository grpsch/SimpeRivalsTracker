import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    //throws IOException, InterruptedException
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();

        String input;
        String uid;
        JsonObject obj;
        HttpRequest request;
        HttpResponse<String> response = null;

        System.out.print("Enter username or uid to scan: ");
        input = scanner.nextLine();

        if(input.matches("[0-9]+")) {

            uid = input;

        } else {
            System.out.println("Searching for: " + input + "...");

            request = HttpRequest.newBuilder().uri(URI.create("https://marvelsapi.com/api/search_player/" + input)).GET().build();
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                System.out.println("ERROR " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("DENIED " + e.getMessage());
            }

            System.out.println("Server status: " + response.statusCode());

            obj = JsonParser.parseString(response.body()).getAsJsonObject();
            uid = obj.get("uid").getAsString();
        }

        System.out.println("Player UID: " + uid);
        System.out.println("Searching for player data...");
        System.out.println();
        request = HttpRequest.newBuilder().uri(URI.create("https://marvelsapi.com/api/player/profile/" + uid)).GET().build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("DENIED " + e.getMessage());
        }
        obj = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject profile = obj.get("profile").getAsJsonObject();
        JsonObject rank = obj.get("rank").getAsJsonObject();

        JsonObject heroes= obj.get("top_heroes").getAsJsonObject();
        JsonArray comp = heroes.get("competitive").getAsJsonArray();
        JsonObject hero1 = comp.get(0).getAsJsonObject();
        JsonObject hero2 = comp.get(1).getAsJsonObject();
        JsonObject hero3 = comp.get(2).getAsJsonObject();


        //System.out.println(response.body());
        System.out.println("\u001B[4m" + "Profile:" + "\u001B[0m");
        System.out.println("Username: " + profile.get("player_name").getAsString());
        System.out.println("Level: " + profile.get("player_level").getAsString());
        System.out.println("Last match: " + profile.get("last_match").getAsString());
        System.out.println();
        System.out.println("\u001B[4m" + "Rank:" + "\u001B[0m");
        System.out.println("Rank: " + rank.get("current_rank").getAsString());
        System.out.println("Rankscore: " + rank.get("current_rank_score").getAsString());
        System.out.println("Winrate: " + rank.get("win_loss").getAsString());
        System.out.println("Peak: " + rank.get("highest_rank").getAsString());
        System.out.println();
        System.out.println("\u001B[4m" + "Top Heroes:" + "\u001B[0m");
        System.out.println("Hero: " + hero1.get("hero_name").getAsString());
        System.out.println("KDA: " + hero1.get("kda").getAsString());
        System.out.println("Average KDA: " + hero1.get("avg_kda").getAsString());
        System.out.println("Winrate: " + hero1.get("winrate").getAsString());
        System.out.println("Total: " + hero1.get("total_games").getAsString());
        System.out.println();
        System.out.println("Hero: " + hero2.get("hero_name").getAsString());
        System.out.println("KDA: " + hero2.get("kda").getAsString());
        System.out.println("Average KDA: " + hero2.get("avg_kda").getAsString());
        System.out.println("Winrate: " + hero2.get("winrate").getAsString());
        System.out.println("Total: " + hero2.get("total_games").getAsString());
        System.out.println();
        System.out.println("Hero: " + hero3.get("hero_name").getAsString());
        System.out.println("KDA: " + hero3.get("kda").getAsString());
        System.out.println("Average KDA: " + hero3.get("avg_kda").getAsString());
        System.out.println("Winrate: " + hero3.get("winrate").getAsString());
        System.out.println("Total: " + hero3.get("total_games").getAsString());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Press any key to exit...");
        System.in.read();
    }
}