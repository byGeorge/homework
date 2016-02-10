/**
 * Summation program using exectuors/callable/futures
 */
 
import java.util.concurrent.*;

class Summation implements Callable<Integer>
{
    private int upper;

    public Summation(int upper) {
        this.upper = upper;
    }

    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= upper; i++)
            sum += i;
    
        return new Integer(sum);
    }
}


public class Attempt1
{
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Attempt1 <integer>");
            System.exit(0);
        }
        else {
            int upper = Integer.parseInt(args[0]);

            ExecutorService pool = Executors.newSingleThreadExecutor();
            Future<Integer> result = pool.submit(new Summation(upper));

            try {
                System.out.println("sum = " + result.get());
            }
            catch (InterruptedException | ExecutionException ie) { }

            // Notice the difference if shutdown() is invoked.

            //pool.shutdown();
        }
    }
}
