package name.ihorko.parsers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.ihorko.models.hard.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class HardJackson {

    public static void createJSONFile(SomeClass someClass, File file) {
        try (JsonGenerator jsonGen = fillJsonGenerator(getJsonGeneratorToWriteInFile(file), someClass)) {
            jsonGen.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createJSONString(SomeClass someClass) {
        Writer writer = new StringWriter();
        try (JsonGenerator jsonGen = fillJsonGenerator(getJsonGeneratorToWriteInString(writer), someClass)){
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private static JsonGenerator fillJsonGenerator(JsonGenerator jsonGenerator, SomeClass someClass) throws IOException {
        JsonGenerator jsonGen = jsonGenerator;

        // root(SomeClass)
        jsonGen.writeStartObject();

            // ClassWithMapAndEnum classWithMapAndEnum
            jsonGen.writeObjectFieldStart("classWithMapAndEnum");
                ClassWithMapAndEnum classWithMapAndEnum = someClass.getClassWithMapAndEnum();
                // LinkedHashMap<MyKey, MyValue> map;
                LinkedHashMap<MyKey, MyValue> map = classWithMapAndEnum.getMap();
                Set<MyKey> keys = map.keySet();
                Iterator<MyKey> iterator = keys.iterator();
                MyKey key;
                MyValue value;
                jsonGen.writeArrayFieldStart("map");
                while (iterator.hasNext()) {
                    key = iterator.next();
                    value = map.get(key);

                    jsonGen.writeStartObject();

                    jsonGen.writeObjectFieldStart("key");
                    jsonGen.writeNumberField("key", key.getKey());
                    jsonGen.writeStringField("keyName", key.getKeyName());
                    jsonGen.writeEndObject();

                    jsonGen.writeObjectFieldStart("value");
                    jsonGen.writeNumberField("date", value.getDate().getTime());
                    jsonGen.writeStringField("whatDate", value.getWhatDate());
                    jsonGen.writeEndObject();

                    jsonGen.writeEndObject();

                }
                jsonGen.writeEndArray();
                // MyEnum myEnum
                jsonGen.writeStringField("myEnum", classWithMapAndEnum.getMyEnum().toString());
            jsonGen.writeEndObject();

            // String someString
            jsonGen.writeStringField("someString", someClass.getSomeString());

            // int[] someIntArray
            int[] someIntArray = someClass.getSomeIntArray();
            jsonGen.writeArrayFieldStart("someIntArray");
            for (int i: someIntArray) {
                jsonGen.writeNumber(i);
            }
            jsonGen.writeEndArray();

            // ArrayList<Something> someThings
            ArrayList<Something> someThings = someClass.getSomeThings();
            jsonGen.writeArrayFieldStart("someIntArray");
            for (Something s: someThings) {
                jsonGen.writeStartArray();
                for (String thing: s.getThings()) {
                    jsonGen.writeString(thing);
                }
                jsonGen.writeEndArray();
            }
            jsonGen.writeEndArray();

        // end of root
        jsonGen.writeEndObject();

        return jsonGen;
    }

    private static JsonGenerator getJsonGeneratorToWriteInFile(File file) throws IOException {
        return new JsonFactory()
                .createGenerator(new FileOutputStream(file))
                .setPrettyPrinter(new DefaultPrettyPrinter());
    }
    private static JsonGenerator getJsonGeneratorToWriteInString(Writer writer) throws IOException {
        return new JsonFactory()
                .createGenerator(writer)
                .setPrettyPrinter(new DefaultPrettyPrinter());
    }

}
