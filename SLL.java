import java.util.NoSuchElementException;

/**
 * Class to implement a singly linked list
 * Singly linked list is a collection of nodes, with each
 * node pointing to the next node and holding a piece of data.
 *
 * @author Abigail Lei
 * @version Spring 2026
 */
public class SLL<T> implements ListADT<T>, NodeBasedOps<T>{

    // Attributes
    private NodeSL<T> head;
    private int size;

    /**
     * Constructor that creates an empty linked list
     * Sets size to 0 and head to null
     */
    SLL() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Constructor that takes in a head.
     * Sets size to one.
     * @param head the Node that is the linked list's head
     */
    SLL(NodeSL<T> head) {
        this.head = head;
        this.size = 1;
    }

    /**
     * Copy constructor for the singly linked list
     * @param original the original linked list that will be copied
     */
    SLL(SLL<T> original) {
        if (original == null || original.size() == 0) {
            this.head = null;
            this.size = 0;
            return;
        } else {
            NodeSL<T> originalNode = original.getHead();

            this.head = new NodeSL<T>(originalNode.getData(), null);
            NodeSL<T> current = this.head;
            originalNode = originalNode.getNext();


            while (originalNode != null) {
                NodeSL<T> next = new NodeSL<T>(originalNode.getData(), null);
                current.setNext(next);
                current= next;
                originalNode = originalNode.getNext();
            }

            this.size = original.size();
        }
    }

    /**
     * An accessor that returns the number of elements in the list
     * @return integer of how many elements are in the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks the linked list and returns whether it is empty or not
     * True means the list empty and has size of 0
     * False means having at least one node
     * @return a boolean of if the list is empty or not
     */
    public boolean isEmpty() {
        if (this.size == 0){
            return true;
        }
        return false;
    }

    /**
     * Accesses an element at a specific index, then returns it
     * @param index of element to access
     * @return T the data at given node
     * @throws IndexOutOfBoundsException if index is invalid, less than 0 or is greater than the size
     */
    public T get(int index) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Invalid index");
        } 
       return getNode(index).getData();
    }

    /**
     * Helper method that returns a node at a specific index
     * @param index of element to access
     * @return node at given index
     * @throws IndexOutOfBoundsException for invalid indicies
     */
    public NodeSL<T> getNode(int index) {
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException("Invalid index");
        } 

        NodeSL<T> current = this.getHead();

        for (int i = 0; i < index; i++){
            current = current.getNext();
        }
        
        return current;
    }

    /**
     * Replaces the node of a specific index with a new element, T item
     * @param index an integer for the index of the position to change
     * @param value the new node with data of T type to replace the previous node with.
     * @throws IndexOutOfBoundsException if index is invalid, less than 0 or is greater than size
     * @throws IllegalStateException if list is empty
     * @return the previous node data that got replaced
     */
    public T set(int index, T value) {
        T returned;

        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        } else if (size < 0) {
            throw new IllegalStateException("Operation invalid in current state");
        } else {
             NodeSL<T> node = getNode(index);
            returned = node.getData();
            node.setData(value);
            if (this.size == 0){
                this.head = node; //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            }
            return returned;
        } 
    }

    /**
     * Adds a new element at a specific index
     * pushes every element after it back an index
     * @param index an integer which is the index of where the new element is added
     * @param value is the element being added, can be many different types
     * @throws IndexOutOfBoundsException if index is invalid as in less than 0 or is greater than size
    */
    public void add(int index, T value) {
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException("Invalid index");
        } 

        if (index == 0){
            addFirst(value);
        } else {
            NodeSL<T> before = getNode(index - 1);
            NodeSL<T> current = new NodeSL<T>(value, before.getNext());
            before.setNext(current);
            this.size += 1;
        }
    }

    /**
     * Removes the item at the specific index
     * Pushes forward each element behind the element that was removed
     * @param index the index of the item to be removed
     * @return the item that was removed from the list
     * @throws IndexOutOfBoundsException if index is invalid: less than 0 or is greater than size
     * @throws IllegalStateException if list is empty
     */
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (this.size == 0) {
            throw new IllegalStateException("Operation invalid in current state");
        }
        if (index == 0) {
            return removeFirst();
        }

        NodeSL<T> returned = getNode(index);
        if (index == size - 1) {
            NodeSL<T> node = getNode(index - 1);
            node.setNext(null);
        } else {
            NodeSL<T> before = getNode(index - 1);
            NodeSL<T> after = getNode(index + 1);
            before.setNext(after);
        }
        this.size -= 1;
        return returned.getData();
    }
    

    /**
     * toString printing method that formats the dynamic array nicely
     * @return String of dyanmic array in format with brackets and commas
     */
    public String toString() {
        String returned = "[";
        if (this.size == 0){
            return "[]";
        }
        
        for (int i = 0; i < this.size; i++){
            returned += this.getNode(i).getData();
            returned = returned + ", ";
        }

        return returned.substring(0, returned.length()-2) + "]";
    }

    /** 
     *  Accessor for head node
     *  @return the head node
     */
    public NodeSL<T> getHead() {
        return this.head;
    }
  
    /** 
     *  Accessor for tail node
     *  @return the tail node
     */
    public NodeSL<T> getTail() {
        if (size == 0){
            return null;
        } else {
            return getNode(size-1);
        }
    }

    /** 
     *  Inserts the given item at the head of the list
     *  @param v item to insert 
     */
    public void addFirst(T v) {
        NodeSL<T> current = new NodeSL<T>(v, this.head);
        this.head = current;
        this.size +=1;
    }

    /** 
     *  Inserts the given item at the tail of the list
     *  @param v item to insert 
     */
    public void addLast(T v) {

        if (size == 0){
            NodeSL<T> current = new NodeSL<T>(v, null);
            this.head = current;
            this.size += 1;
        } else {
            NodeSL<T> current = getNode(size - 1);
            NodeSL<T> next = new NodeSL<T>(v, null);
            current.setNext(next);
            this.size+=1;
        }
    }

    /** 
     *  Removes the given item from the head of the list
     *  @return v item removed
     *  @throws IllegalStateException if used on empty list
     */
    public T removeFirst() {
        if (size == 0){
            throw new IllegalStateException("Cannot remove on empty list");
        } else {
            T removed = this.head.getData();
            if (size == 1){
                this.head = null;
                this.size = 0;
            } else {
                this.head = getNode(1);
                this.size -= 1;
            }

            return removed;
        }
    }

    /** 
     *  Removes the given item from the tail of the list
     *  @return value T item removed
     *  @throws IllegalStateException if used on empty list
     */
    public T removeLast(){

        if (size == 0){
            throw new IllegalStateException("Cannot remove on empty list");
        } 
        T removed = getNode(size-1).getData();

        if (size == 1){
            this.head = null;
            this.size = 0;
        } else {
            getNode(size-2).setNext(null);
            this.size -= 1;
        }
        return removed;
    }

    /** 
     *  Inserts the given item after the specified node.
     *  If here is null, insert at the head.
     *  @param here node to insert after
     *  @param v item to insert 
     */
    public void addAfter(NodeSL<T> here, T v){
        if (here == null) {
            addFirst(v);
        } else {
            NodeSL<T> node = new NodeSL<T>(v, here.getNext());
            here.setNext(node);
            this.size += 1;
        }
    }

    /** 
     *  Removes the node after the given position.
     *  If here is null, remove the head node.
     *  @param here marks position to remove after
     *  @return item removed
     */
    public T removeAfter(NodeSL<T> here){
        if (size == 0){
            throw new IllegalStateException("Cannot remove on empty list");
        } else if (here == null) {
            T removed = this.head.getData();
            this.head = this.head.getNext();
            this.size -= 1;
            return removed;
        }

        NodeSL<T> target = here.getNext();
        if (target == null) {
            throw new IllegalStateException("No node exists after the given node");
        }

        here.setNext(target.getNext());
        this.size -= 1;
        return target.getData();
    }

    /**
     * Creates a fresh iterator
     * @return new iterator of type T for single linked lists
     */
    public Iterator<T> iterator() {
        return new SLLIterator();
    }

    /**
     * Private class for iterator for singly linked list
     * Implements general iterator functionality
     */
    private class SLLIterator implements Iterator<T> {

        // Attributes
        private NodeSL<T> current;

        /**
         * Constructor for singly linked list iterator
         * Starts the iterator at the head
         */
        SLLIterator() {
            this.current = SLL.this.head;
        }

        /**
         * Method that returns if there is a node after
         * @return boolean of if there is a next node
         */
        public boolean hasNext() {
            return current.getNext() != null;
        }

        /**
         * Method that iterates to next node and returns passed node data 
         * @return data of type T stored in passed node
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element");
            }
            T value = current.getData();
            current = current.getNext();
            return value;
        }
    }

    /**
     * Splits SLL from given node to end
     * Copy style so does not impact original SLL
     * @param index the node that SLL is split at
     * @return new split SLL 
     */
    public SLL<T> splitCopy(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        SLL<T> tail = new SLL<>();
        NodeSL<T> node = this.head;

        for (int i = 0; i < index && node != null; i++) {
            node = node.getNext();
        }
        while (node != null) {
            tail.addLast(node.getData());
            node = node.getNext();
        }
        return tail;
    }

    /**
     *  Splits SLL from given node to end
     * Transfer style so impacts original SLL,
     * which is shortened from node 0 to index
     * @param index the node where SLL is split at
     * @return new split SLL from index node to end
     */
    public SLL<T> splitTransfer(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        SLL<T> tail = new SLL<T>();
        if (index == this.size) {
            return tail;
        }
        if (index == 0) {
            tail.head = this.head;
            tail.size = this.size;
            this.head = null;
            this.size = 0;
            return tail;
        }
        NodeSL<T> previous = getNode(index - 1);
        tail.head = previous.getNext();
        tail.size = this.size - index;
        previous.setNext(null);
        this.size = index;
        return tail;
    }
}
