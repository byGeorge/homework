/**
 * FCFS scheduling algorithm.
 */

import java.util.List;

public class RR implements Algorithm
{
    private List<Task> queue;
    private Task currentTask;

    public RR(List<Task> queue) {
        this.queue = queue;
    }

    public void schedule() {
        System.out.println("FCFS Scheduling \n");

        while (!queue.isEmpty()) {
            currentTask = pickNextTask();
            
            CPU.run(currentTask, currentTask.getBurst());

            // we can get the current CPU time
            CPU.getTime();
        }
    }

    public Task pickNextTask() {
        return null;
    }

    public double getAverageWaitTime() {
        return 0;
    }
    
    public double getAverageResponseTime() {
        return 0;
    }

    public double getAverageTurnaroundTime() {
        return 0;
    }
}
