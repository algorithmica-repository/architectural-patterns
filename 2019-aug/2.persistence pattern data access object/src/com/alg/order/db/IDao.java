package com.alg.order.db;

import java.util.List;

public interface IDao<E extends Entity> {

	E find(Integer id) throws Exception;

	void save(E entity) throws Exception;

	boolean delete(Integer id) throws Exception;
	
	boolean update(E entity) throws Exception;

	List<E> findAll() throws Exception;
}
