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

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);  // skapar ett scanner objeckt som läser in användarens val från tangentbordet

    Library library = new Library();

    ArrayList<Book> books = new ArrayList<>(); 
    ArrayList<Magazine> magazines = new ArrayList<>();   // ArrayLists för Book och Magazine objekt
    
    boolean meny = true;

    while (meny) {
        System.out.println("\n--- MENY ---");
        System.out.println("1. Hämta böcker");
        System.out.println("2. Hämta tidningar");
        System.out.println("3. Visa böcker");
        System.out.println("4. Visa tidningar");
        System.out.println("5. Lägg till bok");
        System.out.println("6. Lägg till tidning");

        System.out.println("7. Hämta användare");
        System.out.println("8. Hämta avstängda användare");
        System.out.println("9. Visa användare");
        System.out.println("10. Visa avstängda användare");
        System.out.println("11. Hitta användare via email");
        System.out.println("12. Ta bort bok");
        System.out.println("13. Lägg till avstängd användare");
        System.out.println("14. Lägg till  användare");
        System.out.println("15. Kontrollera om användare får låna");
        System.out.println("16. Avsluta");

        int choice = 0;
        try {
        choice = scanner.nextInt();
        scanner.nextLine();
    }
    catch (Exception e) {
        System.out.println("Fel input!");
        scanner.nextLine();
    }
        
        if (choice == 1) {
            HttpResponse<String> response = Unirest.get("http://10.151.168.5:3111/books").asString(); // programet skicakr en request till servern, och får tillbaka json data.  
            
            String json = response.getBody();  
            Gson gson = new Gson();     // Skapa ett JSON objekt 

            Type bookListType = new TypeToken<ArrayList<Book>>(){}.getType(); // säger för Gson att JSON datat ska bli en lista med böcker

            books = gson.fromJson(json, bookListType); //gör om json texten till riktiga Java-objekt.

            System.out.println("alla böcker hämtade");
        }
        
        else if (choice == 2) { 
            
            HttpResponse<String> response = Unirest.get("http://10.151.168.5:3111/magazines").asString(); // skickar request om att få magazines. 
            
            String json = response.getBody();

            Gson gson = new Gson();

            Type magazineListType = new TypeToken<ArrayList<Magazine>>(){}.getType();

            magazines = gson.fromJson(json, magazineListType);

            System.out.println("alla tidningar hämtade");
        }

        else if (choice == 3) {

            Collections.sort(books);

            for (Book book : books) {
                System.out.println(book.getInfo());
            }
        }


        else if (choice == 4) {
            
            Collections.sort(magazines);

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
            Gson gson = new Gson();
            String json = gson.toJson(newBook);
            Unirest.post("http://10.151.168.5:3111/books").header("Content-Type","application/json").body(json).asString();
            
            System.out.println("Ny bok tillagd på servern");
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

            Gson gson = new Gson();
            String json = gson.toJson(newMagazine);

            Unirest.post("http://10.151.168.5:3111/magazines").header("Content-Type","application/json").body(json).asString();
            
            System.out.println("Tidning tillagd på servern");
        }
        else if (choice == 7) {
            HttpResponse<String> response = Unirest.get("http://10.151.168.5:3111/users").asString();
            
            String json = response.getBody();
            
            Gson gson = new Gson();
            
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            
            library.getUsers().addAll(gson.fromJson(json, userListType));   // ska lägga user i library       
            
            System.out.println("Hämtat alla användare");
        }
        
        else if (choice == 8) {

            HttpResponse<String> response = Unirest.get( "http://10.151.168.5:3111/suspended").asString();
            
            String json = response.getBody();
            Gson gson = new Gson();
            Type suspendedListType = new TypeToken<ArrayList<SuspendedUser>>(){}.getType();

            library.getSuspendedUsers().addAll(gson.fromJson(json, suspendedListType));
            
            System.out.println("avstängda användare hämtade");
        }
        else if (choice == 9) {
            Collections.sort(library.getUsers());

            for (User user:library.getUsers()) {
            System.out.println(user);
    }
}
        else if (choice == 10) {
            
            for (SuspendedUser suspendedUser :library.getSuspendedUsers()){
            System.out.println(suspendedUser);
            }
            }
            
        else if (choice == 11) {
            System.out.print("Skriv email: ");

            String email = scanner.nextLine();

            User foundUser = library.findUserByEmail(email);
            
            if (foundUser != null) {
                System.out.println("Användare hittad:");
                System.out.println(foundUser);
            }
            else {
                System.out.println("Ingen användare hittades");
            }
        }
        else if (choice == 12) {
            System.out.print("Skriv ID på bok:");
            
            String id = scanner.nextLine();
            Unirest.delete("http://10.151.168.5:3111/books/"+ id).asString();
            
            System.out.println("Boken togs bort från servern");
        }
        else if (choice == 13){
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            System.out.print("User ID: ");
            String userId = scanner.nextLine();
            
            System.out.print("Reason: ");
            String reason = scanner.nextLine();
            
            SuspendedUser suspendedUser = new SuspendedUser(id,userId,reason);
            
            library.getSuspendedUsers().add(suspendedUser);  // Lägger till lokal
                
            Gson gson = new Gson();

            String json = gson.toJson(suspendedUser);            
            Unirest.post("http://10.151.168.5:3111/suspended").header("Content-Type","application/json").body(json).asString();
            
            System.out.println("Avstängd användare tillagd");

        }
        else if (choice == 14) {
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Namn: ");
            String name = scanner.nextLine();
            
            System.out.print("Email: ");
            String email = scanner.nextLine();
            User newUser = new User(id,name,email);
            
            library.getUsers().add(newUser);
            
            Gson gson = new Gson();
            
            String json = gson.toJson(newUser);
            
            Unirest.post("http://10.151.168.5:3111/users").header("Content-Type","application/json").body(json).asString();
            
            System.out.println("användare är tillagd");
}
        else if (choice == 15) {
            meny = false;
            System.out.println("Avslutar...");
            }
        }
    }
}