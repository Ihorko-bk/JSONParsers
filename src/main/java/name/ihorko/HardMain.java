package name.ihorko;

import com.google.gson.Gson;
import name.ihorko.models.hard.HardClass;
import name.ihorko.models.hard.SomeClass;
import name.ihorko.parsers.HardGSON;
import name.ihorko.parsers.HardJackson;

/*
https://blog.overops.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/
GSON for small JSONs
Jackson of JSON.simple for big
 */

public class HardMain {
    public static void main(String[] args) {

        SomeClass someClass = HardClass.getSomeClass();

        // -------------------------------------------------------------------------------------------------------------
        // GSON {

//        Gson gson = HardGSON.getCustomGson();
//        String jsonGSON = gson.toJson(someClass, SomeClass.class);
//        System.out.println(jsonGSON);
//
//        SomeClass someClassGSON = gson.fromJson(jsonGSON, SomeClass.class);
//
//        System.out.println(someClassGSON);

        // } GSON
        // -------------------------------------------------------------------------------------------------------------

        // -------------------------------------------------------------------------------------------------------------
        // Jackson {

        // Jackson поводить себе аналогічно GSONy при серіалізації тому потребує ручної се- та десеріалізації

        String jsonJackson = HardJackson.createJSONString(someClass);
        System.out.println(jsonJackson);

        SomeClass someClassJackson = HardJackson.creareSomeClassFromJSONString(jsonJackson);
        System.out.println(someClassJackson);

        // } Jackson
        // -------------------------------------------------------------------------------------------------------------
    }
}
