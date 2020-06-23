import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the connection game!");

        Scanner in = new Scanner(System.in);

        // Getting random points count (n)
        System.out.print("Please type in count of random points: ");
        int n = in.nextInt();

        // Getting threads count (t)
        System.out.print("Please type in count of threads: ");
        int t = in.nextInt();

        // Making sure thread count is less than random points count
        while (t > n){
            System.out.print("Please type count of threads (less than points): ");
            t = in.nextInt();
        }

        // Getting number of seconds (m)
        System.out.print("Please type in number of seconds: ");
        int m = in.nextInt();

        // Initialize Game object which controls the game (random points generation & ArrayLists)
        Game game = new Game();

        // Start game and add user-collected numbers
        game.startGame(n);

        // Create t threads using ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(t);

        // Submit t number of tasks to ExecutorService
        for (int i=0; i<n/2+1; i++){
            executor.submit(new ConnectingEdges(game));
        }

        // Terminate after m seconds
        try {
            executor.awaitTermination(m, TimeUnit.SECONDS);
        } catch (InterruptedException e){
        }

        // Close/Shutdown Executor
        executor.shutdownNow();

        // Display final results for points left and connections (Edges) made
        System.out.println("Points Left ("+ game.getSet().size() +") ");
        System.out.println(game.getSet().toString());
        System.out.println("Points Connected ("+ game.getEdges().size() +") ");
        System.out.println(game.getEdges().toString());

    }
}
