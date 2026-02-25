import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * 
 * @author Michael Mann 
 * @version 02/06/2026
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
   Node front;
   Node rear;

    /** The number of nodes in the list. */
   int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
   public LinkedSet() {
      front = null;
      rear = null;
      size = 0;
   }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
   @Override
    public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
   public int size() {
      return size;
   }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
   public boolean isEmpty() {
      return (size == 0);
   }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
   public boolean add(T element) {
      if (element == null){
         return false;
      }
       // Empty list
      if (front == null) {
         Node n = new Node(element);
         front = n;
         rear = n;
         size++;
         return true;
      }
      
     
      
      Node pointer = front; //alias
       // Scan through the Set - O(N) 
      while (pointer != null && pointer.element.compareTo(element) < 0) {
         pointer = pointer.next;
      }
       
       // End of Set Insertion
      if (pointer == null) {
         Node n = new Node(element);
         n.prev = rear;
         rear.next = n;
         rear = n;
         size++;
         return true;
      }
      
        // Duplicate
      if (pointer.element.compareTo(element) == 0) {
         return false;
      }
       
        
       
      Node n = new Node(element);
       
       // Front insertion
      if (pointer.prev == null) {
         n.next = pointer;
         pointer.prev = n;
      
         front = n;
      } 
       // Middle Insertion
      else {
         n.next = pointer;
         n.prev = pointer.prev;
         pointer.prev.next = n;
         pointer.prev = n;
      }
      
      size++;
      return true;           
   }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
   public boolean remove(T element) {
      if (element == null) {
         return false;
      }
      
      // Empty List
      if (front == null) {
         return false;
      }
      
      // Iterate through set
      Node pointer = front;
      // keep looping until pointer = null AND pointer != element
      while (pointer != null && pointer.element.compareTo(element) != 0) { // O(N)
         pointer = pointer.next;
      }
      
      // if null - isn't found return false
      if (pointer == null) {
         return false;
      }
      ////////////////////
      //// IF FOUND //////
      ///////////////////
      
      // Front and only node //
      if (pointer.prev == null && pointer.next == null) {
         front = null;
         rear = null;
      }
         // Front Node //
      else if (pointer.prev == null) {
         front = pointer.next;
         pointer.next.prev = null;
      }
         // End Node
      else if (pointer.next == null) {
         rear = pointer.prev;
         pointer.prev.next = null; 
      }
      // Must be Middle
      else {
         pointer.prev.next = pointer.next;
         pointer.next.prev = pointer.prev;
      }
      
      size--;
      return true;
   }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
   public boolean contains(T element) {
    // Simple searches for element in collection
    // return true if collection contains element, no modifying
      if (element == null) {
         return false;
      }
    
    // Empty list
      if (front == null) {
         return false;
      }
    
      Node pointer = front;
    
      while (pointer != null && pointer.element.compareTo(element) != 0) { // O(N)
         pointer = pointer.next;
      }
      
      if (pointer == null) {
         return false;
      }
    
      return true;
   }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
   public boolean equals(Set<T> s) {
     // tests for equality between this set and paramter set
     // return true if this set contains excatly the same as parameter set, regardless of order
     // may iterate with 2 for loops
     
     // if not same size, can return early O(1)
      if (this.size != s.size()) {
         return false;
      }
     
      Node pointer = this.front;
     
     // O(N^2)
      while (pointer != null) { // O(N)
         if (!s.contains(pointer.element)) { // O(N)
            return false;
         }
         pointer = pointer.next;
      
      }
     
      return true;
   }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
   public boolean equals(LinkedSet<T> s) {
      
     // if not same size early exit
      if (this.size != s.size) {
         return false;
      }
     
      Node pointerA = this.front;
      Node pointerB = s.front;
     
     // O(N)
      while (pointerA != null && pointerB != null) { // O(N)
         if (pointerA.element.compareTo(pointerB.element) != 0) { // O(1)
            return false;
         }
         pointerA = pointerA.next;
         pointerB = pointerB.next;
      }
     
      return true;
   }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
   public Set<T> union(Set<T> s){
     // return a set
      Set<T> result = new LinkedSet<>();
     
     //add method keeps order for us
     // iterate through and add
     // result add
     // O(N^2) + O(N^2) = O(N^2)
      for (T element: s) { // O(N^2)
         result.add(element);
      }
     
     // this add
      for (T element: this) { // O(N^2)
         result.add(element);
      }
      return result;
   }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
   public Set<T> union(LinkedSet<T> s){
     // return a set
      LinkedSet<T> result = new LinkedSet<>();
      
      Node p1 = this.front;
      Node p2 = s.front;
      
     
      while (p1 != null && p2 != null) { // O(N)
         int cmp = p1.element.compareTo(p2.element);
        
         if (cmp == 0) {
            result.quickAdd(p1.element); // O(1)
            p1 = p1.next;
            p2 = p2.next;
         }
         else if (cmp < 0) {
            result.quickAdd(p1.element);
            p1 = p1.next;
         }
         else {
            result.quickAdd(p2.element);
            p2 = p2.next;
         }
      }
      // If Left over p1
      while (p1 != null) { // O(N)
         result.quickAdd(p1.element);
         p1 = p1.next;
      }
      
      // if left over p2
      while (p2 != null) { // O(N)
         result.quickAdd(p2.element);
         p2 = p2.next;
      }
      return result;
   }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
   public Set<T> intersection(Set<T> s) {
      // only returns commonality between 2 sets
      // returns a set
      Set<T> result = new LinkedSet<>();
      //O(N^2)
      for (T element: this) { //O(N)
         if (s.contains(element)) { // O(N)
            result.add(element);
         }
      }
      return result;
   }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
   public Set<T> intersection(LinkedSet<T> s) {
      // return a set
      LinkedSet<T> result = new LinkedSet<>();
      
      Node p1 = this.front;
      Node p2 = s.front;
      
      // O(N)
      while (p1 != null && p2 != null) { // O(N)
         int cmp = p1.element.compareTo(p2.element);
         
         if (cmp == 0) {
            result.quickAdd(p1.element); // O(1)
            p1 = p1.next;
            p2 = p2.next;
         }
         else if (cmp < 0) {
            p1 = p1.next;
         }
         else {
            p2 = p2.next;
         }
      }
      
      
      return result;
   }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
   public Set<T> complement(Set<T> s) {
   // in THIS but not S
      Set<T> result = new LinkedSet<>();
   
      // O(N^2)
      for (T element: this) { //O(N)
         if (!s.contains(element)) {
            result.add(element); // O(N)
         }
      }
      return result;
   }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
   public Set<T> complement(LinkedSet<T> s) {
   // IN THIS but not S
   // return a Set
      LinkedSet<T> result = new LinkedSet<>();
   
      Node p1 = this.front;
      Node p2 = s.front;
      
      // O(N)
      while (p1 != null) { // O(N)
      // if p2 is already null we can add everything from p1
         if (p2 == null) {
            result.quickAdd(p1.element); // O(1)
            p1 = p1.next;
            continue;
         }
      
      // now compare
         int cmp = p1.element.compareTo(p2.element);
      
         if (cmp < 0) { // if p1 is less than p2 we can add safely because both are sorted
            result.quickAdd(p1.element);
            p1 = p1.next;
         }
         else if (cmp > 0 ) { //however if p1 is greater than p2, move p2
            p2 = p2.next;
         }
         else { // if equal move both, don't add
            p1 = p1.next;
            p2 = p2.next;
         }
      }
      return result;
   }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
   public Iterator<T> iterator() {
      return new LinkedSetIterator();
   }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
   public Iterator<T> descendingIterator() {
      return new DescendingLinkedSetIterator();
   }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
   public Iterator<Set<T>> powerSetIterator() {
      return new LinkedSetPowerSetIterator();
   }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.
    /**
     * Iterates through the set in ascending order (front → rear).
     */
   private class LinkedSetIterator implements Iterator<T> {
      private Node pointer;
      
      public LinkedSetIterator() {
         pointer = front;
      }
      
      public boolean hasNext() {
         return pointer != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T element = pointer.element;
         pointer = pointer.next;
         return element;
      }
   }
   
   /**
     * Iterates through the set in descending order (rear → front).
     */
   private class DescendingLinkedSetIterator implements Iterator<T> {
      private Node pointer;
      
      public DescendingLinkedSetIterator() {
         pointer = rear;
      }
      
      public boolean hasNext() {
         return pointer != null;
      }
      
      public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
         T element = pointer.element;
         pointer = pointer.prev;
         return element;
      }
   }
   
   /**
     * Iterates over all subsets of this set using a binary mask.
     * Generates 2^N subsets with O(N) work per subset.
     */
   private class LinkedSetPowerSetIterator implements Iterator<Set<T>> {
     // number of elements in the set
      int N;
      
      // number of subsets 2^N
      int M;
      
      // binary counter, "which subset we are currently generating"
      int current;
      
      public LinkedSetPowerSetIterator() {
         N = size;
         M = (int)Math.pow(2, N);
         current = 0;
      }
      
      public boolean hasNext() {
         return current < M;
      }
      
      public Set<T> next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         
        // Create the subset to return
         LinkedSet<T> s = new LinkedSet<T>();
         
         // converting "current" to binary
         // if current = 5 = 101 (binary)
         // will append to char[] as ['1', '0', '1'] single ' ' is binary, double " " is string
         // returns the shortest representation, which is why it was recommeneded to go right -> left (no leading zeros)
         
         char[] bitstring = Integer.toBinaryString(current).toCharArray();
         
         // start at rear
         Node n = rear;
         
         // start at right most bit
         int i = bitstring.length - 1;
         
         // we are working from rear to front to match binary mapping
         while (n != null) {
            if (i >= 0 && bitstring[i] == '1') {
               s.add(n.element);
               // example      { A,   B,   C }
              // current = 5 = ['1', '0', '1']
              //             add A   skip   add C
            }
            
            n = n.prev;
            i--;
         }
         
         current = current + 1;
         return s;
      }
   }
   
   /**
    * Appends element to the rear in O(1) time.
    */
   private void quickAdd(T element) {
      Node n = new Node(element);
      
      // If Empty add front
      if (front == null) {
         front = n;
         rear = n;
         size++;
      }
      else { // add to the end
         rear.next = n;
         n.prev = rear;
         rear = n;
         size++;
      }
            
   }
   
  

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
   class Node {
        /** the value stored in this node. */
      T element;
        /** a reference to the node after this node. */
      Node next;
        /** a reference to the node before this node. */
      Node prev;
   
        /**
         * Instantiate an empty node.
         */
      public Node() {
         element = null;
         next = null;
         prev = null;
      }
   
        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
      public Node(T e) {
         element = e;
         next = null;
         prev = null;
      }
   }

}
