import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<Point> points; // ArrayList of all generated Point
    ArrayList<Edge> edges; // ArrayList of all connections/Edges
    Random r = new Random();

    /**
     * Constructor
     */
    public Game(){
        points = new ArrayList<Point>();
        edges = new ArrayList<Edge>();
    }

    /**
     * Start game and generate random points/coordinates
     *
     * @param numbers
     */
    public void startGame(int numbers) {
        double x;
        double y;
        // Fill up the Point ArrayList with random Point
        while (points.size() < numbers){
            // Generate x and y coordinates
            x = r.nextDouble() * 1000;
            y = r.nextDouble() * 1000;

            // Initialize Point object with the x and y coordinates
            Point currentPoint = new Point(x, y);

            // If ArrayList does not include exact same Point then add to ArrayList
            if (!points.contains(currentPoint)){
                points.add(currentPoint);
            }
        }
    }

    public Point getRandomPoint(){
        int index = r.nextInt(points.size());
        Point point = points.get(index);
        points.remove(index);
        return point;
    }

    // Add Edge to ArrayList
    public void pushNewEdgeToCurrentEdges(Edge e){
        edges.add(e);
    }

    // Get Point ArrayList
    public ArrayList<Point> getPointsList(){
        return points;
    }

    // Get Edges ArrayList
    public ArrayList<Edge> getEdgesList(){
        return edges;
    }
}
