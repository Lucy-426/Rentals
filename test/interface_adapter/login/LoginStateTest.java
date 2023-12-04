package interface_adapter.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginStateTest {

    @Test
    void getUsername() {
        LoginState loginState = new LoginState();
        String username = "user";
        loginState.setUsername(username);
        assertEquals(loginState.getUsername(), username);
    }

    @Test
    void getUsernameError() {
        LoginState loginState = new LoginState();
        String error = "usernameError";
        loginState.setUsernameError(error);
        assertEquals(error, loginState.getUsernameError());


    }

    @Test
    void getPassword() {
        LoginState loginState = new LoginState();
        String password = "password";
        loginState.setPassword(password);
        assertEquals(password, loginState.getPassword());

    }

    @Test
    void getPasswordError() {
        LoginState loginState = new LoginState();
        String error = "passwordError";
        loginState.setPasswordError(error);
        assertEquals(error, loginState.getPasswordError());
    }

}