// Janan
// Main klass med meny och ArrayList

package com.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Magazine> magazines = new ArrayList<>();
        
    boolean meny = true;

    while (meny) {
    System.out.println("Meny");
    System.out.println("1. Lägg till en bok");
    System.out.println("2. Lägg till en tidning");
    System.out.println("3. Visa alla böcker");
    System.out.println("4. Visa alla tidningar");
    System.out.println("5. Avsluta");
    }

    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice == 1) {

        System.out.println("Skriv in följande");
        System.out.println("Title: ");
        String title = scanner.nextLine();
    }

    }
}