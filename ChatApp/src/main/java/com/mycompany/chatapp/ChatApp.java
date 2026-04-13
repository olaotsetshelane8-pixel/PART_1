/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import java.util.Scanner;
/**
 *
 * @author Olaotse Tshelane
 */

public class ChatApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        login Chatapp = new login();
      

        System.out.println("=== Welcome to ChatApp ===\n");

        // ── REGISTRATION ──────────────────────────────
        System.out.println("--- Register ---");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        Chatapp.setFirstname(firstName);

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        Chatapp.setlastName(lastName);

        System.out.print("Enter username (must contain '_' and be ≤5 chars): ");
        String username = input.nextLine();
        Chatapp.setUserName(username);

        System.out.print("Enter password (≥8 chars, capital, number, special char): ");
        String password = input.nextLine();
        Chatapp.setPassword(password);

        System.out.print("Enter South African cell number (e.g. +27838968976): ");
        String cellPhoneNumber = input.nextLine();
        Chatapp.setCellPhoneNumber(cellPhoneNumber);

        String registrationResult = Chatapp.registerUser();
        System.out.println("\n" + registrationResult + "\n");

        // Only allow login if registration was successful
        if (!registrationResult.equals("Registration successful.")) {
            System.out.println("Please restart and try again.");
            
            return;
        }

        // ── LOGIN ──────────────────────────────────────
        System.out.println("--- Login ---");

        System.out.print("Enter username: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = input.nextLine();

        String loginStatus = Chatapp.returnLoginStatus(enteredUsername, enteredPassword);
        System.out.println("\n" + loginStatus);

        input.close();

 
    }
}
