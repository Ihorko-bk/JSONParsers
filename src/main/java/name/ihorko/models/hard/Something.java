package name.ihorko.models.hard;

import java.util.Arrays;

public class Something {
    String[] things;

    public Something() {
    }
    public Something(String[] things) {
        this.things = things;
    }

    public String[] getThings() {
        return things;
    }
    public void setThings(String[] things) {
        this.things = things;
    }

    @Override
    public String toString() {
        return "Something{" +
                "things=" + Arrays.toString(things) +
                '}';
    }
}
