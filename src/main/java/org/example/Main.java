package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize the hash table
            HashTable table = new HashTable();

            // Read data from players.txt and add to hash table
            Scanner infile = new Scanner(new FileReader("players.txt"));
            while (infile.hasNextLine()) {
                String name = infile.nextLine();
                if (!infile.hasNextLine()) break;

                int score = Integer.parseInt(infile.nextLine());

                Player player = new Player(name, score);
                table.add(player);
            }
            infile.close();

            // Print original hash table
            System.out.println("=== Original Hash Table ===");
            table.show();

            // Test copy constructor
            HashTable copiedTable = new HashTable(table);
            System.out.println("\n=== Copied Hash Table (via Copy Constructor) ===");
            copiedTable.show();

            // Test createClone method
            HashTable clonedTable = table.createClone();
            System.out.println("\n=== Cloned Hash Table (via createClone) ===");
            clonedTable.show();

            // Test find() method
            System.out.println("\n=== Testing find() ===");
            String testName = "SomePlayerName"; // Replace with a name that exists in your file
            Player found = table.find(testName);
            if (found != null) {
                System.out.println("Found: " + found.getName() + " - " + found.getScore());
            } else {
                System.out.println("Player \"" + testName + "\" not found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: players.txt file not found.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
