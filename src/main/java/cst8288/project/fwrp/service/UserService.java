package cst8288.project.fwrp.service;

import java.sql.SQLException;

import cst8288.project.fwrp.dao.DBDao;
import cst8288.project.fwrp.dao.UserDaoImpl;
import cst8288.project.fwrp.model.User;
import cst8288.project.fwrp.utils.Logger;
import cst8288.project.fwrp.utils.LoggerFactory;

/**
 * Process User Registration, Login.
 * */
public class UserService{
	private final static Logger logger = LoggerFactory.getLogger();
	
	private UserDaoImpl dao = new UserDaoImpl();
	
	public void register(User user) throws SQLException {
		dao.save(user);
	}

}
