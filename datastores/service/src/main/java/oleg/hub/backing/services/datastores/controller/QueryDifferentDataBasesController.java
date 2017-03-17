package oleg.hub.backing.services.datastores.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import oleg.hub.backing.services.datastores.service.EchoService;

@RestController
public class QueryDifferentDataBasesController {
	//TODO replace postgresTemplate and  sqlTemplate by JdbcService.getTemplate()
	@Autowired
	@Qualifier("postgresJdbcTemplate")
	private JdbcTemplate postgresTemplate;

	@Autowired
	@Qualifier("mysqlJdbcTemplate")
	private JdbcTemplate sqlTemplate;

	@RequestMapping(value = "/getCandidatesFromMySql")
	public String getPGUser() {
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		try {
			//TODO create factory for query based on params
			sqlMap = postgresTemplate.queryForMap("select * from candidate");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "MySQL[" + sqlMap.toString() + "].";
	}

	@RequestMapping(value = "/getCandidatesFromPostgreSQL")
	public String getMYUser() {
		Map<String, Object> postgreMap = new HashMap<String, Object>();
		try {
			//TODO create separate query factory
			postgreMap = sqlTemplate.queryForMap(" select * from candidate");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PostgreSQL[" + postgreMap.toString()+ "].";
	}
}