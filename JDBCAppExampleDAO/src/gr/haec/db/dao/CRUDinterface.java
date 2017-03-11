package gr.haec.db.dao;

public interface CRUDinterface<T> extends Dao<T> {

	T add(T dto);
	
	T update(T dto);
	
	T delete(T dto);
	
}