package name.ihorko.models.hard;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

public class HardClass {
    public static SomeClass getSomeClass() {
        return new SomeClass(getClassWithMapAndEnum(), "some string", new int[]{1,2,3},
                new ArrayList<Something>(){{add(getSomething()); add(getSomething()); add(getSomething());}});
    }

    private static ClassWithMapAndEnum getClassWithMapAndEnum() {
        LinkedHashMap<MyKey, MyValue> map = new LinkedHashMap<MyKey, MyValue>(){{
            put(getMyKey(1), getMyValue());
            put(getMyKey(2), getMyValue());
        }};
        return new ClassWithMapAndEnum(map, MyEnum.FIRST);
    }

    private static MyKey getMyKey(int key) {
        return new MyKey(key, key+"st key");
    }
    private static MyValue getMyValue() {
        return new MyValue(new Date(System.currentTimeMillis()), "right meow");
    }

    private static Something getSomething() {
        String[] things = new String[3];
        things[0] = "first string";
        things[1] = "second string";
        things[2] = "third string";
        return new Something(things);
    }
}
