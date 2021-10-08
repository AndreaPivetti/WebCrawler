package WebCrawler;

import java.util.*;

public interface Dao<T> {	//Data Access Object
	
	T get(int id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
	
}
