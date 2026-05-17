// Janan
// Klass för tidningarna

package com.example;

public class Magazine extends LibraryItem{

    private int issueNumber;
    private String category;
    private int publishedYear;

    public Magazine(String id, String title, boolean isAvailable,int issueNumber, String category, int publishedYear) {

        super(id, title, isAvailable);                     // konstruktor för manuelt tilägg av Magazine 

        this.issueNumber = issueNumber;
        this.category = category;
        this.publishedYear = publishedYear;
    }

    public Magazine() {
        super("", "", true);  //  konstruktor som är tom för gason, så gason kan läsa in dem 
    }

    public int getIssueNumber() 
    {
        return issueNumber;
    }

    public String getCategory() 
    {
        return category;
    }

    public int getPublishedYear() 
    {
        return publishedYear;
    }

    @Override
    public String getInfo() 
    {
        return getTitle() + " , " + category;
    }
}
