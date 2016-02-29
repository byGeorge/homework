package Networkslab05.part1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of the Manager interface that
 * permits a number of licenses.
 */

public class LicenseManager implements Manager
{
    // the number of available permits
    private int permits;
    // this is the lock
    private ReentrantLock lck = new ReentrantLock();
  
    public LicenseManager(int permits) {
        if (permits < 0)
            throw new IllegalArgumentException();
        // critical section
        while(!lck.isHeldByCurrentThread()) {
            lck.tryLock();
        }
        this.permits = permits;
        // end critical section
        lck.unlock();
    }
  
    public boolean acquirePermit() {
        boolean rv = false;
    
        if (permits > 0) {
            permits++;
      
            rv = true;
        }
    
    return rv;
    }
  
    public void releasePermit() {
        permits--;
    }
}
  
