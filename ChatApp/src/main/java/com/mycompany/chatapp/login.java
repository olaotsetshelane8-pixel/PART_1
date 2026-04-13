/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Olaotse Tshelane
 */
public class login {
    
 private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String LastName;
 
    //getters and setters
    //username
    
    public void setUserName(String username){
        this.username = username;
    }
    public String getUserName(){
        return this.username;
    }
    
    //password
    
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    
    //cellPhoneNumber
    
    public void setCellPhoneNumber(String cellPhoneNumber){
        this.cellPhoneNumber = cellPhoneNumber;
    }
    public String getCellPhoneNumber(){
        return this.cellPhoneNumber;
    }
    
    //first Name
    
    public void setFirstname(String firstName){
        this.firstName = firstName;
    }
    
    public String getfirstName(){
        return this.firstName;
    }
    
    //lastName
    
    public void setlastName(String lastName){
        this.LastName = lastName;
    }
    public String getlastName(){
        return this.LastName;
    }
    
    
    
   
 
    
    //Checks that the username contains an underscore (_)
    // and is no more than five characters long.
   
    
    public boolean checkUserName() {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
 
    
     //Checks that the password meets the following complexity rules:
     // - At least eight characters long
     // - Contains a capital letter
     // - Contains a number
     // - Contains a special character
     
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) return false;
 
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
 
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
 
        return hasCapital && hasNumber && hasSpecial;
    }
 
    
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) return false;
       
        return cellPhoneNumber.matches("^\\+\\d{1,3}\\d{1,10}$")
                && (cellPhoneNumber.length() <= 13);
    }
 
    
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Registration successful.";
    }
 
   
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (username == null || password == null) return false;
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
 
   
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + firstName + ", " + LastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    void setUsername(String kyl_1) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}
