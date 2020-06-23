public class ThreadStatus {
    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    private String threadName;
    private String statusCode;
    private int completedTasksCount=0;
    private int failuresCount=0;

    public int getFailuresCount() {
        return failuresCount;
    }

    public void increaseFailuresCountByOne() {
        this.failuresCount++;
    }
//    public ThreadStatus(String statusCode, int completedTasksCount) {
//        this.statusCode = statusCode;
//        this.completedTasksCount = completedTasksCount;
//    }

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
