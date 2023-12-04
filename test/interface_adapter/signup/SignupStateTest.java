package interface_adapter.signup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupStateTest {

    @Test
    void getUsername() {
        SignupState state = new SignupState();
        String username = "username";
        state.setUsername(username);
        assertEquals(username, state.getUsername());
    }

    @Test
    void getUsernameError() {
        SignupState state = new SignupState();
        String error = "error";
        state.setUsernameError(error);
        assertEquals(error, state.getUsernameError());
    }

    @Test
    void getPassword() {
        SignupState state = new SignupState();
        String password = "password";
        state.setPassword(password);
        assertEquals(password, state.getPassword());
    }

    @Test
    void getPasswordError() {
        SignupState state = new SignupState();
        String error = "error";
        state.setPasswordError(error);
        assertEquals(error, state.getPasswordError());
    }

    @Test
    void getRepeatPassword() {
        SignupState state = new SignupState();
        String password = "password";
        state.setRepeatPassword(password);
        assertEquals(password, state.getRepeatPassword());
    }

    @Test
    void getRepeatPasswordError() {
        SignupState state = new SignupState();
        String error = "error";
        state.setRepeatPasswordError(error);
        assertEquals(error, state.getRepeatPasswordError());
    }

}