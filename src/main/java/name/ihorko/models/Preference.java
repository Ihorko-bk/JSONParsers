package name.ihorko.models;

public class Preference {
    private Font font;

    public Preference() {
    }

    public Preference(Font font, Window window) {
        this.font = font;
        this.window = window;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    private Window window;

    @Override
    public String toString() {
        return "Preference{" +
                "font=" + font +
                ", window=" + window +
                '}';
    }
}
