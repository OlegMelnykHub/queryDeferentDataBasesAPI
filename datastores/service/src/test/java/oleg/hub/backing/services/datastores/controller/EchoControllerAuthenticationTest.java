package oleg.hub.backing.services.datastores.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cdk.dmg.openplatformboot.coretest.BaseIntegrationTest;

import oleg.hub.backing.services.datastores.Application;

@SpringBootTest(
		classes = Application.class,
		webEnvironment = WebEnvironment.RANDOM_PORT
)
public class EchoControllerAuthenticationTest extends BaseIntegrationTest {

	private static final String MESSAGE = "this is a message";

	@Test
	public void testEchoJsonEndpointWorksWhenAuthenticated() throws Exception {
		ResponseEntity<Map> entity = authenticatedTemplate().getForEntity(url("rest/v1/echo/{msg}"), Map.class, MESSAGE);
		assertThat(entity.getStatusCode(), is(HttpStatus.OK));
		assertThat((String) entity.getBody().get("message"), is("default:" + MESSAGE));
	}

	@Test
	public void testEchoJsonEndpointFailsWhenNotAuthenticated() throws Exception {
		ResponseEntity<Map> entity = template().getForEntity(url("rest/v1/echo/{msg}"), Map.class, MESSAGE);
		assertThat(entity.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
	}

}
