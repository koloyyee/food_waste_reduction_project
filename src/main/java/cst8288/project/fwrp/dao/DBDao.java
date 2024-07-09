package cst8288.project.fwrp.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Basic DAO for persistent data.
 * */
public interface DBDao <CLASS, ID>{
	int save(CLASS object) throws SQLException;
	Optional<CLASS> find(ID id) throws SQLException;
	List<CLASS> findAll() throws SQLException;
	int update(ID id ) throws SQLException;
	int delete(ID id ) throws SQLException;
}
