package web.controller.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AuthenticationService;
import service.UserService;
import views.authorization.AuthenticationView;

/**
 * @author tmblount
 */
@Controller
public class AuthenticationController
{
    //@Autowired
    //private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AuthenticationView login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    )
    {
        return authenticationService.logIn(username, password);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public AuthenticationView logout(
            @RequestParam(value = "username", required = true) String username
    )
    {
        return authenticationService.logout(username);
    }
}
