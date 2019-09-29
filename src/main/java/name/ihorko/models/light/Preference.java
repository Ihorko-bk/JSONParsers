package name.ihorko.models.light;

public class Preference {

    private Font font;
    private Window window;


    public Preference() {
    }
    public Preference(Font font, Window window) {
        this.font = font;
        this.window = window;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    public Font getFont() {
        return font;
    }

    public void setWindow(Window window) {
        this.window = window;
    }
    public Window getWindow() {
        return window;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "font=" + font +
                ", window=" + window +
                '}';
    }
}
