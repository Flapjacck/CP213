package cp213;

/**
 * A single linked stack structure of <code>Node T</code> objects. Only the
 * <code>T</code> object contained in the stack is visible through the standard
 * stack methods. Extends the <code>SingleLink</code> class. Note that the rear
 * attribute should be ignored as the rear is not used in a stack.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the SingleStack data type.
 */
public class SingleStack<T> extends SingleLink<T> {

    /**
     * Combines the contents of the left and right SingleStacks into the current
     * SingleStack. Moves nodes only - does not refer to objects in any way, or call
     * the high-level methods pop or push. left and right SingleStacks are empty
     * when done. Nodes are moved alternately from left and right to this
     * SingleStack.
     *
     * You have two source stacks named left and right. Move all nodes from these
     * two stacks to the current stack. It does not make a difference if the current
     * stack is empty or not, just get nodes from the right and left stacks and add
     * them to the current stack. You may use any appropriate SingleLink helper
     * methods available.
     *
     * Do not assume that both right and left stacks are of the same length.
     *
     * @param left  The first SingleStack to extract nodes from.
     * @param right The second SingleStack to extract nodes from.
     */
    public void combine(final SingleStack<T> left, final SingleStack<T> right) {

	// your code here
	// let's make sure this stack is empty first
        assert this.front == null : "May combine into an empty Stack only";

        // Now, let's move nodes from left and right alternately
        while (!left.isEmpty() || !right.isEmpty()) {
            if (!left.isEmpty()) {
                this.moveFrontToFront(left);
            }
            if (!right.isEmpty()) {
                this.moveFrontToFront(right);
            }
        }
    }

    /**
     * Returns the top object of the stack and removes that object from the stack.
     * The next node in the stack becomes the new top node. Decrements the stack
     * length.
     *
     * @return The object at the top of the stack.
     */
    public T pop() {

	// your code here
	// If the stack is empty, there's nothing to pop
        if (this.front == null) {
            return null;
        }

        // Get the value of the top node
        T value = this.front.getEntity();
        // Move the front to the next node
        this.front = this.front.getNext();
        // Decrease the length of the stack
        this.length--;

        return value;
    }

    /**
     * Adds data to the top of the stack. Increments the stack length.
     *
     * @param entity The object to add to the top of the stack.
     */
    public void push(final T entity) {

	// your code here
	// Create a new node with the entity
        SingleNode<T> newNode = new SingleNode<>(entity, null);
        // Set the new node's next to the current front
        newNode.setNext(this.front);
        // Update the front to the new node
        this.front = newNode;
        // Increase the length of the stack
        this.length++;

	return;
    }

    /**
     * Splits the contents of the current SingleStack into the left and right
     * SingleStacks. Moves nodes only - does not move object or call the high-level
     * methods insert or remove. this SingleStack is empty when done. Nodes are
     * moved alternately from this SingleStack to left and right. left and right may
     * already contain objects.
     *
     * This is the opposite of the combine method.
     *
     * @param left  The first SingleStack to move nodes to.
     * @param right The second SingleStack to move nodes to.
     */
    public void splitAlternate(final SingleStack<T> left, final SingleStack<T> right) {

	// your code here
	// let's alternate nodes between left and right stacks
        boolean moveToLeft = true;

        while (this.front != null) {
            if (moveToLeft) {
                left.moveFrontToFront(this);
            } else {
                right.moveFrontToFront(this);
            }
            moveToLeft = !moveToLeft;
        }
    }
}