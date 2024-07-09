package cst8288.project.fwrp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.db.DBConnection;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

public class UserDaoImpl implements DBDao<User, Long>{
	
	private final static Logger logger = LoggerFactory.getLogger();

	@Override
	public int save(User user) throws SQLException {
		String sql = """ 
				INSERT INTO user 
				(name,email, password, phone, type) 
				VALUES (?, ?, ?, ?, ?);
				""";
		try (Connection conn =  DBConnection.getInstance().getConnection()){
			PreparedStatement stat =   conn.prepareStatement(sql);
			stat.setString(1, user.getName());
			stat.setString(2, user.getEmail());
			stat.setString(3, user.getPassword());
			stat.setString(4, user.getPhone());
			stat.setInt(5, user.getType().code());
			
			int rowAffected = stat.executeUpdate();

			assert(rowAffected > 0);
			if(rowAffected != 1) {
				throw new SQLException("Failed to regsiter new user: " + user.getEmail());
			}
			logger.info("New user: %s registered.".formatted(user.getEmail()));
			return rowAffected;
			
		} 		
		
	}

	@Override
	public Optional<User> find(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
