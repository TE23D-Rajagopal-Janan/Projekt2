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
        System.out.println("2. Lägg till bok");
        System.out.println("3. Visa böcker");
        System.out.println("4. Avsluta");

        int choice = scanner.nextInt();
        scanner.nextLine();

         // HÄMTA BÖCKER
        if (choice == 1) {
            HttpResponse<String> response = Unirest.get("http://10.151.168.5:3111/books").asString();
            
            String json = response.getBody();
            Gson gson = new Gson();
            
            Type bookListType =
            new TypeToken<ArrayList<Book>>(){}.getType();
            books = gson.fromJson(json, bookListType);

            System.out.println("alla böcker hämtade");
            }
    
    else if (choice == 2) {
        System.out.print("Titel: ");
        String title = scanner.nextLine();

        System.out.print("Kategori: ");
        String category = scanner.nextLine();

        System.out.print("Issue Number: ");
        int issue = scanner.nextInt();

        System.out.print("År: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        
        Magazine newMagazine = new Magazine("1",title,true,issue,category, year);
        
        magazines.add(newMagazine);
        System.out.println("Tidning tillagd");
    }
}
}
}