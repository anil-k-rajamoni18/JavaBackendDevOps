package org.akteach;

import org.akteach.model.User;
import org.akteach.service.UserService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Welcome to Maven!");

        UserService userService = new UserService();

        // Add users
        userService.addUser(new User("1", "Ram", "ram@example.com"));
        userService.addUser(new User("2", "krishna", "krishna@example.com"));

        // Retrieve and print users
        System.out.println("User 1: " + userService.getUser("1"));
        System.out.println("User 2: " + userService.getUser("2"));

        // Delete a user
        userService.deleteUser("1");
        System.out.println("User 1 after deletion: " + userService.getUser("1"));

    }
}