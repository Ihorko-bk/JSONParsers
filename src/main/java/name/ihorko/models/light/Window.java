package name.ihorko.models.light;

public class Window {
    public Window() {
    }

    public Window(int height, int width, int locx, int locy) {
        this.height = height;
        this.width = width;
        this.locx = locx;
        this.locy = locy;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLocx() {
        return locx;
    }

    public void setLocx(int locx) {
        this.locx = locx;
    }

    public int getLocy() {
        return locy;
    }

    public void setLocy(int locy) {
        this.locy = locy;
    }

    private int height;
    private int width;
    private int locx;
    private int locy;

    @Override
    public String toString() {
        return "Window{" +
                "height=" + height +
                ", width=" + width +
                ", locx=" + locx +
                ", locy=" + locy +
                '}';
    }
}
