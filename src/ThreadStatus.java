
/*
 * ThreadStatus Object will be used to preserve each thread "status" across reuses from the executor service.
 */

public class ThreadStatus {
    //
    private String threadName;
    private String statusCode;
    private int completedTasksCount=0;
    private int failuresCount=0;

    public String getThreadName() {
        return threadName;
    }
   

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public int getFailuresCount() {
        return failuresCount;
    }

    public void increaseFailuresCountByOne() {
        this.failuresCount++;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getCompletedTasksCount() {
        return completedTasksCount;
    }

    public void increaseCompletedTaskCountByOne() {
        this.completedTasksCount++;
    }

    @Override
    public String toString() {
        return "ThreadStatus{" +
                "threadName='" + threadName + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", completedTasksCount=" + completedTasksCount +
                ", failuresCount=" + failuresCount +
                '}';
    }
}
