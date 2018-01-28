package authenticationtests;

import config.appconfig.DatabaseConfig;
import config.appconfig.ShiroConfig;
import config.webconfig.SpringWebJavaConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author tmblount
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfig.class, ShiroConfig.class, SpringWebJavaConfig.class})
@WebAppConfiguration
public class AuthenticationTest
{
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testWacNonNull() throws Exception
    {
        Assert.assertNotNull(wac);
    }

    @Test
    public void testLoginSucceed() throws Exception
    {
        mockMvc.perform(post("/login")
                                .param("username", "Mel")
                                .param("password", "Mel"))
                                .andExpect(jsonPath("$.loginMessage", is("OK")))
                                .andExpect(jsonPath("$.userName", is("Mel")))
                .andDo(print());
    }

    @Test
    public void testLoginWrongPassword() throws Exception
    {
        mockMvc.perform(post("/login")
                                .param("username", "Mel")
                                .param("password", "wronglol"))
                .andDo(print());
    }

    @Test
    public void testLoginAccountNotFound() throws Exception
    {
        mockMvc.perform(post("/login")
                                .param("username", "DoesNotExist")
                                .param("password", "Fake"))
                .andDo(print());
    }
}