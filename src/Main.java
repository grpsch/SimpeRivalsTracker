import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String input;
        String uid;
        JsonObject obj = null;
        Getter getter = new Getter();
        Cacher cacher = new Cacher();
        Printer printer = new Printer();
        boolean notCached = true;
        String useCase = "profile";

        //create cache folder if not already existing
        new File("cache").mkdirs();

        //ask user for input
        System.out.print("Enter username or uid to scan: ");
        input = scanner.nextLine();



        //scan cache folder
        File cache = new File("cache");
        File[] data = cache.listFiles();

        if (data != null) {
            for (File cachefile : data) {
                if (cachefile.getName().contains(input)) {
                    String content = new String(Files.readAllBytes(cachefile.toPath()));
                    obj = JsonParser.parseString(content).getAsJsonObject();
                    notCached = false;
                    break;
                }
            }
        }
        if (notCached) {
            uid = getter.getID(input);
            obj = getter.getData(uid, useCase);
            cacher.cache(obj, uid, input);
        }

        printer.printProfile(obj);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Press any key to exit...");
        System.in.read();


    }
}