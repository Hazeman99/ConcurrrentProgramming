import java.util.ArrayList;
import java.util.Random;

public class Game {
    ArrayList<Point> points; // ArrayList of all generated Point
    ArrayList<Edge> edges; // ArrayList of all connections/Edges

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
        Random r = new Random();
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

    // do a points shift
    public Point shiftPoints(){
        return points.remove(0);
    }

    // Add Edge to ArrayList
    public void addEdge(Edge e){
        edges.add(e);
    }

    // Get Point ArrayList
    public ArrayList<Point> getSet(){
        return points;
    }

    // Get Edges ArrayList
    public ArrayList<Edge> getEdges(){
        return edges;
    }
}
