package com.mycompany.chatapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;


public class LoginTest {

    private login testLogin;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    /**
     * Creates a fresh login object before every test so that tests are
     * completely independent of one another.
     */
    @BeforeEach
    public void setUp() {
        testLogin = new login();
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    //  checkUserName()

    /** Username contains '_' and is ≤ 5 characters , must return true. */
    @Test
    public void testCheckUserName_ValidUsername_ReturnsTrue() {
        testLogin.setUserName("kyl_1");
        assertTrue(testLogin.checkUserName(),
            "Username 'kyl_1' contains '_' and is 5 chars — should be valid.");
    }

    /** Username has no underscore , must return false. */
    @Test
    public void testCheckUserName_NoUnderscore_ReturnsFalse() {
        testLogin.setUserName("kyle1");
        assertFalse(testLogin.checkUserName(),
            "Username 'kyle1' has no '_' — should be invalid.");
    }

    /** Username is longer than 5 characters , must return false. */
    @Test
    public void testCheckUserName_TooLong_ReturnsFalse() {
        testLogin.setUserName("kyle_longer");
        assertFalse(testLogin.checkUserName(),
            "Username 'kyle_longer' exceeds 5 chars — should be invalid.");
    }

    /** Null username → must return false (no NullPointerException). */
    @Test
    public void testCheckUserName_NullUsername_ReturnsFalse() {
        // username is never set — defaults to null
        assertFalse(testLogin.checkUserName(),
            "Null username should return false.");
    }

    //checkPasswordComplexity()

    /**
     * test data: "Ch&&sec@ke99!" meets all complexity rules.
     * Must return true.
     */
    @Test
    public void testCheckPasswordComplexity_ValidPassword_ReturnsTrue() {
        testLogin.setPassword("Ch&&sec@ke99!");
        assertTrue(testLogin.checkPasswordComplexity(),
            "'Ch&&sec@ke99!' meets all complexity rules — should be true.");
    }

    /**
     *  test data: "password" — all lower-case, no digit, no special char.
     * Must return false.
     */
    @Test
    public void testCheckPasswordComplexity_SimplePassword_ReturnsFalse() {
        testLogin.setPassword("password");
        assertFalse(testLogin.checkPasswordComplexity(),
            "'password' fails complexity rules — should be false.");
    }

    /** Password shorter than 8 characters → must return false. */
    @Test
    public void testCheckPasswordComplexity_TooShort_ReturnsFalse() {
        testLogin.setPassword("Ab1!");
        assertFalse(testLogin.checkPasswordComplexity(),
            "Password shorter than 8 chars should be invalid.");
    }

    /** Null password → must return false (no NullPointerException). */
    @Test
    public void testCheckPasswordComplexity_NullPassword_ReturnsFalse() {
        assertFalse(testLogin.checkPasswordComplexity(),
            "Null password should return false.");
    }

    //  checkCellPhoneNumber() 

    /**
     * test data: "+27838968976" — valid South African international format.
     * Must return true.
     */
    @Test
    public void testCheckCellPhoneNumber_ValidNumber_ReturnsTrue() {
        testLogin.setCellPhoneNumber("+27838968976");
        assertTrue(testLogin.checkCellPhoneNumber(),
            "'+27838968976' is a valid SA international number — should be true.");
    }

    /**
     * test data: "08966553" — no international code, too short.
     * Must return false.
     */
    @Test
    public void testCheckCellPhoneNumber_NoInternationalCode_ReturnsFalse() {
        testLogin.setCellPhoneNumber("08966553");
        assertFalse(testLogin.checkCellPhoneNumber(),
            "'08966553' has no international code — should be false.");
    }

    /** Null cell number → must return false (no NullPointerException). */
    @Test
    public void testCheckCellPhoneNumber_NullNumber_ReturnsFalse() {
        assertFalse(testLogin.checkCellPhoneNumber(),
            "Null cell number should return false.");
    }

    // registerUser()

    /** All fields valid → "Registration successful." */
    @Test
    public void testRegisterUser_AllValid_ReturnsSuccessMessage() {
        testLogin.setFirstname("John");
        testLogin.setlastName("Doe");
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");
        testLogin.setCellPhoneNumber("+27838968976");

        assertEquals("Registration successful.", testLogin.registerUser(),
            "All valid data should return 'Registration successful.'");
    }

    /** Invalid username → username error message returned. */
    @Test
    public void testRegisterUser_InvalidUsername_ReturnsUsernameError() {
        testLogin.setUserName("toolongusername");
        testLogin.setPassword("Ch&&sec@ke99!");
        testLogin.setCellPhoneNumber("+27838968976");

        assertEquals(
            "Username is not correctly formatted; please ensure that your username "
          + "contains an underscore and is no more than five characters in length.",
            testLogin.registerUser(),
            "Invalid username should return the username error message.");
    }

    /**
     * test data: password "password" fails complexity.
     * Set a valid username and phone so only the password check triggers.
     */
    @Test
    public void testRegisterUser_InvalidPassword_ReturnsPasswordError() {
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("password");
        testLogin.setCellPhoneNumber("+27838968976");

        assertEquals(
            "Password is not correctly formatted; please ensure that the password "
          + "contains at least eight characters, a capital letter, a number, and a "
          + "special character.",
            testLogin.registerUser(),
            "Password 'password' should return the password error message.");
    }

    /**
     * test data: cell number "08966553" is incorrectly formatted.
     * Set valid username and password so only the phone check triggers.
     */
    @Test
    public void testRegisterUser_InvalidCellNumber_ReturnsCellError() {
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");
        testLogin.setCellPhoneNumber("08966553");

        assertEquals(
            "Cell phone number incorrectly formatted or does not contain "
          + "international code.",
            testLogin.registerUser(),
            "'08966553' should return the cell phone error message.");
    }

    // loginUser() 

    /**
     * Stored credentials match entered credentials → must return true.
     * "Login Successful → The system returns: True"
     */
    @Test
    public void testLoginUser_CorrectCredentials_ReturnsTrue() {
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");

        assertTrue(testLogin.loginUser("kyl_1", "Ch&&sec@ke99!"),
            "Matching credentials should return true.");
    }

    /**
     * Wrong password → must return false.
     * "Login Failed → The system returns: False"
     */
    @Test
    public void testLoginUser_WrongPassword_ReturnsFalse() {
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");

        assertFalse(testLogin.loginUser("kyl_1", "wrongpassword"),
            "Incorrect password should return false.");
    }

    /** Wrong username → must return false. */
    @Test
    public void testLoginUser_WrongUsername_ReturnsFalse() {
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");

        assertFalse(testLogin.loginUser("wrong", "Ch&&sec@ke99!"),
            "Incorrect username should return false.");
    }

    // returnLoginStatus()

    /** Correct credentials → welcome message with first and last name. */
    @Test
    public void testReturnLoginStatus_CorrectCredentials_ReturnsWelcome() {
        testLogin.setFirstname("John");
        testLogin.setlastName("Doe");
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");

        assertEquals(
            "Welcome John, Doe it is great to see you again.",
            testLogin.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"),
            "Successful login should return the welcome message.");
    }

    // Wrong credentials → error message
    @Test
    public void testReturnLoginStatus_WrongCredentials_ReturnsError() {
        testLogin.setFirstname("John");
        testLogin.setlastName("Doe");
        testLogin.setUserName("kyl_1");
        testLogin.setPassword("Ch&&sec@ke99!");

        assertEquals(
            "Username or password incorrect, please try again.",
            testLogin.returnLoginStatus("kyl_1", "badpass"),
            "Failed login should return the incorrect credentials message.");
    }

    /**
     * Test of setUserName method, of class login.
     */
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        String username = "";
        login instance = new login();
        instance.setUserName(username);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserName method, of class login.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        login instance = new login();
        String expResult = "";
        String result = instance.getUserName();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class login.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        login instance = new login();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class login.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        login instance = new login();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
       
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCellPhoneNumber method, of class login.
     */
    @Test
    public void testSetCellPhoneNumber() {
        System.out.println("setCellPhoneNumber");
        String cellPhoneNumber = "";
        login instance = new login();
        instance.setCellPhoneNumber(cellPhoneNumber);
       
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCellPhoneNumber method, of class login.
     */
    @Test
    public void testGetCellPhoneNumber() {
        System.out.println("getCellPhoneNumber");
        login instance = new login();
        String expResult = "";
        String result = instance.getCellPhoneNumber();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstname method, of class login.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        String firstName = "";
        login instance = new login();
        instance.setFirstname(firstName);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of getfirstName method, of class login.
     */
    @Test
    public void testGetfirstName() {
        System.out.println("getfirstName");
        login instance = new login();
        String expResult = "";
        String result = instance.getfirstName();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of setlastName method, of class login.
     */
    @Test
    public void testSetlastName() {
        System.out.println("setlastName");
        String lastName = "";
        login instance = new login();
        instance.setlastName(lastName);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of getlastName method, of class login.
     */
    @Test
    public void testGetlastName() {
        System.out.println("getlastName");
        login instance = new login();
        String expResult = "";
        String result = instance.getlastName();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUserName method, of class login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber();
        assertEquals(expResult, result);
       
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        login instance = new login();
        String expResult = "";
        String result = instance.registerUser();
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String enteredUsername = "";
        String enteredPassword = "";
        login instance = new login();
        boolean expResult = false;
        boolean result = instance.loginUser(enteredUsername, enteredPassword);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        String enteredUsername = "";
        String enteredPassword = "";
        login instance = new login();
        String expResult = "";
        String result = instance.returnLoginStatus(enteredUsername, enteredPassword);
        assertEquals(expResult, result);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class login.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String kyl_1 = "";
        login instance = new login();
        instance.setUsername(kyl_1);
       
        fail("The test case is a prototype.");
    }
}

