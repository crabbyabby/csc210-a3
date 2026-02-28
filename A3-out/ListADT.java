/**
 * Interface for the list abstract data type
 */
public interface ListADT<T>{
        /**
     * An accessor that returns the number of elements in the list
     * @return integer of how many elements are in the list
     * @throws NullPointerException if called on a list that has not been created yet.
     */
    public int size();

    /**
     * Checks the list and returns whether it is empty or not
     * True means the list empty and has size of 0
     * False means having at least one element
     * @return a boolean of if the list is empty or not
     * @throws NullPointerException if called on a list that has not been created yet.
     */
    public boolean isEmpty();

    /**
     * Accesses an element at a specific index, then returns it
     * @param index of element to access
     * @return T - the element at the index
     * @throws IndexOutOfBoundsException if index is invalid, less than 0 or is greater than the size
     */
    public T get(int index);

    /**
     * Replaces the element of a specific index with a new element, T item
     * @param index an integer for the index of the position to change
     * @param value the new element of T type to replace the previous element with.
     * @throws IndexOutOfBoundsException if index is invalid, less than 0 or is greater than size
     * @throws IndexOutOfBoundsException if list is empty
     * @return the previous element that got replaced
     */
    public T set(int index, T value);

    /**
     * Adds a new element at a specific index
     * pushes every element after it back an index
     * @param index an integer which is the index of where the new element is added
     * @param value is the element being added, can be many different types
     * @throws IndexOutOfBoundsException if index is invalid as in less than 0 or is greater than size
    */
    public void add(int index, T value);

    /**
     * Removes the item at the specific index
     * Pushes forward each element behind the element that was removed
     * @param index the index of the item to be removed
     * @return the item that was removed from the list
     * @throws IndexOutOfBoundsException if index is invalid: less than 0 or is greater than size
     * @throws IndexOutOfBoundsException if list is empty
     */
    public T remove(int index);
    
    /**
     * toString printing method that formats the dynamic array nicely
     * @return String of dyanmic array in format with brackets and commas
     */
    public String toString();

}
