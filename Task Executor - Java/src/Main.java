import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
        maxReader = (int) numberTask - (numberTask * E) / 10;
        maxWriter = (numberTask - maxReader);

        executor.createTasks((int) numberTask, (int) maxWriter);

        int part = (int) (numberTask / T);
        Thread[] threads = new Worker[T];
        for (int i = 0; i < T; i++) {
            threads[i] = new Worker(part, i);
            threads[i].start();
        }

        for (int i = 0; i < T; i++)
        {
            try{
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}