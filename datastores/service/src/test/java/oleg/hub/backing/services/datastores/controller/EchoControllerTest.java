package oleg.hub.backing.services.datastores.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.cdk.dmg.openplatformboot.coretest.BaseTest;

import oleg.hub.backing.services.datastores.Application;
import oleg.hub.backing.services.datastores.service.EchoService;

@SpringBootTest(classes = Application.class)
public class EchoControllerTest extends BaseTest {

    private static final String MESSAGE = "this is a message";

    private static final String TEST_ECHO_STRING = "test";

    @Mock
    private EchoService echoService;

    @InjectMocks
    private EchoController echoController;

    protected MockMvc mockMvc;

    @Override
    public void setupMockMvc() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(echoController).build();
    }

    @Override
    protected void setupTest() {
        when(echoService.getEchoString()).thenReturn(TEST_ECHO_STRING);
    }
    
    @Test
    public void testEchoJsonEndpointWorks() throws Exception {
        mockMvc.perform(get("/rest/v1/echo/{msg}", MESSAGE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value(TEST_ECHO_STRING + ":" + MESSAGE));
    }

}
