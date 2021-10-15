package dao;

import java.sql.SQLException;
import java.util.*;

import models.Downloads;

public interface Dao<T> {	//Data Access Object
	
	T get(int id) throws SQLException;
    
    List<T> getAll();
    
    void save(T t) throws SQLException;
    
    void update(T t, String[] params) throws SQLException;
    
    void delete(T t);
	
}
