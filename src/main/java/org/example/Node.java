package org.example;

/**
 * Represents a node in a linked list used by the hash table.
 * Stores a Player and a reference to the next node.
 */
public class Node {

   private Player data;
   private Node next;

   //Note to self: You need getter methods for data and next to return those values in the list class since they are private.

    /**
     * Constructs a new node with given Player data and link to the next node.
     * @param data The Player object to store.
     * @param next The next Node in the list.
     */
    public Node(Player data, Node next) {
        this.data = data;
        this.next = next;
    }

    /** @return The Player stored in this node. */
    public Player getData() {
        return data;
    }

    /** @return The next node in the list. */
    public Node getNext() {
        return next;
    }

    /** @param next Sets the next node link. */
    public void setNext(Node next) {
        this.next = next;
    }


}
