package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**
 * The Main class demonstrates the usage of the HashTable data structure.
 * It reads Player data from a file, populates the hash table, and tests
 * various HashTable methods such as find, copy constructor, and createClone.
 */
public class Main {
    /**
     * The main entry point of the program.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // === Step 1: Create the hash table ===
            HashTable table = new HashTable();

            // === Step 2: Read players from the file and add to the hash table ===
            Scanner infile = new Scanner(new FileReader("players.txt"));
            while (infile.hasNextLine()) {
                String name = infile.nextLine();
                if (!infile.hasNextLine()) break;

                int score = Integer.parseInt(infile.nextLine());
                Player player = new Player(name, score);
                table.add(player); // Add player to hash table
            }
            infile.close();

            // === Step 3: Print original hash table (organized by bucket) ===
            System.out.println("=== Original Hash Table ===");
            table.show();

            // === Step 4: Test the `find()` method ===
            System.out.println("\n=== Testing find() ===");
            String[] testNames = {"Kayla Lewis", "Emma Flores", "Nonexistent Player"};

            for (String name : testNames) {
                Player found = table.find(name);
                if (found != null) {
                    System.out.println("Found: " + found.getName() + " with score: " + found.getScore());
                } else {
                    System.out.println( name + " not found in hash table.");
                }
            }

            // === Step 5: Test the Copy Constructor ===
            System.out.println("\n=== Testing Copy Constructor ===");
            HashTable copiedTable = new HashTable(table); // Deep copy
            copiedTable.show(); // Should print same as original

            // Modify original and show both to prove they are independent
            System.out.println("Adding a new player to the original table...");
            table.add(new Player("Maaz", 9999));

            System.out.println("\nOriginal Table After Adding New Player:");
            table.show();

            System.out.println("\nCopied Table (Should NOT have the new player):");
            copiedTable.show();

            //  Test the createClone() method
            System.out.println("\n=== Testing createClone() ===");
            HashTable clonedTable = table.createClone(); // Should behave like deep copy
            clonedTable.show();



        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + e);
        }
    }


}


