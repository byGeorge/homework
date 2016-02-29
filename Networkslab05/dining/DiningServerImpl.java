package Networkslab05.dining;

/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{  
	// different philosopher states
	enum State {THINKING, HUNGRY, EATING};
	
	// number of philosophers
	public static final int NUMBER_OF_PHILOSOPHERS = 5;
	
	// array to record each philosopher's state
	private State[] state;

    // to lock states
    private static Lock chopstick[];

	public DiningServerImpl()
	{
		// array of philosopher's state
		state = new State[NUMBER_OF_PHILOSOPHERS];
        chopstick = new ReentrantLock[NUMBER_OF_PHILOSOPHERS];
		
		// originally all philosopher's are thinking
		for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            state[i] = State.THINKING;
            chopstick[i] = new ReentrantLock();
        }
	}
	
	// called by a philosopher when they wish to eat 
	public void takeForks(int pnum)  {
        state[pnum] = State.HUNGRY;
        test(pnum);
        state[pnum] = State.EATING;
	}
	
	// called by a philosopher when they are finished eating 
	public void returnForks(int pnum) {
        // puts utensils down and continues to ruminate upon the meaning of life
        chopstick[leftNeighbor(pnum)].unlock();
        chopstick[pnum].unlock();
		state[pnum] = State.THINKING;
	}
	
	private void test(int i) {
        //left and right neighbours
        int l = leftNeighbor(i);
        int r = rightNeighbor(i);
        // in other words ... if I'm hungry and my left and
        // right neighbors aren't eating, then let me eat!
        // loops until neighbours forks are available
        while (state[i] == State.HUNGRY) {
               // try to pick up the chopsticks
               if (chopstick[l].tryLock() && chopstick[i].tryLock()) {
                   //two chopsticks acquired!
                   state[i] = State.EATING;
               }
               else {
                   // put any acquired chopsticks down and continue to wait
                   try {
                       chopstick[l].unlock();
                       chopstick[i].unlock();
                   }catch (Exception e) {}
               }
        }
    }
	
	// return the index of the left neighbor of philosopher i
	private int leftNeighbor(int i)
	{
        return ((i + 1) % NUMBER_OF_PHILOSOPHERS);
    }
	
	// return the index of the right neighbor of philosopher i
	private int rightNeighbor(int i)
	{
        return ((i + 4) % NUMBER_OF_PHILOSOPHERS);
	}
}
