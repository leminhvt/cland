package spring.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spring.constant.Defines;
import spring.model.bean.Category;
import spring.model.bean.Land;
import spring.model.bean.Like;

@Repository
public class LandDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String FIND_ALL = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY l.lid DESC";
	private static final String INSERT_ONE = "INSERT INTO lands(lname, description, cid, picture, area, address, detail) VALUES (?,?,?,?,?,?,?)";
	private static final String FIND_ONE_BY_ID = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.lid = ?";
	private static final String UPDATE_ONE_BY_ID = "UPDATE lands SET lname = ?,description = ?,cid = ?,picture = ?,area = ?,address = ?,detail = ? WHERE lid = ?";
	private static final String DELETE_ONE_BY_ID = "DELETE FROM lands WHERE lid = ?";
	private static final String FIND_ALL_SEE = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY l.count_views DESC LIMIT 3";
	private static final String FIND_ALL_BY_CAT_ID = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.cid = ?";
	private static final String COUT_ITEM_BY_CAT_ID = "SELECT COUNT(*) FROM lands WHERE cid = ?";
	private static final String COUT_ITEMS = "SELECT COUNT(*) FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid";
	private static final String FIND_PAGINATION = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid ORDER BY date_create DESC LIMIT ?,?";
	private static final String FIND_BY_RELATED = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.cid = ? AND l.lid != ? ORDER BY date_create DESC LIMIT ?";
	private static final String FIND_ALL_SEARCH = "SELECT l.*,c.cname FROM lands AS l INNER JOIN categories AS c ON l.cid = c.cid WHERE l.lname LIKE ?";
	private static final String DELETE_ONE_BY_ID_CAT = "DELETE FROM lands WHERE cid = ?";
	private static final String COUT_ITEMS_VIEW = "UPDATE lands SET count_views= count_views + 1 WHERE lid = ?";
	private static final String FIND_COUNT_BY_ID = "SELECT count_views FROM lands WHERE lid = ?";

	private RowMapper<Land> getRowMapper() {
		return new RowMapper<Land>() {
			@Override
			public Land mapRow(ResultSet rs, int rowNum) throws SQLException {
				Land land = new Land(rs.getInt("lid"), rs.getString("lname"), rs.getString("description"),
						rs.getTimestamp("date_create"), new Category(rs.getInt("cid"), rs.getString("cname")),
						rs.getString("picture"), rs.getInt("area"), rs.getString("address"), rs.getInt("count_views"),
						rs.getString("detail"));

				return land;
			}
		};
	}

	private RowMapper<Land> getRowMapperSearch() {
		return new RowMapper<Land>() {
			@Override
			public Land mapRow(ResultSet rs, int rowNum) throws SQLException {
				Land land = new Land(rs.getInt("lid"), rs.getString("lname"), rs.getString("description"),
						rs.getTimestamp("date_create"), new Category(rs.getInt("cid"), null), rs.getString("picture"),
						rs.getInt("area"), rs.getString("address"), rs.getInt("count_views"), rs.getString("detail"));

				return land;
			}
		};
	}

	public List<Land> getItems() {
		return jdbcTemplate.query(FIND_ALL, getRowMapper());
	}

	public int add(Land land) {
		return jdbcTemplate.update(INSERT_ONE, land.getLname(), land.getDescription(), land.getCategory().getCid(),
				land.getPicture(), land.getArea(), land.getAddress(), land.getDetail());
	}

	public Land getItem(int id) {
		return jdbcTemplate.queryForObject(FIND_ONE_BY_ID, getRowMapper(), id);

	}

	public int edit(Land land) {
		return jdbcTemplate.update(UPDATE_ONE_BY_ID, land.getLname(), land.getDescription(),
				land.getCategory().getCid(), land.getPicture(), land.getArea(), land.getAddress(), land.getDetail(),
				land.getLid());
	}

	public int del(int id) {
		return jdbcTemplate.update(DELETE_ONE_BY_ID, id);
	}

	public List<Land> getItemsee() {
		return jdbcTemplate.query(FIND_ALL_SEE, getRowMapper());
	}

	public List<Land> getItemsById(int id) {
		return jdbcTemplate.query(FIND_ALL_BY_CAT_ID, getRowMapper(), id);
	}

	public int countLandByCatId(int id) {
		return jdbcTemplate.queryForObject(COUT_ITEM_BY_CAT_ID, Integer.class, id);
	}

	public int coutItems() {
		return jdbcTemplate.queryForObject(COUT_ITEMS, Integer.class);
	}

	public List<Land> getItemsPagination(int offset) {
		return jdbcTemplate.query(FIND_PAGINATION, getRowMapper(), offset, Defines.ROW_COUNT);

	}

	public List<Land> getRelatedItems(Land land, int i) {
		return jdbcTemplate.query(FIND_BY_RELATED, getRowMapper(), land.getCategory().getCid(), land.getLid(), i);

	}

	public ArrayList<Land> getItemSearch(String search) {
		try {
			return (ArrayList<Land>) jdbcTemplate.query(FIND_ALL_SEARCH, getRowMapper(), "%" + search + "%");
		} catch (TransientDataAccessResourceException e) {
			return new ArrayList<Land>();
		}

	}

	public void delCat(int id) {
		jdbcTemplate.update(DELETE_ONE_BY_ID_CAT, id);

	}

	public int countLand(int aIdLand) {
		return jdbcTemplate.update(COUT_ITEMS_VIEW, aIdLand);
	}

	public int getCount(int alandId) {
		return jdbcTemplate.queryForObject(FIND_COUNT_BY_ID, Integer.class, alandId);
	}

}
