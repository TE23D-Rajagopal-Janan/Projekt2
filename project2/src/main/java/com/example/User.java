package com.example;

public class User implements Comparable<User> {

    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) 
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User() {
    }

    public String getId() 
    {
        return id;
    }

    public String getName() 
    {
        return name;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setEmail(String email) 
    { this.email = email; }

    public String toString(){
        return name + " ," + email;
    }
    @Override
    public int compareTo(User otherUser) {

    return this.name.compareToIgnoreCase(
            otherUser.name
    );
}
}
