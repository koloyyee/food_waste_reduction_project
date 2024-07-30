package cst8288.project.fwrp.service;

import java.sql.SQLException;
import java.util.Optional;

import cst8288.project.fwrp.dao.DBDao;
import cst8288.project.fwrp.dao.UserDaoImpl;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;
import cst8288.project.fwrp.utils.exception.NoSuchUserException;

/**
 * Process User Registration, Login.
 * */
public class UserService{
	private final static Logger log = LoggerFactory.getLogger();
	
	private UserDaoImpl dao = new UserDaoImpl();
	
	public void register(User user) throws SQLException {
		dao.save(user);
	}

	public User loadUserByEmail(String email) throws SQLException {
		return  dao.loadUserByEmail(email).orElse(null);
	}

	public  int update(User user) throws SQLException {
	    return dao.update(user.getId(), user);
	}

}
