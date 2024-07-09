package dao;

import java.util.List;
import java.util.Optional;

/**
 * Basic DAO for persistent data.
 * */
public interface DBDao <CLASS, ID>{
	int save(CLASS object);
	Optional<CLASS> find(ID id);
	List<CLASS> findAll();
	int update(ID id );
	int delete(ID id );
}
