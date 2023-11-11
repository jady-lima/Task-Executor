public class Task {
    private int TaskId;
    private double cost;
    private int type;
    private double value;

    public Task(int taskId, double cost, int type, double value) {
        this.TaskId = taskId;
        this.cost = cost;
        this.type = type;
        this.value = value;
    }

    public int getTaskId()
    {
        return TaskId;
    }

    public void setTaskId(int taskId)
    {
        TaskId = taskId;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }
}
