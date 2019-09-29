package name.ihorko.models.hard;

import java.util.LinkedHashMap;
import java.util.Set;

public class ClassWithMapAndEnum {
    LinkedHashMap<MyKey, MyValue> map;
    MyEnum myEnum;

    public ClassWithMapAndEnum() {
    }
    public ClassWithMapAndEnum(LinkedHashMap<MyKey, MyValue> map, MyEnum myEnum) {
        this.map = map;
        this.myEnum = myEnum;
    }

    public LinkedHashMap<MyKey, MyValue> getMap() {
        return map;
    }
    public void setMap(LinkedHashMap<MyKey, MyValue> map) {
        this.map = map;
    }

    public MyEnum getMyEnum() {
        return myEnum;
    }
    public void setMyEnum(MyEnum myEnum) {
        this.myEnum = myEnum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<MyKey> myKeys = map.keySet();
        for(MyKey m: myKeys) {
            sb.append("<" + m + ", " + map.get(m) + ">");
        }
        String enumName = "";
        switch (myEnum) {
            case FIRST: enumName = "First"; break;
            case SECOND: enumName = "Second"; break;
            case THIRD: enumName = "Third";
        }
        return "ClassWithMapAndEnum{" +
                "map=" + sb.toString() +
                ", myEnum=" + enumName +
                '}';
    }
}
