package pettests;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author tmblount
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ShiroConfig.class, SpringWebJavaConfig.class})
@WebAppConfiguration
public class PetInfoTest
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
    public void testGetAllPets() throws Exception
    {
        mockMvc.perform(get("/pet/all"))
                .andDo(print())
                .andExpect(jsonPath("$[0].petId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Dragon (western)")))
                .andExpect(jsonPath("$[0].hp", is(150)))
                .andExpect(jsonPath("$[0].strength", is(55)))
                .andExpect(jsonPath("$[0].defense", is(20)))
                .andExpect(jsonPath("$[0].speed", is(25)))

                .andExpect(jsonPath("$[1].petId", is(2)))
                .andExpect(jsonPath("$[1].name", is("Dragon (eastern)")))
                .andExpect(jsonPath("$[1].hp", is(100)))
                .andExpect(jsonPath("$[1].strength", is(60)))
                .andExpect(jsonPath("$[1].defense", is(55)))
                .andExpect(jsonPath("$[1].speed", is(25)))

                .andExpect(jsonPath("$[2].petId", is(3)))
                .andExpect(jsonPath("$[2].name", is("Pegasus")))
                .andExpect(jsonPath("$[2].hp", is(75)))
                .andExpect(jsonPath("$[2].strength", is(40)))
                .andExpect(jsonPath("$[2].defense", is(20)))
                .andExpect(jsonPath("$[2].speed", is(100)))

                .andExpect(jsonPath("$[3].petId", is(4)))
                .andExpect(jsonPath("$[3].name", is("Unicorn")))
                .andExpect(jsonPath("$[3].hp", is(125)))
                .andExpect(jsonPath("$[3].strength", is(50)))
                .andExpect(jsonPath("$[3].defense", is(30)))
                .andExpect(jsonPath("$[3].speed", is(115)));
    }

    @Test
    public void testGetPetById() throws Exception
    {
        mockMvc.perform((get("/pet/byId"))
                .param("petId", "1"))
                .andDo(print())
                .andExpect(jsonPath("$.petId", is(1)))
                .andExpect(jsonPath("$.name", is("Dragon (western)")))
                .andExpect(jsonPath("$.hp", is(150)))
                .andExpect(jsonPath("$.strength", is(55)))
                .andExpect(jsonPath("$.defense", is(20)))
                .andExpect(jsonPath("$.speed", is(25)));
    }

    @Test
    public void testGetPetsByUserId() throws Exception
    {
        mockMvc.perform(get("/pet/user/pets")
                .param("userId", "2"))
                .andDo(print())
                .andExpect(jsonPath("$[0].petId", is(1)))
                .andExpect(jsonPath("$[0].name", is("Dragon (western)")))
                .andExpect(jsonPath("$[0].hp", is(150)))
                .andExpect(jsonPath("$[0].strength", is(55)))
                .andExpect(jsonPath("$[0].defense", is(20)))
                .andExpect(jsonPath("$[0].speed", is(25)));
    }
}
