package oleg.hub.backing.services.datastores.controller;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import oleg.hub.backing.services.datastores.service.EchoService;

@RestController
@RequestMapping(value = "/rest/v1")
@Api(value = "echo-apis", description = "Template for Service RESTful echo end-points")
public class EchoController {

    @Resource
    private EchoService echoService;

	@RequestMapping(method = RequestMethod.GET, value = "/echo/{msg}")
    @ApiOperation(httpMethod = "GET", value = "Echoes message")
	@TimedHandler(name = "http.controller.echo", absolute = true)
	public Map<String, String> echo(@ApiParam(value = "Message for the API to be echo", required = true) @PathVariable("msg") String msg) {
		Map<String, String> er = new HashMap<String, String>();
		String returnMessage = echoService.getEchoString() + ":" + msg;
		er.put("message", returnMessage);
		return er;
	}

}