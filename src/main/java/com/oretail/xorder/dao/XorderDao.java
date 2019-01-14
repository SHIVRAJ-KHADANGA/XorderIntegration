package com.oretail.xorder.dao;

import java.util.List;

import com.oretail.xorder.entity.XorderEntity;

public interface XorderDao {

	public List<XorderEntity> findAll();

	public XorderEntity findById(int orderId);
	
	public List<XorderEntity> findByItem(String itemId);

	public void save(XorderEntity theOrder);
	
	public void buildOrder(int orderId);
}
