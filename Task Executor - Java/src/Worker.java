import java.util.concurrent.Semaphore;

public class Worker extends Thread{
    private Executor executor = Executor.getInstance();
    private File file = File.getInstance();
    private Task task;
    private int part, workerID;
    private double time;

    public Worker(int part, int workerID, Task task)
    {
        this.part = part;
        this.workerID = workerID;
        this.task = task;
    }

    @Override
    public void run() {
        try {

            //for (int i = (workerID * part); i < ((workerID + 1) * part);) {
                long startTime = System.nanoTime();

                //Task task = executor.getTasks().poll();

                if (task != null) {

                    synchronized (file) {

                        if (task.getType() == 0) {
                            //Thread.sleep((long) task.getCost());

                            file.setData(task.getValue() + file.getData());

                            long endTime = System.nanoTime();
                            long time = endTime - startTime;
                            double finalTime = (double) time / 1000000.0;

                            Result result = new Result(task.getTaskId(), file.getData(), finalTime);
                            executor.addResult(result);

                        } else {
                            System.out.println("File: " + file.getData());

                            long endTime = System.nanoTime();
                            long time = endTime - startTime;
                            double finalTime = (double) time / 1000000.0;

                            Result result = new Result(task.getTaskId(), file.getData(), finalTime);
                            executor.addResult(result);
                        }

                        //i++;
                    }

                }

            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
