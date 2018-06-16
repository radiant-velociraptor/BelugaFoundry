package web.controller.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserInfoService;
import service.UserRegistrationService;
import views.User;
import views.UserView;

/**
 * @author tmblount
 */
@Controller
public class RegistrationController
{
    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    @ResponseBody
    public UserView register(
            @RequestParam(value = "emailAddress", required = true) String email,
            @RequestParam(value = "username", required = false, defaultValue = "Player") String username,
            @RequestParam(value = "password", required = true) String password
    )
    {
        UserView userView = new UserView();

        User user = userInfoService.getUserInfo(email);

        // Does the user exist?
        if (user == null)
        {
            // TODO register the user, creating a user object at the same time
            user = userRegistrationService.registerUser(email, username, password);

            userView.setRegistered(false);
            userView.setNew(true);
        }

        return userView;
    }

    @RequestMapping(value = "/isRegistered", method = RequestMethod.POST)
    @ResponseBody
    public UserView isRegistered(
            @RequestParam(value = "emailAddress", required = true) String email)
    {
        UserView userView = new UserView();

        User user = userInfoService.getUserInfo(email);

        userView.setEmailAddress(email);

        // Does the user exist?
        if (user == null)
        {
            userView.setRegistered(false);
        }

        return userView;
    }

    @RequestMapping(value = "/usernameTaken", method = RequestMethod.POST)
    @ResponseBody
    public UserView usernameTaken(
            @RequestParam(value = "username", required = true) String username
    )
    {
        // TODO create query to check this

        return new UserView();
    }
}
