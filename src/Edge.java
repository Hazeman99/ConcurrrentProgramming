public class Edge {
    Point firstPoint; // First Point Object
    Point secondPoint; // Second Point Object

    /**
     * Constructor
     *
     * @param first
     * @param second
     */
    public Edge(Point first, Point second){
        firstPoint = first;
        secondPoint = second;
    }

    @Override
    public String toString() {
        return "firstPoint: " + firstPoint.toString() + "; secondPoint: " + secondPoint.toString();
    }
}
