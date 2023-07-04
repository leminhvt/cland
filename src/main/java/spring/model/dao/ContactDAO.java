package spring.model.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.model.bean.Contact;

@Repository
public class ContactDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL = "SELECT * FROM vnecontact";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM vnecontact WHERE id = ?";
	private static final String FIND_ONE_BY_ID = "SELECT * FROM vnecontact WHERE id = ?";
	private static final String UPDATE_ONE_BY_ID = "UPDATE vnecontact SET fullname = ?,email = ?,subject = ?,content = ? WHERE id = ?";
	private static final String INSERT_ONE = "INSERT INTO vnecontact(fullname, email, subject, content) VALUES (?,?,?,?)";

	public List<Contact> getItems() {
		return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	public int del(int id) {
		return jdbcTemplate.update(DELETE_ONE_BY_ID, id);
	}

	public Contact getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_ONE_BY_ID, new BeanPropertyRowMapper<Contact>(Contact.class), id);
	}

	public int edit(Contact contact) {
		return jdbcTemplate.update(UPDATE_ONE_BY_ID, contact.getFullname(), contact.getEmail(), contact.getSubject(),
				contact.getContent(), contact.getId());
	}

	public int add(Contact contact) {
		return jdbcTemplate.update(INSERT_ONE, contact.getFullname(), contact.getEmail(), contact.getSubject(),
				contact.getContent());
	}

}
