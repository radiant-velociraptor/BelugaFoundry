package service;

import views.User;

public interface UserInfoService
{
    /**
     * Get info for the user attached to this email address;
     *
     * @param emailAddress the user's email address
     *
     * @return a user corresponding with this email address, if available
     */
    public User getUserInfo(String emailAddress);
}
