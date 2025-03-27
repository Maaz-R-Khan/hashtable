package org.example;

public class List {

    private int size;
   private int[] bucket;
   private Node head;

   /**
    * Default List Constructor**/
    public List() {
        head = null;
        this.size = 0;
    }

    /**
     * Copy Constructor - Deep copy**/
     List(List other) {


         // if the incoming list is empty, other is empty, initialize size to 0, and point head to null.

       if(other == null) {
           this.size = 0;
           head = null;
           return;
       }


       this.size = other.size;
       this.head = new Node(new Player(other.head.getData()), null); //create a deep copy of the newNode.

        Node currentNew = this.head;
        Node currentOtherHead = other.head.getNext();

        while(currentOtherHead != null) {
            currentNew.setNext(new Node(new Player(other.head.getData()), null));
            currentNew = currentNew.getNext();
            currentOtherHead = currentOtherHead.getNext();

        }

        currentNew.setNext(null);
    }


    /** **/
    List createClone() {
        return new List(this);
    }


    void add(Player) {


    }

    Player find(String name) {
    }
}
