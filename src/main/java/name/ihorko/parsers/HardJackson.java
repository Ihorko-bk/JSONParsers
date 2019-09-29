package name.ihorko.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import name.ihorko.models.hard.SomeClass;

import java.io.IOException;

public class HardJackson {
    public static String createJSONString(SomeClass someClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(someClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static SomeClass creareSomeClassFromJSONString(String jsonString) {
        SomeClass someClass = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            someClass = objectMapper.readValue(jsonString, SomeClass.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return someClass;
    }
}
