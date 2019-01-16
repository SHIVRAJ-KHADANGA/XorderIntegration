package com.oretail.xorder.jms;

import javax.jms.JMSException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.oretail.xorder.constants.XorderConstants;
import com.oretail.xorder.entity.XorderEntity;
import com.oretail.xorder.service.XorderService;

@Component
public class XorderJmsAMQListener {
	
	private static final Logger LOGGER = LogManager.getRootLogger();
	
	@Autowired
	XorderService xorderService;
		
	@JmsListener(destination = XorderConstants.destinationQueue)
	public void receiveMessage(XorderEntity theOrder) throws JMSException {
		LOGGER.info("Xorder create message received by JMS listener : "+theOrder);
		xorderService.createOrder(theOrder);
		
	}

}
