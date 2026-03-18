import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class Cacher {
    public void cache(JsonObject obj, String uid, String input) {

        try {
            FileWriter writer;
            if (input.equals(uid)) {
                writer = new FileWriter("cache/" + uid + ".json");
            } else {
                writer = new FileWriter("cache/" + uid + "-" + input + ".json");
            }
            writer.write(obj.toString());
            System.out.println("Response cached");
            writer.close();

        } catch (
                IOException e) {
            System.out.println("caching error");
            System.out.println();
        }
    }
}
