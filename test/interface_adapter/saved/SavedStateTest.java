package interface_adapter.saved;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SavedStateTest {

    @Test
    void getUsername() {
        SavedState savedState = new SavedState();
        String username = "testUsername";
        savedState.setUsername(username);
        assertEquals(username, savedState.getUsername());
    }

    @Test
    void getSavedListings() {
        SavedState state = new SavedState();
        state.setSavedListings(null);
        assertNull(state.getSavedListings());
    }

    @Test
    void setUsername() {
    }

    @Test
    void setSavedListings() {
    }
}