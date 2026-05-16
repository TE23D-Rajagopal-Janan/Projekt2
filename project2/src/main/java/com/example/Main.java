// Janan
// Main klass med meny och ArrayList
package com.example;

import java.util.ArrayList;
import java.util.Scanner;

import kong.unirest.core.Unirest;
import kong.unirest.core.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Magazine> magazines = new ArrayList<>();

    boolean meny = true;

    while (meny) {
        System.out.println("--- MENY ---");
        System.out.println("1. Hämta böcker");
        System.out.println("2. Hämta tidningar");
        System.out.println("3. Visa böcker");
        System.out.println("4. Visa tidningar");
        System.out.println("5. Lägg till bok");
        System.out.println("6. Lägg till tidning");
        System.out.println("7. Avsluta");

        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            HttpResponse<String> response = Unirest.get("http://10.151.168.5:3111/books").asString();
            
            String json = response.getBody();
            Gson gson = new Gson();     // Skapa ett JSON objekt 
            
            Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType();

            books = gson.fromJson(json, bookListType);

            System.out.println("alla böcker hämtade");
            }
        
        else if (choice == 2) { 
            
            HttpResponse<String> response = Unirest.get("http://10.151.168.5:3111/magazines").asString();
            
            String json = response.getBody();

            Gson gson = new Gson();

            Type magazineListType = new TypeToken<ArrayList<Magazine>>(){}.getType();

            magazines = gson.fromJson(json, magazineListType);

            System.out.println("alla tidningar hämtade");
    }

        else if (choice == 3) {

            for (Book book : books) {
                System.out.println(book.getInfo());
            }
        }


        else if (choice == 4) {
            for (Magazine magazine : magazines) {
                System.out.println(magazine.getInfo());
            }
        }

        else if (choice == 5) {
            System.out.print("Titel: ");
            String title = scanner.nextLine();

            System.out.print("Författare: ");
            String author = scanner.nextLine();

            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Sidor: ");
            int pages = scanner.nextInt();
            scanner.nextLine();

            Book newBook = new Book("1",title,true,author,genre,pages);
            
            books.add(newBook);

            System.out.println("ny bok tillagd");
            }

        else if (choice == 6) {
            System.out.print("Titel: ");
            String title = scanner.nextLine();
            
            System.out.print("Kategori: ");
            String category = scanner.nextLine();
            
            System.out.print("Issue Number: ");
            int issueNumber = scanner.nextInt();
            
            System.out.print("År: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            Magazine newMagazine = new Magazine("1",title,true,issueNumber,category,year);
            
            magazines.add(newMagazine);
            System.out.println("Tidning tillagd!");
            }

            else if (choice == 7) {

                meny = false;

                System.out.println("Avslutar...");
            }
        }
    }
}