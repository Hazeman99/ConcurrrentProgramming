import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectingEdges implements Callable {
    // Lock for when creating Edges
    private static Lock lock = new ReentrantLock();
    ThreadStatus status =  new ThreadStatus();
    // Game object to retrieve Point and Edges ArrayLists
    Game game;

    public ConnectingEdges(Game gameObj) {
        game = gameObj;
    }

    @Override
    public ThreadStatus call() throws Exception {
        status.setThreadName(Thread.currentThread().getName());
        lock.lock();
        try {
            // Form an edge using 2 Points
            if (game.getPointsList().size() > 1) {
                Point point1 = game.getRandomPoint();
                Point point2 = game.getRandomPoint();
                Edge currentEdge = new Edge(point1, point2);
                game.pushNewEdgeToCurrentEdges(currentEdge);
                status.increaseCompletedTaskCountByOne();
                status.setStatusCode("SUCCESS");
                return status;
//                System.out.println(Thread.currentThread().getName() + ": Added " + point1.toString() + " and " + point2);
//            } else if (game.getPointsList().size() == 1) {
//                status.increaseFailuresCountByOne();
            } else {
                status.setStatusCode("FAIL");
                status.increaseFailuresCountByOne();
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
            return status;
        }
    }
}
