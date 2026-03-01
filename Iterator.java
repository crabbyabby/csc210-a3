/**
 * An interface for the iterator class
 */
public interface Iterator<T> {

    /**
     * 
     * @return boolean of if there is an element in the next position
     */
    public boolean hasNext();

    /**
     * Moves iterator forward once
     * @return element of type T that iterator passes over
     */
    public T next();

}
