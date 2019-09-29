package name.ihorko.models.hard;

import java.util.ArrayList;
import java.util.Arrays;

public class SomeClass {
    ClassWithMapAndEnum classWithMapAndEnum;
    String someString;
    int[] someIntArray;
    ArrayList<Something> someThings;

    public SomeClass() {
    }
    public SomeClass(ClassWithMapAndEnum classWithMapAndEnum, String someString, int[] someIntArray,
                     ArrayList<Something> someThings) {
        this.classWithMapAndEnum = classWithMapAndEnum;
        this.someString = someString;
        this.someIntArray = someIntArray;
        this.someThings = someThings;
    }

    public ClassWithMapAndEnum getClassWithMapAndEnum() {
        return classWithMapAndEnum;
    }
    public void setClassWithMapAndEnum(ClassWithMapAndEnum classWithMapAndEnum) {
        this.classWithMapAndEnum = classWithMapAndEnum;
    }

    public String getSomeString() {
        return someString;
    }
    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public int[] getSomeIntArray() {
        return someIntArray;
    }
    public void setSomeIntArray(int[] someIntArray) {
        this.someIntArray = someIntArray;
    }

    public ArrayList<Something> getSomeThings() {
        return someThings;
    }

    public void setSomeThings(ArrayList<Something> someThings) {
        this.someThings = someThings;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "classWithMapAndEnum=" + classWithMapAndEnum +
                ", someString='" + someString + '\'' +
                ", someIntArray=" + Arrays.toString(someIntArray) +
                ", someThings=" + someThings +
                '}';
    }
}
