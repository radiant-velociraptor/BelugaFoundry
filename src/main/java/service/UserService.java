package service;

import views.User;

/**
 * @author tmblount
 */
public interface UserService
{
    /**
     * Get info for the user attached to this email address;
     *
     * @param emailAddress the user's email address
     *
     * @return a user corresponding with this email address, if available
     */
    public User getUserInfo(String emailAddress);

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
