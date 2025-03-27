package org.example;

public class Node {
   private Player data;
   private Node next;

   //Note to self: You need getter methods for data and next to return those values in the list class since they are private.

    public Node(Player data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Player getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }


}
