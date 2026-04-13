package com.mycompany.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LoginTest {

    private login testLogin;

    /**
     * Creates a fresh login object before every test so that tests are
     * completely independent of one another.
     */
    @BeforeEach
    public void setUp() {
        testLogin = new login();
    }

    // ── checkUserName() ───────────────────────────────────────────────────────

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

    // ── checkPasswordComplexity() ─────────────────────────────────────────────

    /**
     * POE test data: "Ch&&sec@ke99!" meets all complexity rules.
     * Must return true.
     */
    @Test
    public void testCheckPasswordComplexity_ValidPassword_ReturnsTrue() {
        testLogin.setPassword("Ch&&sec@ke99!");
        assertTrue(testLogin.checkPasswordComplexity(),
            "'Ch&&sec@ke99!' meets all complexity rules — should be true.");
    }

    /**
     * POE test data: "password" — all lower-case, no digit, no special char.
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

    // ── checkCellPhoneNumber() ────────────────────────────────────────────────

    /**
     * POE test data: "+27838968976" — valid South African international format.
     * Must return true.
     */
    @Test
    public void testCheckCellPhoneNumber_ValidNumber_ReturnsTrue() {
        testLogin.setCellPhoneNumber("+27838968976");
        assertTrue(testLogin.checkCellPhoneNumber(),
            "'+27838968976' is a valid SA international number — should be true.");
    }

    /**
     * POE test data: "08966553" — no international code, too short.
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

    // ── registerUser() ────────────────────────────────────────────────────────

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
     * POE test data: password "password" fails complexity.
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
     * POE test data: cell number "08966553" is incorrectly formatted.
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

    // ── loginUser() ───────────────────────────────────────────────────────────

    /**
     * Stored credentials match entered credentials → must return true.
     * (POE: "Login Successful → The system returns: True")
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
     * (POE: "Login Failed → The system returns: False")
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

    // ── returnLoginStatus() ───────────────────────────────────────────────────

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
}

