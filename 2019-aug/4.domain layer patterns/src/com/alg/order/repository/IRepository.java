package com.alg.order.repository;

import java.util.List;

import com.alg.order.common.Entity;
import com.alg.order.repository.specifications.ISqlSpecification;

public interface IRepository<E extends Entity> {

	List<E> query(ISqlSpecification specification) throws Exception;

	void save(E entity) throws Exception;

	boolean delete(E entity) throws Exception;
	
	boolean update(E entity) throws Exception;

	List<E> findAll() throws Exception;
}
