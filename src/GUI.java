import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GUI extends JFrame {

    private JTextField pointsTextField;
    private JPanel mainPanel;
    private JTextField threadsTextField;
    private JTextField secondsTextField;
    private JButton submitButton;
    private JLabel pointsLabel;
    private JLabel threadsLabel;
    private JLabel secondsLabel;
    private JLabel errorLabel;
    public int n;
    public int t;
    public int m;
    public boolean isDone;

    public GUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    n = (int)(Integer.parseInt(pointsTextField.getText()));

                    t = (int)(Integer.parseInt(threadsTextField.getText()));

                    if (t > n) {
                        JOptionPane.showMessageDialog(null,
                                "Error: Threads must be less than the points");
                    } else {
                        m = (int)(Integer.parseInt(secondsTextField.getText()));
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
                                }

                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        executor.shutdown();

                        printStatus(game, threadsStore);

                        EventQueue.invokeLater(() -> {

                            var ex = new Chart(game.getEdgesList());
                            ex.setVisible(true);
                        });
                    }

                    setVisible(false);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Error: You must enter an integer");
                }
            }
        });
    }

    public static void main(String[] args) {
        GUI frame = new GUI("Start Game");
        frame.setVisible(true);
        frame.setSize(300, 200);
    }

    public static void printStatus(Game game, HashMap threadStore) {
        JFrame frame = new JFrame(" Thread Details");
        StringBuilder all = new StringBuilder("<html>");
        JLabel label = new JLabel();
        Iterator iterator = threadStore.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry me = (Map.Entry) iterator.next();
            all.append(me);
            all.append("<br/><br/>");
        }
        all.append("</html>");
        label.setText(all.toString());

        frame.add(label);
        frame.setSize(1000, 400);
        frame.setVisible(true);
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
