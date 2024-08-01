package cst8288.project.fwrp.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.db.DBConnection;
import cst8288.project.fwrp.model.CommMethodType;
import cst8288.project.fwrp.model.Notification;

/**
 * Insert to Notification table <Br>
 * insert only
 * 
 * */
public class NotificationDao{

	public int save(Long userId, Long itemId, CommMethodType type, String message ) throws SQLException {
		String sql = """
				INSERT INTO notification
				(user_id, item_id, comm_method,  message)
				VALUES
				(?, ?, ?, ? )
				""";
		try (Connection conn = DBConnection.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setLong(1, userId);
			statement.setLong(2, itemId);
			statement.setInt(3, type.code());
			statement.setString(4, message);
			return statement.executeUpdate();
		}
	}

}
