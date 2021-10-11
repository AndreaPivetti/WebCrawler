package WebCrawler;

import java.sql.SQLException;
import java.util.*;

public interface Dao<T> {	//Data Access Object
	
	T get(int id) throws SQLException;
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
	
}
