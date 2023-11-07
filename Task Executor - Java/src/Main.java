import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Executor executor = Executor.getInstance();

        int N = 0, E = 0, maxWriter = 0, numberTask = 0;

        System.out.println("N: ");
        N = sc.nextInt();
        while (true) {
            System.out.println("E: ");
            E = sc.nextInt();
            if (E >= 0 && E <= 100)
            {
                break;
            }
        }

        numberTask = (int) Math.pow(10, N);
        maxWriter = numberTask * (E / 100);

        executor.createTasks(numberTask, maxWriter);
        executor.readTasks();
    }
}