package com.oretail.xorder.service;

import java.util.List;


import com.oretail.xorder.entity.XorderEntity;

public interface XorderService {
	
	public List<XorderEntity> findAll();
	
	public XorderEntity findOrderbyId(int orderId);
	
	public List<XorderEntity> findOrdersbyItem(String itemId);
	
	public void createOrder(XorderEntity theOrder);
	
	

}
