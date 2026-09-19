public class Vertice {
    private final String rotulo;

    private int x;
    private int y;

    public Vertice(String rotulo, int x, int y) {
        this.rotulo = rotulo;
        this.x = x;
        this.y = y;
    }

    public String getRotulo() {
        return rotulo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}