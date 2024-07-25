package cst8288.project.fwrp.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import cst8288.project.fwrp.db.DBConnection;
import cst8288.project.fwrp.model.Notification;

/**
 * Insert to Notification table <Br>
 * insert only
 * 
 * */
public class NotificationDao{

	public int save(Notification object) throws SQLException {
		String sql = """
				INSERT INTO notification
				(user_id, item_id, comm_method, sent_time, message)
				VALUES
				(?, ?, ?, ?, ?)
				""";
		try (Connection conn = DBConnection.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setLong(1, object.getUser().getId());
			statement.setLong(2, object.getItem().getId());
			statement.setString(3, object.getCommMethod().name());
			statement.setTimestamp(4, Timestamp.valueOf(object.getSentTime()));
			statement.setString(5, object.getMessage());
			return statement.executeUpdate();
		}
	}

}
