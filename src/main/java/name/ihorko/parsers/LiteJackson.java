package name.ihorko.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import name.ihorko.models.light.Preference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiteJackson {
    public static void createJSONFile(List<Preference> preferences, File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, preferences);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createJSONFile(Preference preference, File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(file, preference);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Preference getPreferenceFromJSON(File file) {
        Preference preference = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            preference = objectMapper.readValue(file, Preference.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return preference;
    }
    public static ArrayList<Preference> getPreferencesFromJSON(File file) {
        ArrayList<Preference> preferences = null;
        Preference[] preferencesArray;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            preferencesArray = objectMapper.readValue(file, Preference[].class);
            preferences = new ArrayList<>(Arrays.asList(preferencesArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return preferences;
    }
}
