package cp213;

/**
 * A single linked queue structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the queue is visible through the standard
 * queue methods. Extends the <code>SingleLink</code> class.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the SingleQueue data type.
 */
public class SingleQueue<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleQueues into the current
     * SingleQueue. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods insert or remove. left and right SingleQueues are
     * empty when done. Nodes are moved alternately from left and right to this
     * SingleQueue.
     *
     * You have two source queues named left and right. Move all nodes from these
     * two queues to the current queue. It does not make a difference if the current
     * queue is empty or not, just get nodes from the right and left queues and add
     * them to the current queue. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left queues are of the same length.
     *
     * @param left  The first SingleQueue to extract nodes from.
     * @param right The second SingleQueue to extract nodes from.
     */
    public void combine(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
	// Ensure the current queue is empty
        assert this.front == null : "Error: current queue is not empty";

        // Combine nodes from left and right queues alternately
        while (!left.isEmpty() || !right.isEmpty()) {
            if (!left.isEmpty()) {
                this.moveFrontToRear(left);
            }
            if (!right.isEmpty()) {
                this.moveFrontToRear(right);
            }
        }
    }

    /**
     * Adds object to the rear of the queue. Increments the queue length.
     *
     * @param entity The object to added to the rear of the queue.
     */
    public void insert(final T entity) {

	// your code here
	// Create a new node with the entity
        SingleNode<T> newNode = new SingleNode<>(entity, null);

        // Insert the new node at the rear of the queue
        if (this.rear == null) {
            this.front = newNode;
        } else {
            this.rear.setNext(newNode);
        }
        this.rear = newNode;

        // Increment the length of the queue
        this.length++;
    }

    /**
     * Returns the front object of the queue and removes that object from the queue.
     * The next node in the queue becomes the new first node. Decrements the queue
     * length.
     *
     * @return The object at the front of the queue.
     */
    public T remove() {

	// your code here
	// Check if the queue is empty
        if (this.front == null) {
            return null;
        }

        // Remove the front node and return its value
        T value = this.front.getEntity();
        this.front = this.front.getNext();
        this.length--;

        // Update the rear if the queue is now empty
        if (this.front == null) {
            this.rear = null;
        }

        return value;
    }

    /**
     * Splits the contents of the current SingleQueue into the left and right
     * SingleQueues. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleQueue is empty when done. Nodes are
     * moved alternately from this SingleQueue to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleQueue to move nodes to.
     * @param right The second SingleQueue to move nodes to.
     */
    public void splitAlternate(final SingleQueue<T> left, final SingleQueue<T> right) {

	// your code here
	// Split nodes alternately into left and right queues
        boolean moveToLeft = true;

        while (this.front != null) {
            if (moveToLeft) {
                left.moveFrontToRear(this);
            } else {
                right.moveFrontToRear(this);
            }
            moveToLeft = !moveToLeft;
        }
    }
}
