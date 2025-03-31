package org.example;

/**
 * A singly linked list used to handle collisions in the hash table (chaining).
 * Supports adding, finding, cloning, and size tracking of Player nodes.
 */
public class List {

    private int size;
   private Node head;

   /**
    * Default List Constructor**/
    public List() {
        //empty list
        head = null;
        this.size = 0;
    }

    /**
     * Copy Constructor - Deep copy**/
    List(List other) {
        //checks if the incoming list is either empty or null.
        if (other == null || other.head == null) {
            this.size = 0;
            this.head = null;
            return;
        }

        this.size = other.size; // Copy the size from the original list.

        // Deep copy the head node of the original list using Player's createClone()
        this.head = new Node(other.head.getData().createClone(), null);

        Node currentNew = this.head; //the new list we are building
        Node currentOtherHead = other.head.getNext(); // walks through the original list starting from second node

        // Traverse through the original list, starting from the second node since we already copied the head.
        while (currentOtherHead != null) {
            // Clone the player at the current original node and create a new node in the new list
            currentNew.setNext(new Node(currentOtherHead.getData().createClone(), null));
            currentNew = currentNew.getNext(); //
            currentOtherHead = currentOtherHead.getNext();
        }

        //set the last node to null to reach the end of the list.
        currentNew.setNext(null);
    }


    /** Clone method that creates a deep copy of the current instance. **/
    List createClone() {
        return new List(List.this);
    }


    /**
     * This method adds a player to the START of the list.**/
    void add(Player newPlayer) {
        Node newNode = new Node(new Player(newPlayer), head); // create a new node with a new player, and pointing to head.
        head = newNode; //the new player is now inserted at head, meaning its at the new beginning of the list.
        size++; //increment size
    }

    /**
     * This method searches the list for the player with the given name. It should return the Player instance if it finds it, and null otherwise.   **/
    Player find(String name) {
       Node currentNode = head; //start at the head of the list.

      //if the list is empty, return nothing
       if(head == null) {
           return null;
       }

        while(currentNode != null) {
            //if the currentNode's data name is the same as the parameter then return the data of that node.
            if(currentNode.getData().getName().equals(name)) {
                return currentNode.getData();
            }
            else {
                currentNode = currentNode.getNext(); //else move to the next node.
            }
        }
        return null;
    } //end of find method


    /** Getter method of size. **/
    public int getSize() {
        return size;
    }

    /** Getter method of head**/
    public Node getHead() {
        return head;
    }
}
