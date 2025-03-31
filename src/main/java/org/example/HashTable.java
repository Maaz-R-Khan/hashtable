package org.example;

/**
 * A hash table implementation using separate chaining (via List).
 * Supports adding players, resizing on high load factor, deep copying, and searching by name.
 */
public class HashTable {

    private List[] hashTable; //Hash Table of size numBuckets
   private int numBuckets; //number of numBuckets
  private int size;


    /**
     * Computes the index of the bucket for a given Player object.
     * The index is determined using the hash code of the player's name,
     * which ensures a consistent mapping based on the player's identity.
     */
   private int getBucketIndex(Player p) {
       return Math.abs(p.hashCode()) % numBuckets;
   }

    /**
     * Computes the index of the bucket for a given player name.
     * This method is used when searching for a player by name, and
     * ensures that the hash function behaves consistently with the
     * one used when adding a Player object.
     */
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

       //Update numBuckets before recalculating hashes
        this.numBuckets = newNumBuckets;

        // Create a new hash table with double the number of buckets
        this.hashTable = new List[newNumBuckets];
        for (int i = 0; i < newNumBuckets; i++) {
            this.hashTable[i] = new List();
        }

        // Reset size (it will be recalculated during rehashing)
        this.size = 0;

        // Rehash all players from the old hash table into the new one
        for (int i = 0; i < oldNumBuckets; i++) {
            List bucket = oldHashTable[i];
            if (bucket != null) {
                Node current = bucket.getHead();
                while (current != null) {
                    this.add(current.getData()); // Re-add using updated hash index
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
        for (int i = 0; i < numBuckets; i++) {
            Node current = hashTable[i].getHead();
            if (current != null) {
                System.out.print("Bucket " + i + ": ");
                while (current != null) {
                    Player p = current.getData();
                    System.out.print(p.getName() + " (" + p.getScore() + ") -> ");
                    current = current.getNext();
                }
                System.out.println("null");
            }
        }
    }




}
