package name.ihorko.models.hard;

public class MyKey {
    int key;
    String keyName;

    public MyKey() {
    }
    public MyKey(int key, String keyName) {
        this.key = key;
        this.keyName = keyName;
    }

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public String getKeyName() {
        return keyName;
    }
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    @Override
    public String toString() {
        return "MyKey{" +
                "key=" + key +
                ", keyName='" + keyName + '\'' +
                '}';
    }
}
