package Networkslab05.part1;

/**
 * Implementation of the Manager interface that
 * permits a number of licenses.
 */

public class LicenseManager implements Manager
{
    // the number of available permits
    private int permits;
  
    public LicenseManager(int permits) {
        if (permits < 0)
            throw new IllegalArgumentException();

        this.permits = permits;
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
  
