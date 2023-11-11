import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Executor executor = Executor.getInstance();

        int N = 0, E = 0 , T = 0;
        double maxWriter = 0, numberTask = 0, maxReader = 0;

        System.out.println("N: ");
        N = sc.nextInt();
        System.out.println("T: ");
        T = sc.nextInt();

        while (true) {
            System.out.println("E: ");
            E = sc.nextInt();
            if (E >= 0 && E <= 100)
            {
                break;
            }
        }

        numberTask = (int) Math.pow(10, N);
        maxReader = (int) numberTask - (numberTask * E) / 100;
        maxWriter = (numberTask - maxReader);

        executor.createTasks((int) numberTask, (int) maxWriter);

        long startTime = System.nanoTime();
        executor.createWorkers(numberTask, T);
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        double finalTime = (double) time / 1000000000.0;
        System.out.println("Time: " + finalTime);
    }
}