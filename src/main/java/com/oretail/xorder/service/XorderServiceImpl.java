package com.oretail.xorder.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oretail.xorder.dao.XorderDao;
import com.oretail.xorder.entity.XorderEntity;

@Service
public class XorderServiceImpl implements XorderService {
	@Autowired
	private XorderDao xorderDao;
	
	private static final Logger LOGGER = LogManager.getRootLogger();

	@Override
	@Transactional
	public List<XorderEntity> findAll() {

		return xorderDao.findAll();
	}

	@Override
	@Transactional
	public XorderEntity findOrderbyId(int orderId) {

		return xorderDao.findById(orderId);
	}

	@Override
	@Transactional
	public List<XorderEntity> findOrdersbyItem(String itemId) {

		return xorderDao.findByItem(itemId);
	}

	@Override
	@Transactional
	public void createOrder(XorderEntity theOrder) {
		LOGGER.info("Order building starts with the details:"+theOrder);
		xorderDao.save(theOrder);
		LOGGER.info("Order saved to staging tables with order_no:"+theOrder.getOrderNo());
		LOGGER.info("Calling store proc for database operations");
		xorderDao.buildOrder(theOrder.getOrderNo());
	}

}
