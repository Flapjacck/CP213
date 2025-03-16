package cp213;

/**
 * Stores a entity and an occurrence count for that entity. The entity must be
 * Comparable so that it can be compared and sorted against other datas of the
 * same type.
 *
 * @author David Brown
 * @version 2025-01-05
 * @param <T> the data structure data type
 */
public class CountedEntity<T extends Comparable<T>> implements Comparable<CountedEntity<T>> {

    // Attributes.
    private int count = 0; // entity count
    private T entity = null; // entity

    /**
     * Copy constructor.
     *
     * @param source Another CountedEntity object.
     */
    public CountedEntity(final CountedEntity<T> source) {
	this.entity = source.entity;
	this.count = source.count;
    }

    /**
     * Constructor for key version of object. (entity count defaults to 0)
     *
     * @param entity The object to be counted.
     */
    public CountedEntity(final T entity) {
	this.entity = entity;
    }

    /**
     * Constructor.
     *
     * @param entity The object to be counted.
     * @param count  The object count.
     */
    public CountedEntity(final T entity, final int count) {
	this.entity = entity;
	this.count = count;
    }

    /**
     * Comparison method for objects. Compares only the stored objects, counts are
     * ignored. Returns:
     * <ul>
     * <li>0 if this equals target</li>
     * <li>&lt; 0 if this precedes target</li>
     * <li>&gt; 0 if this follows target</li>
     * </ul>
     *
     * @param target CountedEntity object to compare against.
     * @return Comparison result.
     */
    @Override
    public int compareTo(CountedEntity<T> target) {
	return this.entity.compareTo(target.entity);
    }

    /**
     * Decrements the entity count.
     */
    public void decrementCount() {
	this.count--;
    }

    /**
     * Returns this entity count.
     *
     * @return this entity count.
     */
    public int getCount() {
	return this.count;
    }

    /**
     * Returns this entity.
     *
     * @return this entity.
     */
    public T getEntity() {
	return this.entity;
    }

    /**
     * Returns a copy of the current object.
     *
     * @return the current object
     */
    public CountedEntity<T> copy() {
	return new CountedEntity<T>(this.entity, this.count);
    }

    /**
     * Increments the entity count.
     */
    public void incrementCount() {
	this.count++;
    }

    /**
     * Sets the entity count.
     *
     * @param count the new entity count.
     */
    public void setCount(final int count) {
	this.count = count;
	return;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return String.format("{%s: %d}", this.entity.toString(), this.count);
    }

}
