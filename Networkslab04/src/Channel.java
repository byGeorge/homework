/**
 * Channel interface.
 *
 * Figure 3.20
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

public interface Channel<E>
{
	/**
	 * Inserts an item into the mailbox.
	 * The contract of this interface does not specify whether
	 * this operation is blocking or not.
	 */
	public void send(E item);

	/**
	 * Removes an item from the mailbox.
	 * The contract of this interface does not specify whether
	 * this operation is blocking or not.
	 */	
	public E receive();
}
