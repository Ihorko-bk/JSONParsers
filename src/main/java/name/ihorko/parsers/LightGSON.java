package name.ihorko.parsers;


import com.google.gson.*;
import name.ihorko.models.light.Preference;

import java.lang.reflect.Type;


public class LightGSON implements JsonSerializer<Preference>, JsonDeserializer<Preference> {

    private GsonBuilder gsonBuilder;
    private Gson gson;

    public LightGSON() {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Preference.class, this);
        gson = gsonBuilder.create();
    }

    public Preference getPreferenceFromJSON(String string) {
        return gson.fromJson(string, Preference.class);
    }

    @Override
    public JsonElement serialize(Preference preference, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonElement jeFont = new JsonElement() {
            @Override
            public JsonElement deepCopy() {
                return null;
            }
        };

        JsonObject joFont = new JsonObject();


        JsonObject joWindow = new JsonObject();

        JsonObject joPreference = new JsonObject();

        return null;
    }

    @Override
    public Preference deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {



        return null;
    }
}
