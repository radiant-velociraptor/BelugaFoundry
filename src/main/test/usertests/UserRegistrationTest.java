package usertests;

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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author tmblount
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShiroConfig.class, SpringWebJavaConfig.class})
@WebAppConfiguration
public class UserRegistrationTest
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
    public void testCheckIfUserRegisteredTrue() throws Exception
    {
        mockMvc.perform(post("/isRegistered")
                .param("emailAddress", "Mel@Mel.com"))
                .andExpect(jsonPath("$.emailAddress", is("Mel@Mel.com")))
                .andExpect(jsonPath("$.registered", is(true)))
                .andDo(print());
    }

    @Test
    public void testCheckIfUserRegisteredFalse() throws Exception
    {
        mockMvc.perform(post("/isRegistered")
                .param("emailAddress", "not@registered.com"))
                .andExpect(jsonPath("$.emailAddress", is("not@registered.com")))
                .andExpect(jsonPath("$.registered", is(false)))
                .andDo(print());
    }
}
