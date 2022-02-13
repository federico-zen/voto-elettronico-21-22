package votoelettronico.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	public T get(String id);
	List<T> getAll();
    void save(T t);    
    void update(T t, String[] params);
    void delete(T t);

}
