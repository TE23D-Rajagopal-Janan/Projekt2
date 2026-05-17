// Janan
// klass för funktioner och listor

package com.example;

import java.util.ArrayList;

public class Library {

    private ArrayList<User> users = new ArrayList<>();

    private ArrayList<SuspendedUser> suspendedUsers = new ArrayList<>();


    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<SuspendedUser>getSuspendedUsers() {
        return suspendedUsers;
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }

        return null;
    }

    public boolean canBorrow(String userId) {
        for (SuspendedUser suspendedUser :suspendedUsers) {
            if (suspendedUser.getUserId().equals(userId)) {
                return false;
            }
        }
        return true;
    }
}