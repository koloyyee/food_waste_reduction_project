package cst8288.project.fwrp.dao;

import cst8288.project.fwrp.dao.DBDao;
import cst8288.project.fwrp.db.DBConnection;
import cst8288.project.fwrp.model.*;
import cst8288.project.fwrp.model.Observer;
import cst8288.project.fwrp.utils.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.*;

/**
 * A User (Consumer / Charitable Organization) subscribe to an item, when the
 * item is marked as surplus, the Consumer will be notified. when the item is
 * marked as donation, the Charitable Organization will be notified.
 */
public class SubscriptionDao {
	private Logger log = Logger.getLogger();
	private Connection conn;

	public SubscriptionDao() {
		this.conn = DBConnection.getInstance().getConnection();
	}

	public int save(Long itemId, Long userId) throws SQLException {

		String sql = """
				INSERT INTO subscription
				(item_id, user_id)
				VALUES (?, ?)
				""";
		try (PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stat.setLong(1, itemId);
			stat.setLong(2, userId);
			stat.executeUpdate();
			log.info("Subscription saved: " + itemId + " " + userId);
			try (ResultSet generatedKeys = stat.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				} else {
					log.warn("Subscription not saved: " + itemId + " " + userId);
					return 0;
				}
			}
		}
	}

//	public SubscribedItem save(SubscribedItem object) throws SQLException {
//		return null;
//	}

	public Optional<SubscribedItem> find(Long itemId) throws SQLException {
		String sql = """
				SELECT
				i.id as item_id,
				i.name as item_name,
				i.description as item_description,
				i.expiry_date as item_expiry_date,
				i.price as item_price,
				i.discount_rate as item_discount_rate,
				i.is_surplus as item_is_surplus,
				i.is_donation as item_is_donation,
				i.quantity as item_quantity,
				i.is_available as item_is_available,
				i.created_at as item_created_at,
				i.updated_at as item_updated_at,

				u.id as user_id,
				u.name as user_name,
				u.email as user_email,
				u.phone as user_phone,
				u.password as user_password,
				u.type as user_type,
				u.comm_method as user_comm_method,
				u.location as user_location,

				s.subscription_date as subscription_date

				FROM
				subscription s
				JOIN item i ON s.item_id = i.id
				JOIN user u ON s.user_id = u.id
				WHERE item_id = ?
				""";

		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setLong(1, itemId);
			var rs = stat.executeQuery();

			Set<Observer> userSet = new HashSet<>();
			Item item = new Item();

			while (rs.next()) {
				item.setId(rs.getLong("item_id"));
				item.setName(rs.getString("item_name"));
				item.setDescription(rs.getString("item_description"));
				item.setExpiryDate(rs.getDate("item_expiry_date").toLocalDate());
				item.setPrice(BigDecimal.valueOf(rs.getDouble("item_price")));
				item.setDiscountRate(rs.getDouble("item_discount_rate"));
				item.setSurplus(rs.getBoolean("item_is_surplus"));
				item.setDonation(rs.getBoolean("item_is_donation"));
				item.setQuantity(rs.getInt("item_quantity"));
				item.setAvailable(rs.getBoolean("item_is_available"));
				item.setCreatedAt(rs.getTimestamp("item_created_at").toLocalDateTime());
				item.setUpdatedAt(rs.getTimestamp("item_updated_at").toLocalDateTime());

				User user = new User();
				user.setId(rs.getLong("user_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("user_email"));
				user.setPhone(rs.getString("user_phone"));
				user.setType(UserType.valueOf(rs.getString("user_type")));
				var commMethod = CommMethodType.getByCode(Integer.parseInt(rs.getString("user_comm_method")));
				if (commMethod.isPresent()) {
					user.setCommMethod(commMethod.get());
				}
				user.setLocation(rs.getString("user_location"));

				LocalDateTime subscriptionDate = rs.getTimestamp("subscription_date").toLocalDateTime();

				userSet.add(new Subscriber(user, subscriptionDate));
			}

			if (item.getName() == null) {
				return Optional.empty();
			} else {
				SubscribedItem subscribedItem = new SubscribedItem(item);
				subscribedItem.addSubscribers(userSet.stream().toList());
				log.info("SubscribedItem found: " + subscribedItem);
				return Optional.of(subscribedItem);
			}
		}
	}

	public List<SubscribedItem> findAll() throws SQLException {
		return List.of();
	}


	public List<Item> findUserSubscribed(Long id) throws SQLException {
		String sql = """
				   SELECT
				  	*
				   FROM subscription s
				  	JOIN item i ON s.item_id = i.id
				   Where s.user_id = ?
				""";
		try (var stat = conn.prepareStatement(sql)) {
			stat.setLong(1, id);
			var rs = stat.executeQuery();

			List<Item> items = new ArrayList<>();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getLong("id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				item.setExpiryDate(rs.getDate("expiry_date").toLocalDate());
				item.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
				item.setDiscountRate(rs.getDouble("discount_rate"));
				item.setSurplus(rs.getBoolean("is_surplus"));
				item.setDonation(rs.getBoolean("is_donation"));
				item.setQuantity(rs.getInt("quantity"));
				item.setAvailable(rs.getBoolean("is_available"));
				item.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				item.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
				items.add(item);
			}
			return items;
		}
	}

	public int delete(Long itemId, Long userId) throws SQLException {
		String sql = """
				DELETE FROM subscription
				WHERE item_id = ? AND user_id = ?
				""";
		try (PreparedStatement stat = conn.prepareStatement(sql)) {
			stat.setLong(1, itemId);
			stat.setLong(2, userId);
			int result = stat.executeUpdate();
			return result;
		}
	}
}
