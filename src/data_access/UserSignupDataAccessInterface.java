package data_access;

import entity.User;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    String getUserPassword(String identifier);

    void save(User user);
}
