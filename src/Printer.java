import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Printer {
    public void printProfile(JsonObject obj){

        JsonObject profile = obj.get("profile").getAsJsonObject();
        JsonObject rank = obj.get("rank").getAsJsonObject();
        JsonObject heroes = obj.get("top_heroes").getAsJsonObject();
        JsonArray comp = heroes.get("competitive").getAsJsonArray();
        JsonObject hero1 = comp.get(0).getAsJsonObject();
        JsonObject hero2 = comp.get(1).getAsJsonObject();
        JsonObject hero3 = comp.get(2).getAsJsonObject();

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
    }

    public void printCareer(JsonObject obj){
        JsonArray career = obj.get("matches").getAsJsonArray();
        JsonObject game0 = career.get(0).getAsJsonObject();

        System.out.println("\u001B[4m" + obj.get("player_name").getAsString() + "`s career':" + "\u001B[0m");

        for (int i = 0; i < 6; i++) {
            JsonObject game = career.get(i).getAsJsonObject();
            System.out.println("Hero: " + game.get("hero").getAsString());
            System.out.println("Date: " + game.get("date").getAsString());
            System.out.println("Duration: " + game.get("duration").getAsString().replaceAll("WIN", "").replaceAll("LOSS", ""));
            System.out.println("KDA: " + game.get("kda").getAsString().replaceAll("\n", " - "));
            System.out.println("Result: " + game.get("result").getAsString().replaceAll("match win", "WIN").replaceAll("match loss", "LOSS"));
            System.out.println("Map: " + game.get("map").getAsString());
            System.out.println();
        }

        System.out.println();



    }
}
