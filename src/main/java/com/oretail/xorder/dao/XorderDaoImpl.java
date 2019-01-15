package com.oretail.xorder.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oretail.xorder.entity.XorderEntity;

@Repository
public class XorderDaoImpl implements XorderDao {

	// define field for entitymanager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public XorderDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<XorderEntity> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<XorderEntity> theQuery = currentSession.createQuery("from XorderEntity", XorderEntity.class);

		// execute query and get result list
		List<XorderEntity> orders = theQuery.getResultList();

		// return the results
		return orders;
	}

	@Override
	public XorderEntity findById(int orderId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		XorderEntity theOrder = currentSession.get(XorderEntity.class, orderId);

		// return the results
		return theOrder;
	}

	@Override
	public List<XorderEntity> findByItem(String itemId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<XorderEntity> theQuery = currentSession.createQuery(
				"select o " + "from XorderEntity o " + "inner join fetch o.items " + "where item = :itemId",
				XorderEntity.class).setParameter("itemId", itemId);

		// execute query and get result list
		List<XorderEntity> orders = theQuery.getResultList();

		// return the results
		return orders;

	}

	@Override
	public void save(XorderEntity theOrder) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save employee
		currentSession.saveOrUpdate(theOrder);

	}
	
	@Override
	public void buildOrder(int orderId) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("xorder_pkg.build_order")
				.registerStoredProcedureParameter(1, int.class, ParameterMode.IN)
				.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT).setParameter(1,orderId);

		query.execute();

		String errorMsg = (String) query.getOutputParameterValue(2);
		
		if(errorMsg!=null)
			throw new RuntimeException(errorMsg);
	}

}
