package service;

import views.User;

/**
 * @author tmblount
 */
public interface UserRegistrationService
{
    /**
     * Register a user.
     *
     * @param emailAddress the user's email address
     * @param username the user's username/nickname
     * @param password the user's password
     *
     * @return a user corresponding with this email address, if possible
     */
    public User registerUser(String emailAddress, String username, String password);
}
