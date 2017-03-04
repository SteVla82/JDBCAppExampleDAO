package gr.haec.db.dao;

public interface CRUDinterface<T> extends Dao<T> {

	T add();
	
	T update();
	
	T delete();
	
}