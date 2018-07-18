package battletests;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author tmblount
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShiroConfig.class, SpringWebJavaConfig.class})
@WebAppConfiguration
public class BattleArenaTest
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
    public void testAttack() throws Exception
    {
        mockMvc.perform(post("/login")
                .param("emailAddress", "Mel@Mel.com")
                .param("password", "Mel"))
                .andDo(print())
                .andExpect(jsonPath("$.loginMessage", is("OK")))
                .andExpect(jsonPath("$.userName", is("Mel@Mel.com")))
                .andExpect(jsonPath("$.user.username", is("Mel")))
                .andExpect(jsonPath("$.user.emailAddress", is("Mel@Mel.com")))
                .andExpect(jsonPath("$.user.banned", is("F")));

        mockMvc.perform(get("/calculateAttkDamage")
                .param("userId", "2")
                .param("petId", "2")
                .param("opponentPetDef", "25"))
                .andDo(print());
    }

    @Test
    public void testVictory() throws Exception
    {
        mockMvc.perform(post("/login")
                .param("emailAddress", "Mel@Mel.com")
                .param("password", "Mel"))
                .andDo(print())
                .andExpect(jsonPath("$.loginMessage", is("OK")))
                .andExpect(jsonPath("$.userName", is("Mel@Mel.com")))
                .andExpect(jsonPath("$.user.username", is("Mel")))
                .andExpect(jsonPath("$.user.emailAddress", is("Mel@Mel.com")))
                .andExpect(jsonPath("$.user.banned", is("F")));

        mockMvc.perform(get("/winner")
                .param("username", "Mel"))
                .andDo(print())
                .andExpect(jsonPath("$.winner", is("Mel")));
    }
}
