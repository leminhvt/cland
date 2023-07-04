package spring.model.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.model.bean.Category;

@Repository
public class CategoryDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL = "SELECT * FROM categories";
	private static final String FIND_0NE_BY_ID = "SELECT * FROM categories WHERE cid = ?";
	private static final String INSERT_ONE = "INSERT INTO categories(cname) VALUES (?)";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM categories WHERE cid=?";
	private static final String UPDATE_ONE_BY_ID = "UPDATE categories SET cname = ? WHERE cid = ?";

	public BeanPropertyRowMapper<Category> getRowMapper() {
		return new BeanPropertyRowMapper<Category>(Category.class);
	}

	public List<Category> getItems() {
		return jdbcTemplate.query(FIND_ALL, getRowMapper());
	}

	public int add(Category category) {
		return jdbcTemplate.update(INSERT_ONE, category.getCname());

	}

	public int delItem(int id) {
		return jdbcTemplate.update(DELETE_ONE_BY_ID, id);
	}

	public Category getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_0NE_BY_ID, getRowMapper(), id);
	}

	public int editItem(Category category) {
		return jdbcTemplate.update(UPDATE_ONE_BY_ID, category.getCname(), category.getCid());
	}

}
