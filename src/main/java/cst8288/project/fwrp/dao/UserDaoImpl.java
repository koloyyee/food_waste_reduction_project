package cst8288.project.fwrp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.db.DBConnection;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

public class UserDaoImpl implements DBDao<User, Long> {

	private final static Logger logger = LoggerFactory.getLogger();

	@Override
	public User save(User user) throws SQLException {
		String sql = """
				INSERT INTO user
				(name,email, password, phone, type)
				VALUES (?, ?, ?, ?, ?);
				""";
		try (Connection conn = DBConnection.getInstance().getConnection()) {
			PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stat.setString(1, user.getName());
			stat.setString(2, user.getEmail());
			stat.setString(3, user.getPassword());
			stat.setString(4, user.getPhone());
			stat.setInt(5, user.getType().code());

			
			int rowAffected = stat.executeUpdate();

			assert (rowAffected > 0);
			if (rowAffected != 1) {
				throw new SQLException("Failed to regsiter new user: " + user.getEmail());
			}
			logger.info("New user: %s registered.".formatted(user.getEmail()));
			
			User.Builder builder = new User.Builder(user.getEmail(), user.getPassword());
			builder.setName(user.getName()).setPhone(user.getPhone()).setUserType(user.getType());

			
		      try (ResultSet generatedKeys = stat.getGeneratedKeys()) {
		        if (generatedKeys.next()) {
		          builder.setId(generatedKeys.getLong(1));
		        } else {
		          throw new SQLException("Creating user failed, no ID obtained.");
		        }
		      }
			
			return  builder.build();

		}

	}

	@Override
	public Optional<User> find(Long id) throws SQLException {
		String sql = """
				SELECT id, name, email, phone, type
				FROM
				user
				WHERE
				id = ?
				""";

		try (Connection conn = DBConnection.getInstance().getConnection()) {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Long uid = Long.valueOf(rs.getInt(1));
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				UserType type = UserType.getByCode(rs.getInt(4));

				User.Builder builder = new User.Builder(email, "");
				return Optional.of(builder.setId(uid).setName(name).setPhone(phone).setUserType(type).build());

			}
		}
		return Optional.empty();
	}

	@Override
	public List<User> findAll() throws SQLException {
		String sql = """
				SELECT id, name, email, phone, type
				FROM
				users;
				""";

		try (Connection conn = DBConnection.getInstance().getConnection()) {
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			
			List<User> users = new ArrayList<>();
			while (rs.next()) {
				Long uid = Long.valueOf(rs.getInt(1));
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				UserType type = UserType.getByCode(rs.getInt(4));

				User.Builder builder = new User.Builder(email, "");
				User user = builder.setId(uid).setName(name).setPhone(phone).setUserType(type).build();
				users.add(user);
			}

			return users;
		}
	}

	@Override
	public int update(Long id, User user) throws SQLException {
		String sql = """
				UPDATE users
				SET
				name = ?,
				email = ?,
				phone = ?
				type = ?
				""";
		try(Connection conn = DBConnection.getInstance().getConnection()) {

			PreparedStatement stat = conn.prepareStatement(sql);

		}
		
		return -1;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
