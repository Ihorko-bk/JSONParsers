package name.ihorko.parsers;

import com.google.gson.*;
import name.ihorko.models.hard.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

public class HardGSON {
    public static Gson getSimpleGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();
    }
    public static Gson getCustomGson() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .registerTypeAdapter(SomeClass.class, new CustomGsonConverter())
                .create();
    }
}


class CustomGsonConverter implements JsonSerializer<SomeClass>, JsonDeserializer<SomeClass> {
    @Override
    public JsonElement serialize(SomeClass someClass, Type type, JsonSerializationContext jsonSerializationContext) {
        // creating main JsonObject(next - "JO") of SomeClass object
        JsonObject joSomeClass = new JsonObject();

        // addition JO of ClassWithMapAndEnum to JO of SomeClass obj
        joSomeClass.add("classWithMapAndEnum",
                getJsonObjectOfClassWithMapAndEnum(someClass.getClassWithMapAndEnum()));

        // addition "someString" property to JO of SomeClass obj
        joSomeClass.addProperty("someString", someClass.getSomeString());

        // addition someIntArray like a property of JO of SomeClass obj
        // by iterating of array and addition step by step each elements to JsonArray
        JsonArray jarrSomeIntArray = new JsonArray();
        for(int i: someClass.getSomeIntArray()) {
            jarrSomeIntArray.add(i);
        }
        joSomeClass.add("someIntArray", jarrSomeIntArray);

        // addition someThings ArrayList like an array of SomeThing JO
        JsonArray jarrSomeThings = new JsonArray();
        for(Something s: someClass.getSomeThings()) {
            jarrSomeThings.add(getJsonObjectOfSomeThing(s));
        }
        joSomeClass.add("someThings", jarrSomeThings);

        // returning already complete JO of SomeClass
        return joSomeClass;
    }

    private JsonElement getJsonObjectOfClassWithMapAndEnum(ClassWithMapAndEnum classWithMapAndEnum) {
        // creating JO of ClassWithMapAndEnum obj
        JsonObject joClassWithMapAndEnum = new JsonObject();

        // map will be like array with JO with two JO "key" and "value"
        JsonArray jarrMap = new JsonArray();

        LinkedHashMap<MyKey, MyValue> map = classWithMapAndEnum.getMap();
        Set<MyKey> mapKeys = map.keySet();

        JsonObject joMap;
        for(MyKey key: mapKeys) {
            joMap = new JsonObject();
            joMap.add("key", getJsonObjectOfMyKey(key));
            joMap.add("value", getJsonObjectOfMyValue(map.get(key)));

            jarrMap.add(joMap);
        }
        joClassWithMapAndEnum.add("map", jarrMap);

        // MyEnum property
        joClassWithMapAndEnum.addProperty("myEnum", classWithMapAndEnum.getMyEnum().toString());

        return joClassWithMapAndEnum;
    }

    private JsonElement getJsonObjectOfMyKey(MyKey myKey) {
        JsonObject joMyKey = new JsonObject();
        joMyKey.addProperty("key", myKey.getKey());
        joMyKey.addProperty("keyName", myKey.getKeyName());
        return joMyKey;
    }
    private JsonElement getJsonObjectOfMyValue(MyValue myValue) {
        JsonObject joMyValue = new JsonObject();
        joMyValue.addProperty("date", myValue.getDate().getTime());
        joMyValue.addProperty("whatDate", myValue.getWhatDate());
        return joMyValue;
    }

    private JsonElement getJsonObjectOfSomeThing(Something something) {
        // creating JO of Something obj
        // and addition things array to it
        JsonObject joSomeThing = new JsonObject();
        JsonArray jarrThings = new JsonArray();
        for(String s: something.getThings()) {
            jarrThings.add(s);
        }
        joSomeThing.add("things", jarrThings);

        return joSomeThing;
    }
/*
{
  "classWithMapAndEnum": {
    "map": [
      {
        "key": {
          "key": 1,
          "keyName": "1st key"
        },
        "value": {
          "date": 1569759566879,
          "whatDate": "right meow"
        }
      },
      {
        "key": {
          "key": 2,
          "keyName": "2st key"
        },
        "value": {
          "date": 1569759566879,
          "whatDate": "right meow"
        }
      }
    ],
    "myEnum": "FIRST"
  },
  "someString": "some string",
  "someIntArray": [
    1,
    2,
    3
  ],
  "someThings": [
    {
      "things": [
        "first string",
        "second string",
        "third string"
      ]
    },
    {
      "things": [
        "first string",
        "second string",
        "third string"
      ]
    },
    {
      "things": [
        "first string",
        "second string",
        "third string"
      ]
    }
  ]
}
*/

    @Override
    public SomeClass deserialize(JsonElement jsonElement, Type type,
                                 JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject joSomeClass = jsonElement.getAsJsonObject();

        return new SomeClass(getClassWithMapAndEnumFromJO(joSomeClass.get("classWithMapAndEnum").getAsJsonObject()),
                joSomeClass.get("someString").getAsString(),
                getSomeIntArrayFromJArr(joSomeClass.getAsJsonArray("someIntArray")),
                getSomeThingsArrayListFromJArr(joSomeClass.getAsJsonArray("someThings")));
    }

    private ClassWithMapAndEnum getClassWithMapAndEnumFromJO(JsonObject joClassWithMapAndEnum) {

        LinkedHashMap<MyKey, MyValue> map =
                getLinkedHashMapFromJarr(joClassWithMapAndEnum.getAsJsonArray("map"));

        MyEnum myEnum;
        switch (joClassWithMapAndEnum.get("myEnum").getAsString()) {
            case "FIRST": myEnum = MyEnum.FIRST; break;
            case "SECOND": myEnum = MyEnum.SECOND; break;
            default: myEnum = MyEnum.THIRD;
        }

        return new ClassWithMapAndEnum(map, myEnum);
    }

    private LinkedHashMap<MyKey, MyValue> getLinkedHashMapFromJarr(JsonArray jarrMap) {
        LinkedHashMap<MyKey, MyValue> map = new LinkedHashMap<>();

        JsonObject joKeyValue;
        JsonObject joKey, joValue;
        MyKey myKey;
        MyValue myValue;
        for (int i = 0; i < jarrMap.size(); i++) {
            joKeyValue = jarrMap.get(i).getAsJsonObject();

            joKey = joKeyValue.getAsJsonObject("key");
            myKey = new MyKey(joKey.get("key").getAsInt(), joKey.get("keyName").getAsString());

            joValue = joKeyValue.getAsJsonObject("value");
            myValue = new MyValue(
                    new Date(joValue.get("date").getAsLong()),
                    joValue.get("whatDate").getAsString());

            map.put(myKey, myValue);
        }

        return map;
    }

    private int[] getSomeIntArrayFromJArr(JsonArray jarrSomeIntArray) {
        int[] someIntArray = new int[jarrSomeIntArray.size()];
        for (int i = 0; i < jarrSomeIntArray.size(); i++) {
            someIntArray[i] = jarrSomeIntArray.get(i).getAsInt();
        }
        
        return someIntArray;
    }

    private ArrayList<Something> getSomeThingsArrayListFromJArr(JsonArray jarrSomeThings) {
        ArrayList<Something> somethings = new ArrayList<>();

        JsonObject joThing;
        JsonArray jarrThingsInside;
        String[] everySomeThingsInside;

        for (int i = 0; i < jarrSomeThings.size(); i++) {
            joThing = jarrSomeThings.get(i).getAsJsonObject();
            jarrThingsInside = joThing.getAsJsonArray("things");
            everySomeThingsInside = new String[jarrThingsInside.size()];
            for (int j = 0; j < jarrThingsInside.size(); j++) {
                everySomeThingsInside[j] = jarrThingsInside.get(j).getAsString();
            }
            somethings.add(new Something(everySomeThingsInside));
        }

        return somethings;
    }
}
