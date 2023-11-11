public class File {
    private volatile double data;

    public synchronized double getData()
    {
        return data;
    }

    public synchronized void setData(double data)
    {
        this.data = data;
    }
}
