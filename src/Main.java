import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the connection game!");

        //initializing scanner
        Scanner in = new Scanner(System.in);

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

        //terminating scanner after completion of inputs
        in.close();

        // Initialize Game object which controls the game (random points generation & ArrayLists)
        Game game = new Game();

        // Start game and add user-collected numbers
        game.startGame(n);

        // Create t threads using ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(t);

        // Submit t number of tasks to ExecutorService
        for (int i = 0; i < n / 2 + 1; i++) {
            executor.submit(new ConnectingEdges(game));
        }

        // Terminate after m seconds
        try {
            executor.awaitTermination(m, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }

        // Close/Shutdown Executor
        executor.shutdown();

        // Display final results for points left and connections (Edges) made
        System.out.println("Points Left (" + game.getSet().size() + ") ");
        System.out.println(game.getSet().toString());
        System.out.println("Points Connected (" + game.getEdges().size() + ") ");
        System.out.println(game.getEdges().toString());

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


