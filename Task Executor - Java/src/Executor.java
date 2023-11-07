import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Executor {
    private static Executor instance;
    private static int taskID = 1;
    private static int resultID = 1;
    private Random random = new Random();
    private Queue<Task> tasks;
    private Queue<Result> results;
    private int type = 0;
    private double value = 0, cost = 0;

    public Executor()
    {
        tasks = new LinkedList<Task>();
        results = new LinkedList<Result>();
    }

    public static Executor getInstance()
    {
        if (instance == null)
        {
            instance = new Executor();
        }
        return instance;
    }

    public void createTasks(int numberTask, int maxWriter)
    {
        for (int i = 0; i < numberTask; i++) {
            cost = random.nextDouble(0.01);
            value = random.nextDouble(10);

            type = random.nextInt(2);
            /**
            if(type == 0) {
                if (maxWriter > 0) {
                    maxWriter--;
                } else {
                    type = 1;
                }
            }
             */

            Task task = new Task(taskID, cost, type , value);
            tasks.offer(task);
            taskID++;
        }
    }

    public void readTasks()
    {
        while (!tasks.isEmpty()) {
            Task task = tasks.poll();
            printTask(task);
        }
    }

    public void newResult(Result result)
    {
        results.offer(result);
    }

    public void printTask(Task task)
    {
        System.out.printf("ID: %d \nCusto: %.2f \nTipo: %d \nValor: %.2f \n"
                , task.getTaskId(), task.getCost(), task.getType(), task.getValue());
        System.out.println("--------------------------------------------------");

    }
}
