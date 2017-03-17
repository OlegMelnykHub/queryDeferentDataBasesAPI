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
	public String getCandidatesFromMySql() {
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
	public String getCandidatesFromPostgreSQL() {
		Map<String, Object> postgreMap = new HashMap<String, Object>();
		try {
			//TODO create separate query factory
			postgreMap = sqlTemplate.queryForMap(" select * from candidate");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "PostgreSQL[" + postgreMap.toString()+ "].";
	}

	@ApiOperation(value = "provides more detailed customer information")
	@RequestMapping(value = "/candidates/{id}")
	@PreAuthorize("hasAnyAuthority('ONLY_MANAGER','ONLY_REGIONAL_MANAGER')")
	public List<Candidate> getCandidatesFromPostgreSQL() {
		@ApiParam(value = "candidate id") @PathVariable String id,
		List<Candidate> candidates;
		try {
			//TODO create separate query factory
			candidates = sqlTemplate.queryObjects(" select * from candidate where id='"+id+"'");
		} catch (Exception e) {
			candidates = null;
			e.printStackTrace();
		}
		System.oyt.prtintln("PostgreSQL cnadidate="+id+" ditails [" + candidates+ "].");
		return candidates;
	}

}