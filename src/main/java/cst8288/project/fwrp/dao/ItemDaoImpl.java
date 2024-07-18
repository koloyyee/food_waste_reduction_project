package cst8288.project.fwrp.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.db.DBConnection;
import cst8288.project.fwrp.model.Item;

public class ItemDaoImpl implements DBDao<Item, Long> {

	private Connection connection;

	public ItemDaoImpl() {
		this.connection = DBConnection.getInstance().getConnection();
	}

	@Override
	public Item save(Item object) throws SQLException {
		return null;
	}

	@Override
	public Optional<Item> find(Long id) throws SQLException {
		String sql = """
				SELECT * FROM item
				WHERE
				id = ?
				""";
		try (var statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			var result = statement.executeQuery();
			if (result.next()) {
				Item item = new Item();
				item.setId(result.getLong("id"));
				item.setName(result.getString("name"));
				item.setDescription(result.getString("description"));
				item.setExpiryDate(result.getDate("expiry_date").toLocalDate());
				item.setPrice(result.getBigDecimal("price"));
				item.setDiscountRate(result.getDouble("discount_rate"));
				item.setSurplus(result.getBoolean("is_surplus"));
				item.setDonation(result.getBoolean("is_donation"));
				item.setQuantity(result.getInt("quantity"));
				item.setAvailable(result.getBoolean("is_available"));
				item.setCreatedAt(result.getTimestamp("created_at").toLocalDateTime());
				item.setUpdatedAt(result.getTimestamp("updated_at").toLocalDateTime());
				return Optional.of(item);
			}
		}
		return Optional.empty();
	}

	@Override
	public List<Item> findAll() throws SQLException {

		String sql = """
				SELECT * FROM item
				WHERE
				id = ?
				is_donation = false AND
				is_available = true AND
				quantity > 0
				""";
		try (var statement = connection.prepareStatement(sql)) {
			var result = statement.executeQuery();
			while (result.next()) {
				Item item = new Item();
				item.setId(result.getLong("id"));
				item.setName(result.getString("name"));
				item.setDescription(result.getString("description"));
				item.setExpiryDate(result.getDate("expiry_date").toLocalDate());
				item.setPrice(result.getBigDecimal("price"));
				item.setDiscountRate(result.getDouble("discount_rate"));
				item.setSurplus(result.getBoolean("is_surplus"));
				item.setDonation(result.getBoolean("is_donation"));
				item.setQuantity(result.getInt("quantity"));
				item.setAvailable(result.getBoolean("is_available"));
				item.setCreatedAt(result.getTimestamp("created_at").toLocalDateTime());
				item.setUpdatedAt(result.getTimestamp("updated_at").toLocalDateTime());
				return List.of(item);
			}
		}
		return List.of();
	}
	
	/**
	 * Update will handle update item in general, including price, discount rate.
	 * */
	@Override
	public int update(Long id, Item object) throws SQLException {
		String sql = """
				UPDATE Items
				SET
				name = ?,
				description = ?,
				expiry_date = ?,
				price = ?,
				discount_rate = ?,
				is_surplus = ?,
				is_donation = ?,
				quantity = ?,
				is_available = ?
				WHERE
				id = ?
						""";
		try (var statement = connection.prepareStatement(sql)) {
			statement.setString(1, object.getName());
			statement.setString(2, object.getDescription());
			statement.setDate(3, java.sql.Date.valueOf(object.getExpiryDate()));
			statement.setBigDecimal(4, object.getPrice());
			statement.setDouble(5, object.getDiscountRate());
			statement.setBoolean(6, object.isSurplus());
			statement.setBoolean(7, object.isDonation());
			statement.setInt(8, object.getQuantity());
			statement.setBoolean(9, object.isAvailable());
			statement.setLong(10, id);
			return statement.executeUpdate();
		}
	}
	
	/**
	 * updateQuantity will handle update quantity only.
	 * */
	public int updateQuantity(Long id, int newQuant) {
		String sql = """
				UPDATE Items
				SET
				quantity = ?
				WHERE
				id = ?
				""";
		try (var statement = connection.prepareStatement(sql)) {
			statement.setInt(1, newQuant);
			statement.setLong(2, id);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * findSurplus will used to by both Retailer and Consumer to find surplus items.
	 * */
	public List<Item> findSurplus() throws SQLException {
		String sql = """
				SELECT * FROM item
				WHERE
				is_surplus = true AND
				is_available = true AND
				quantity > 0
				""";
		try (var statement = connection.prepareStatement(sql)) {
			var result = statement.executeQuery();
			while (result.next()) {
				Item item = new Item();
				item.setId(result.getLong("id"));
				item.setName(result.getString("name"));
				item.setDescription(result.getString("description"));
				item.setExpiryDate(result.getDate("expiry_date").toLocalDate());
				item.setPrice(result.getBigDecimal("price"));
				item.setDiscountRate(result.getDouble("discount_rate"));
				item.setSurplus(result.getBoolean("is_surplus"));
				item.setDonation(result.getBoolean("is_donation"));
				item.setQuantity(result.getInt("quantity"));
				item.setAvailable(result.getBoolean("is_available"));
				item.setCreatedAt(result.getTimestamp("created_at").toLocalDateTime());
				item.setUpdatedAt(result.getTimestamp("updated_at").toLocalDateTime());
				return List.of(item);
			}
		}
		return List.of();
	}
	
	/**
	 * findDonations will used to by both Retailer and Charitable Organization to find donation items.
	 * */
	public List<Item> findDonations() throws SQLException {
		String sql = """
				SELECT * FROM item
				WHERE
				is_donation = true,
				is_available = true,
				quantity > 0
				""";
		try (var statement = connection.prepareStatement(sql)) {
			var result = statement.executeQuery();
			while (result.next()) {
				Item item = new Item();
				item.setId(result.getLong("id"));
				item.setName(result.getString("name"));
				item.setDescription(result.getString("description"));
				item.setExpiryDate(result.getDate("expiry_date").toLocalDate());
				item.setPrice(result.getBigDecimal("price"));
				item.setDiscountRate(result.getDouble("discount_rate"));
				item.setSurplus(result.getBoolean("is_surplus"));
				item.setDonation(result.getBoolean("is_donation"));
				item.setQuantity(result.getInt("quantity"));
				item.setAvailable(result.getBoolean("is_available"));
				item.setCreatedAt(result.getTimestamp("created_at").toLocalDateTime());
				item.setUpdatedAt(result.getTimestamp("updated_at").toLocalDateTime());
				return List.of(item);
			}
		}
		return List.of();
	}
	

	/**
	 * This is a soft delete, it will set is_available to false.
	 * */
	@Override
	public int delete(Long id) throws SQLException {
		String sql = """
				UPDATE Items
				SET
				is_available = false
				WHERE
				id = ?
				""";
		try (var statement = connection.prepareStatement(sql)) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		}
	}

}
