package oleg.hub.backing.services.datastores.acceptance;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Resource;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EchoTest extends AcceptanceTestBase {

    @Resource
    private ServiceContext serviceContext;
    
    @Before
    public void setup() {
        // To be implemented if any pre-initialization / setup is required
    }

    @Test
    public void canEcho() throws ClientProtocolException, IOException {
        String path = "rest/v1/echo/hello";
        List<AcceptanceRequestResponse<CloseableHttpResponse>> responses = serviceContext.makeHttpRequest(path);
        for (AcceptanceRequestResponse<CloseableHttpResponse> res : responses) {
            int resStatus = res.getResponse().getStatusLine().getStatusCode();
            Assert.assertEquals(HttpStatus.SC_OK, resStatus);
            String responseBody = new Scanner(res.getResponse().getEntity().getContent(), "UTF-8").useDelimiter("\\A").next();
            Assert.assertEquals("{\"message\":\"default:hello\"}", responseBody);
        }
    }

    @After
    public void tearDown() {
        // To be implemented if any tear down activity is required
    }

}