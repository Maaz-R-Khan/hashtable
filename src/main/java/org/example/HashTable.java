package org.example;

public class HashTable {

    private List[] hashTable; //Hash Table of size numBuckets
   private int numBuckets; //number of numBuckets
  private int size;


   /** The hash function method calculates a bucket index. **/
   private int getBucketIndex(Player p) {
       return Math.abs(p.hashCode()) % numBuckets;
   }

    private int getBucketIndex(String name) {
        return Math.abs(name.hashCode()) % numBuckets;
    }

    /** Default Constructor **/
    public HashTable() {
        this.numBuckets = 4;
        this.hashTable = new List[this.numBuckets]; //initialize hash table with 4 numBuckets
        for(int i = 0; i < this.numBuckets; i++) {
            this.hashTable[i] = new List(); //creates a list at each bucket.
        }
    }

    /** Deep copy constructor**/
    HashTable(HashTable other) {
        this.numBuckets = other.numBuckets; //copy the same amount of numBuckets to the new hash table.
        this.hashTable = new List[other.numBuckets]; //create a new hashTable with same number of numBuckets.

    for(int i = 0; i < this.numBuckets; i++) {
        this.hashTable[i] = new List(other.hashTable[i]);
    }
    }

    HashTable createClone() {
        return new HashTable(this);
    }

    /** This method resizes the hash table if the load factor exceeds 0.75 **/
    public void resize() {
        List[] oldHashTable = this.hashTable;
        int oldNumBuckets = this.numBuckets;

        int newNumBuckets = oldNumBuckets * 2;
        this.hashTable = new List[newNumBuckets];

        //Initialize each bucket in the new list.
        for(int i = 0; i < newNumBuckets; i++) {
            this.hashTable[i] = new List();
        }

        this.size = 0;

       for(int i = 0; i < oldNumBuckets; i++) {
           List bucket = oldHashTable[i];
           if(bucket != null) {
               Node current = bucket.getHead();
               while (current != null) {
                   this.add(current.getData()); // Re-add using the updated hash function
                   current = current.getNext();
               }
           }
       }
    }

    /**
     * This method adds a player to the hash table**/
    void add(Player p) {
       int index = getBucketIndex(p); //get the index of the list.
        double loadFactor = (double) size /  numBuckets;

        if (loadFactor > 0.75) {
            resize();
        }

        if(hashTable[index] == null) {
         hashTable[index] = new List();
       }


        hashTable[index].add(p);
        size++;
    }

    /** The find method  Searches the hash table for the player with the given name.
     * It returns the Player instance if it finds it, and null otherwise  **/
    Player find(String name) {
        int index = getBucketIndex(name);
        return hashTable[index].find(name);
    }

    /** The show method prints all the data in the hash table. **/
    void show() {
        for(int i = 0; i < size; i++) {
            System.out.println(hashTable[i] + " ");
        }
        System.out.println();
    }



}
