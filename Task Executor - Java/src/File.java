public class File {
    private static File instance;
    private volatile double data;

    public static File getInstance()
    {
        if (instance == null)
        {
            instance = new File();
        }
        return instance;
    }


    public double getData()
    {
        return data;
    }

    public synchronized void setData(double data)
    {
        this.data = data;
    }
}
