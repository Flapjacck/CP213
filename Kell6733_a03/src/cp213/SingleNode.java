package cp213;

/**
 * DO NOT CHANGE THE CONTENTS OF THIS CLASS.
 *
 * The individual node of a linked structure that stores <code>T</code> objects.
 * This is a singly linked node. The node link can be updated, but not the node
 * entity, in order to avoid copying or moving values between nodes. Data
 * structures must be updated by moving nodes, not by copying or moving entity.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> data type for structure.
 */
public final class SingleNode<T> {

    /**
     * The generic entity stored in the node.
     */
    private T entity = null;
    /**
     * Link to the next Node.
     */
    private SingleNode<T> next = null;

    /**
     * Creates a new node with entity and link to next node. Not copy safe as it
     * accepts a reference to the entity rather than a copy of the entity.
     *
     * @param entity the entity to store in the node.
     * @param next   the next node to link to.
     */
    public SingleNode(final T entity, final SingleNode<T> next) {
	this.entity = entity;
	this.next = next;
    }

    /**
     * Returns the node entity. Not copy safe as it returns a reference to the
     * entity, not a copy of the entity.
     *
     * @return The entity portion of the node.
     */
    public T getEntity() {
	return this.entity;
    }

    /**
     * Returns the next node in the linked structure.
     *
     * @return The node that follows this node.
     */
    public SingleNode<T> getNext() {
	return this.next;
    }

    /**
     * Links this node to the next node.
     *
     * @param next The new node to link to.
     */
    public void setNext(final SingleNode<T> next) {
	this.next = next;
    }
}
