package spring.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.model.bean.Role;

@Repository
public class RoleDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL = "SELECT * FROM roles";

	public List<Role> getItems() {
		return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<Role>(Role.class));
	}

}
