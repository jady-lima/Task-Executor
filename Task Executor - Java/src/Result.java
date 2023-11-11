import java.sql.Date;

public class Result {
    private int resultId;
    private double result;
    private double time;

    public Result(int resultId, double result, double time)
    {
        this.resultId = resultId;
        this.result = result;
        this.time = time;
    }

    public int getResultId()
    {
        return resultId;
    }

    public void setResultId(int resultId)
    {
        this.resultId = resultId;
    }

    public double getResult()
    {
        return result;
    }

    public void setResult(double result)
    {
        this.result = result;
    }

    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }
}
