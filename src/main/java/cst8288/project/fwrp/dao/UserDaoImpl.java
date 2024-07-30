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
import cst8288.project.fwrp.model.CommMethodType;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.model.UserType;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

public class UserDaoImpl implements DBDao<User, Long> {

	private final static Logger log = LoggerFactory.getLogger();

	private Connection conn = DBConnection.getInstance().getConnection();

	@Override
	public User save(User newUser) throws SQLException {
		String sql = """
				INSERT INTO user
				(name,email, password, phone, type, comm_method, location)
				VALUES (?, ?, ?, ?, ?, ? , ?);
				""";
		try (

				PreparedStatement stat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stat.setString(1, newUser.getName());
			stat.setString(2, newUser.getEmail());
			stat.setString(3, newUser.getPassword());
			stat.setString(4, newUser.getPhone());
			stat.setString(5, newUser.getType().name());
			stat.setInt(6, newUser.getCommMethod().code());
			stat.setString(7, newUser.getLocation());

			int rowAffected = stat.executeUpdate();

			assert (rowAffected > 0);
			if (rowAffected != 1) {
				throw new SQLException("Failed to regsiter new user: " + newUser.getEmail());
			}
			log.info("New user: %s registered.".formatted(newUser.getEmail()));

			User user = new User();
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setType(newUser.getType());
			user.setCommMethod(newUser.getCommMethod());
			user.setLocation(newUser.getLocation());

			try (ResultSet generatedKeys = stat.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getLong(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			return user;
		}

	}

	@Override
	public Optional<User> find(Long id) throws SQLException {
		String sql = """
				SELECT id, name, email, phone, type, comm_type, location
				FROM
				user
				WHERE
				id = ?
				""";

		try (

				PreparedStatement stat = conn.prepareStatement(sql);) {
			stat.setLong(1, id);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Long uid = Long.valueOf(rs.getInt(1));
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				UserType type = UserType.valueOf(rs.getString(4));
				CommMethodType commMethod = CommMethodType.valueOf(rs.getString(5));
				String location = rs.getString(6);

				User user = new User();
				user.setId(uid);
				user.setName(name);
				user.setEmail(email);
				user.setPhone(phone);
				user.setType(type);
				user.setCommMethod(commMethod);
				user.setLocation(location);

				return Optional.of(user);
			}
			rs.close();
		}
		return Optional.empty();
	}

	public Optional<User> loadUserByEmail(String reqEmail) throws SQLException {
		String sql = """
				SELECT id,name, email, password, type, phone, comm_method, location
				FROM
				user
				WHERE
				email = ?
				""";

		try (PreparedStatement stat = conn.prepareStatement(sql);) {
			stat.setString(1, reqEmail);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Long id = Long.valueOf(rs.getInt(1));
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				UserType type = UserType.valueOf(rs.getString(5));
				String phone = rs.getString(6);
				CommMethodType commMethod = CommMethodType.getByCode(rs.getInt(7)).orElse(null);
				String location = rs.getString(8);

				User user = new User();
				user.setId(id);
				user.setEmail(email);
				user.setPassword(password);
				user.setName(name);
				user.setType(type);
				user.setPhone(phone);
				user.setCommMethod(commMethod);
				user.setLocation(location);

				return Optional.of(user);

			}
			rs.close();
			return Optional.empty();
		}
	}

	@Override
	public List<User> findAll() throws SQLException {
		String sql = """
				SELECT id, name, email, phone, type, comm_method, location
				FROM
				users;
				""";

		try (PreparedStatement stat = conn.prepareStatement(sql);) {
			ResultSet rs = stat.executeQuery();

			List<User> users = new ArrayList<>();
			while (rs.next()) {
				Long uid = Long.valueOf(rs.getInt(1));
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				UserType type = UserType.valueOf(rs.getString(4));
				CommMethodType commMethod = CommMethodType.valueOf(rs.getString(5));
				String location = rs.getString(6);

				User user = new User();
				user.setId(uid);
				user.setName(name);
				user.setEmail(email);
				user.setPhone(phone);
				user.setType(type);
				user.setCommMethod(commMethod);
				user.setLocation(location);

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
				comm_method = ?
				location = ?
				WHERE
				id = ?
				""";
		try (Connection conn = DBConnection.getInstance().getConnection()) {

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
