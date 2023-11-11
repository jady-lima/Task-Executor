import java.util.*;

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
        tasks = new LinkedList<>();
        results = new LinkedList<>();
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

            if (random.nextInt(2) == 0) {
                if (maxWriter > 0) {
                    type = 0;
                    maxWriter--;
                } else {
                    type = 1;
                }
            } else {
                type = 1;
            }

            Task task = new Task(taskID, cost, type, value);
            tasks.add(task);
            taskID++;
        }
    }

    public void createWorkers(double numberTask, int T)
    {
        int part = (int) (numberTask / T);
        Thread[] threads = new Worker[T];
        for (int i = 0; i < T; i++) {
            for (int j = i * part; j < (i + 1) * part; j++) {
                Task task = tasks.poll();
                threads[i] = new Worker(part, j, task);
                threads[i].start();
            }
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

    public void readTasks()
    {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.poll();
            printTask(task);
        }
    }

    public void printTask(Task task)
    {
        System.out.printf("ID: %d \nCusto: %.2f \nTipo: %d \nValor: %.2f \n"
                , task.getTaskId(), task.getCost(), task.getType(), task.getValue());
        System.out.println("--------------------------------------------------");

    }


    public void addResult(Result result)
    {
        results.add(result);
    }

    public Queue<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(Queue<Task> tasks)
    {
        this.tasks = tasks;
    }

    public Queue<Result> getResults()
    {
        return results;
    }

    public void setResults(Queue<Result> results)
    {
        this.results = results;
    }
}
