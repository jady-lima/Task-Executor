public class Worker extends Thread{
    private Executor executor = Executor.getInstance();
    private File file;
    private int part, workerID;

    public Worker(int part, int workerID)
    {
        this.part = part;
        this.workerID = workerID;
        file = new File();
    }

    @Override
    public void run() {
        try {

            for (int i = (workerID * part); i < ((workerID + 1) * part); ) {
                Task task = executor.getTasks().poll();
                Thread.sleep((long) task.getCost());

                synchronized (file) {
                    if (task != null) {
                        if (task.getType() == 0) {
                            file.setData(task.getValue() + file.getData());
                            //executor.printTask(task);
                        } else if (task.getType() == 1) {
                            System.out.println("File: " + file.getData());
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
