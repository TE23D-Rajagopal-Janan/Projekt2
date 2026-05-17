package com.example;

public class SuspendedUser {

    private String id;
    private String userId;
    private String reason;

    public SuspendedUser(String id, String userId, String reason) 
    {
        this.id = id;
        this.userId = userId;   
        this.reason = reason;
    }

    public SuspendedUser() {     // konstruktor för gason
    }

    public String getId() 
    {
        return id;
    }

    public String getUserId() 
    {
        return userId;
    }

    public String getReason() 
    {
        return reason;
    }

    public String toString() {

           return "User ID: " + userId + "| Reason: " + reason;
    }
}
