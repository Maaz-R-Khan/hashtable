package org.example;

public class List {

    private int size;
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


       this.size = other.size; //copy the size of the original list, to other list.
       this.head = new Node(new Player(other.head.getData()), null); //create a deep copy of the newNode storing a Player object.

        Node currentNew = this.head;
        Node currentOtherHead = other.head.getNext();

        while(currentOtherHead != null) {
            currentNew.setNext(new Node(new Player(currentOtherHead.getData()), null));
            currentNew = currentNew.getNext();
            currentOtherHead = currentOtherHead.getNext();

        }

        currentNew.setNext(null);
    }


    /** Clone method that creates a clone of the list. **/
    List createClone() {
        return new List(this);
    }


    void add(Player newPlayer) {
        Node newNode = new Node(new Player(newPlayer), head); // create a new node with a new player, and pointing to head.
        head = newNode; //the new player is now at head.
        size++; //increment size
    }

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


    public int getSize() {
        return size;
    }

}
