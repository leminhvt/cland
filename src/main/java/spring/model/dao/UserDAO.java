package spring.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.model.bean.Role;
import spring.model.bean.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL = "SELECT u.*,r.name FROM users AS u INNER JOIN roles AS r ON u.roleid = r.id";
	private static final String FIND_ONE_BY_USERNAME = "SELECT u.*,r.name FROM users AS u INNER JOIN roles AS r ON u.roleid = r.id WHERE u.username = ?";
	private static final String INSERT_ONE = "INSERT INTO users(username, fullname, password, roleid) VALUES (?,?,?,?)";
	private static final String COUNT_USERNAME = "SELECT COUNT(*) FROM users WHERE username = ?";
	private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_ONE_BY_ID = "UPDATE users SET fullname = ?,password = ?,roleid = ? WHERE id = ?";
	private static final String FIND_ONE_BY_ID = "SELECT u.*,r.name FROM users AS u INNER JOIN roles AS r ON u.roleid = r.id WHERE u.id = ?";

	private RowMapper<User> getRowMapper() {
		return new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"),
						rs.getString("password"), new Role(rs.getInt("roleid"), rs.getString("name")));
				return user;
			}
		};
	}

	public List<User> getItems() {
		return jdbcTemplate.query(FIND_ALL, getRowMapper());
	}

	public User getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_ONE_BY_ID, getRowMapper(), id);
	}

	public int add(User user) {
		return jdbcTemplate.update(INSERT_ONE, user.getUsername(), user.getFullname(), user.getPassword(),
				user.getRole().getId());
	}

	public boolean hasUser(String username) {
		return jdbcTemplate.queryForObject(COUNT_USERNAME, Boolean.class, username);
	}

	public int delete(int id) {
		return jdbcTemplate.update(DELETE_BY_ID, id);
	}

	public int edit(User user) {
		return jdbcTemplate.update(UPDATE_ONE_BY_ID, user.getFullname(), user.getPassword(), user.getRole().getId(),
				user.getId());
	}

	public User getItemByUsername(String username) {
		return jdbcTemplate.queryForObject(FIND_ONE_BY_USERNAME, getRowMapper(), username);
	}

}
