package name.ihorko.parsers;

import name.ihorko.models.Font;
import name.ihorko.models.Preference;
import name.ihorko.models.Window;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSON_Simple {

    // creating and writing
    // -----------------------------------------------------------------------------------------------------------------
    public static void createJSONFile(List<Preference> preferences, File file) {
        writeJSONToFile(createPreferencesJSON(preferences), file);
    }

    public static void createJSONFile(Preference preference, File file) {
        writeJSONToFile(createPreferenceJSON(preference), file);
    }

    public static JSONArray createPreferencesJSON(List<Preference> preferences) {
        JSONArray preferencesJSONArray = new JSONArray();
        for (Preference preference : preferences) {
            preferencesJSONArray.add(createPreferenceJSON(preference));
        }
        return preferencesJSONArray;
    }

    public static JSONObject createPreferenceJSON(Preference preference) {
        JSONObject preferenceJSONObject = new JSONObject();

        preferenceJSONObject.put("font", createFontJSONObject(preference.getFont()));
        preferenceJSONObject.put("window", createWindowJSONObject(preference.getWindow()));

        return preferenceJSONObject;
    }

    private static JSONObject createFontJSONObject(Font font) {
        JSONObject fontJSONObject = new JSONObject();

        fontJSONObject.put("role", font.getRole());
        fontJSONObject.put("name", font.getName());
        fontJSONObject.put("size", font.getSize());

        return fontJSONObject;
    }
    private static JSONObject createWindowJSONObject(Window window) {
        JSONObject windowJSONObject = new JSONObject();

        windowJSONObject.put("height", window.getHeight());
        windowJSONObject.put("width", window.getWidth());
        windowJSONObject.put("locx", window.getLocx());
        windowJSONObject.put("locy", window.getLocy());

        return windowJSONObject;
    }

    public static void writeJSONToFile(JSONObject jsonObject, File file) {
        writeToFile(jsonObject.toJSONString(), file);
    }

    public static void writeJSONToFile(JSONArray jsonArray, File file) {
        writeToFile(jsonArray.toJSONString(), file);
    }

    private static void writeToFile(String string, File file) {
        try (FileWriter fileWriter = new FileWriter(file.getPath())) {
            fileWriter.write(string);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // -----------------------------------------------------------------------------------------------------------------
    // creating and writing


    // getting and reading
    // -----------------------------------------------------------------------------------------------------------------
    public static ArrayList<Preference> getPreferencesFromJSON(File file) {
        return getPreferencesFromJSON(readFromFile(file));
    }
    public static ArrayList<Preference> getPreferencesFromJSON(JSONArray jsonArray) {
        ArrayList<Preference> preferences = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            preferences.add(getPreferenceFromJSONObject((JSONObject) jsonArray.get(i)));
        }

        return preferences;
    }

    public static Preference getPreferenceFromJSONObject(JSONObject jsonObject) {
        return new Preference(
                getFontFromJSONObject(jsonObject.get("font")),
                getWindowFromJSONObject(jsonObject.get("window"))
        );
    }
    private static Font getFontFromJSONObject(Object object) {
        JSONObject jsonObject = (JSONObject) object;
        return new Font(
                (String) jsonObject.get("role"),
                (String) jsonObject.get("name"),
                ((Long) jsonObject.get("size")).intValue()
        );
    }
    private static Window getWindowFromJSONObject(Object object) {
        JSONObject jsonObject = (JSONObject) object;
        return new Window(
                ((Long) jsonObject.get("height")).intValue(),
                ((Long) jsonObject.get("width")).intValue(),
                ((Long) jsonObject.get("locx")).intValue(),
                ((Long) jsonObject.get("locy")).intValue()
        );
    }

    public static JSONArray readFromFile(File file) {
        JSONArray jsonArray = null;
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(file.getPath())) {
            jsonArray = (JSONArray) jsonParser.parse(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            // if we have only one preference object in JSON (not array)
            try (FileReader fileReader = new FileReader(file.getPath())) {
                jsonArray = new JSONArray();
                jsonArray.add((JSONObject) jsonParser.parse(fileReader));
            } catch (ParseException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return jsonArray;
    }
    // -----------------------------------------------------------------------------------------------------------------
    // getting and reading
}