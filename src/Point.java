public class Point {
    public double x_coordinate; // X coordinate point
    public double y_coordinate; // Y coordinate point

    /**
     * Constructor
     *
     * @param x
     * @param y
     */
    public Point(double x, double y){
        x_coordinate = x;
        y_coordinate = y;
    }

    @Override
    public String toString() {
        return "(" + String.format("%.2f", x_coordinate) + ", " + String.format("%.2f", y_coordinate)+")";
    }
}
