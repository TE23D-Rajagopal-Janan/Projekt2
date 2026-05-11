// Janan
// Basklass för bibliotekets objekt

package com.example;

public abstract class LibraryItem {
    
    private String id;
    private String title;
    private boolean isAvailable;

    public LibraryItem(String id, String title, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean status) {
        isAvailable = status;
    }

    public abstract String getInfo();
    
}
