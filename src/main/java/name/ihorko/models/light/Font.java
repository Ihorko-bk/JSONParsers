package name.ihorko.models.light;

public class Font {

    private String role;
    private String name;
    private int size;

    public Font() {
    }
    public Font(String role, String name, int size) {
        this.role = role;
        this.name = name;
        this.size = size;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Font{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
