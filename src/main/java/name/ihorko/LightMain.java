package name.ihorko;

import com.google.gson.Gson;
import name.ihorko.models.light.Font;
import name.ihorko.models.light.Preference;
import name.ihorko.models.light.Window;

import java.io.File;

public class LightMain {
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

//        ArrayList<Preference> preferences = getPreferencesFromJSON(jsonFile);
//
//        for(Preference p: preferences) {
//            System.out.println(p);
//        }

//        ArrayList<Preference> preferences = new ArrayList<>();
//        Preference[] preferences;
////        preferences.add(preference);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
////            objectMapper.writeValue(new File("json1(LiteJackson writer).json"), preferences);
////            Preference preference1 = objectMapper.readValue(jsonFile, Preference.class);
////            System.out.println(preference1);
//            preferences = objectMapper.readValue(jsonFile, Preference[].class);
//            System.out.println(preferences[0]);
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Gson gson = new Gson();
        String string = gson.toJson(preference);
        System.out.println(string);

        Preference gsonPreference = gson.fromJson(string, Preference.class);
        System.out.println(gsonPreference);

    }
}
