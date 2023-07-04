package spring.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.model.bean.Category;
import spring.model.bean.Land;
import spring.model.bean.Like;
import spring.model.bean.Role;
import spring.model.bean.User;

@Repository
public class LikeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_PAGINATION = "SELECT k.*,l.*,c.*,u.*,e.* FROM likes AS k INNER JOIN lands AS l ON k.id_land = l.lid INNER JOIN categories AS c ON k.id_cat = c.cid INNER JOIN users AS u ON k.id_user = u.id INNER JOIN roles AS e ON k.id_role = e.id";
	private static final String FIND_PAGINATION_ONE = "SELECT k.*,l.* FROM lands AS l LEFT JOIN likes AS k ON k.id_land = l.lid WHERE k.id_land = ? AND k.id_user = ?;";
	private static final String COUT_USER_ID = "SELECT COUNT(id_user) FROM likes WHERE id_land = ? AND status = ?";
	private static final String UPDATE_STATUS_BY_ID = "UPDATE likes SET status = ? WHERE id_land = ? and id_user = ?";
	private static final String INSERT_INTO_ONE = "INSERT INTO likes(id_land, id_user, status) VALUES (?,?,?)";
	private static final String COUT_USER = "SELECT COUNT(id_user) FROM likes WHERE id_land = ?";

	private RowMapper<Like> getRowMapper() {
		return new RowMapper<Like>() {
			@Override
			public Like mapRow(ResultSet rs, int rowNum) throws SQLException {
				Like like = new Like(rs.getInt("id_like"),
						new Land(rs.getInt("lid"), rs.getString("lname"), rs.getString("description"),
								rs.getTimestamp("date_create"), null, rs.getString("picture"), rs.getInt("area"),
								rs.getString("address"), rs.getInt("count_views"), rs.getString("detail")),
						 null, rs.getInt("status"));
				return like;
			}
		};

	}

	public List<Like> getItems() {
		return jdbcTemplate.query(FIND_PAGINATION, getRowMapper());
	}

	public Like getItem(int landId, int userId) {
		try {
			return jdbcTemplate.queryForObject(FIND_PAGINATION_ONE, getRowMapper(), landId, userId);
		} catch (EmptyResultDataAccessException  e) {
			return new Like();
		}
	}

	public Like getCoutStatus(int id_user, int id_land) {
		return jdbcTemplate.queryForObject(FIND_PAGINATION, getRowMapper(), id_land, id_user);
	}

	public int getCoutUser(int id_land, int status) {
		return jdbcTemplate.queryForObject(COUT_USER_ID, Integer.class, id_land, status);
	}

	public int update(int status, int aIdLand, int idUser) {
		return jdbcTemplate.update(UPDATE_STATUS_BY_ID, status, aIdLand, idUser);
	}

	public void add(Like like) {
		jdbcTemplate.update(INSERT_INTO_ONE,like.getLand().getLid(),like.getUser().getId(),like.getStatus());
		
	}

	public int getCoutUser(int aIdLand) {
		return jdbcTemplate.queryForObject(COUT_USER, Integer.class, aIdLand);
		
	}



}
