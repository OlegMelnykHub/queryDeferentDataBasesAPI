package oleg.hub.backing.services.datastores.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {

	private String echoString = "default";

	public String getEchoString() {
		return echoString;
	}

}