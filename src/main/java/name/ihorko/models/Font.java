package name.ihorko.models;

public class Font {
    public Font() {
    }

    public Font(String role, String name, int size) {
        this.role = role;
        this.name = name;
        this.size = size;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private String role;
    private String name;
    private int size;

    @Override
    public String toString() {
        return "Font{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
