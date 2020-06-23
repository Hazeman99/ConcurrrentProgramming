import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the connection game!");

        // Getting random points count (n)
        int n = readInt("Please type in count of random points: ");

        // Getting threads count (t)
        int t = readInt("Please type in count of threads: ");

        // Making sure thread count is less than random points count
        while (t > n) {
            System.out.print("Please type count of threads (less than points): ");
            t = readInt("Please type in count of threads: ");
        }

        // Getting number of seconds (m)
        int m = readInt("Please type in number of seconds: ");


        // Initialize Game object which controls the game (random points generation & ArrayLists)
        Game game = new Game();

        // Start game and add user-collected numbers
        game.startGame(n);

        // Create t threads using ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(t);
        HashMap<String, ThreadStatus> threadsStore = new HashMap<String, ThreadStatus>();

        // Submit t number of tasks to ExecutorService
        for (int i = 0; i < n / 2 + 1; i++) {
            Future<ThreadStatus> f = executor.submit(new ConnectingEdges(game));
            try {
                ThreadStatus ts = f.get();
                String currentThreadName = ts.getThreadName();

                if (threadsStore.containsKey(currentThreadName)) {

                    ThreadStatus currentThreadStatus = threadsStore.get(currentThreadName);

                    if (ts.getStatusCode() == "SUCCESS") {
                        currentThreadStatus.increaseCompletedTaskCountByOne();
                    } else {
                        currentThreadStatus.increaseFailuresCountByOne();
                    }
                    threadsStore.put(currentThreadName, currentThreadStatus);

                } else {
                    threadsStore.put(currentThreadName, ts);
                }

                if (threadsStore.get(currentThreadName).getFailuresCount() >= 20) {
                    // terminate after 20 failed attempts.
                    executor.shutdownNow();
                    break;
//                    executor.awaitTermination(1, TimeUnit.NANOSECONDS);
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        executor.shutdown();

        printStatus(game, threadsStore);

    }

    public static void printStatus(Game game, HashMap threadStore) {

        threadStore.forEach((k, v) -> {
            System.out.println(v);
        });

        // Display final results for points left and connections (Edges) made
//        System.out.println("Points Left (" + game.getPointsList().size() + ") ");
//        System.out.println(game.getPointsList().toString());
//        System.out.println("Points Connected (" + game.getEdgesList().size() + ") ");
//        System.out.println(game.getEdgesList().toString());
    }

    public static int readInt(String msg) {
        boolean error = false;
        int x = 0;
        do {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print(msg);
                x = in.nextInt();
                if (0 >= x) {
                    error = true;
                    System.out.println("Please enter a positive number only");
                } else {
                    error = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input value, Please Input Integer Only:");
                error = true;
            }
        } while (error);
        return (x);
    }

}