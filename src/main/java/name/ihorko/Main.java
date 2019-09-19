package name.ihorko;

import name.ihorko.models.Font;
import name.ihorko.models.Preference;
import name.ihorko.models.Window;

import java.io.File;
import java.util.ArrayList;

import static name.ihorko.parsers.JSON_Simple.*;

public class Main {
    public static void main(String[] args) {
        Preference preference = new Preference(
            new Font(
                "another one role",
                "Monotype Corsia?",
                22
            ),
            new Window(
                10, 15, 1, 1
            )
        );

        File jsonFile = new File("json.json");

//        createJSONFile(preference, jsonFile);

        ArrayList<Preference> preferences = getPreferencesFromJSON(jsonFile);

        for(Preference p: preferences) {
            System.out.println(p);
        }


    }
}
