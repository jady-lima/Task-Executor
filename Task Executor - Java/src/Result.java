import java.sql.Date;

public class Result {
    private int resultId;
    private double result;
    private Date time;

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

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }
}
