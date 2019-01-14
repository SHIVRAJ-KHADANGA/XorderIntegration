package com.oretail.xorder.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oretail.xorder.entity.XorderEntity;
import com.oretail.xorder.rest.exeception.XorderCustomException;
import com.oretail.xorder.service.XorderService;

@RestController
@RequestMapping("/api")
public class XorderRestController {

   @Autowired
   private XorderService xorderService;
   
   private static final Logger LOGGER = LogManager.getRootLogger();

	@GetMapping("/orders")
	public List<XorderEntity> findAll() {
		return xorderService.findAll();
	}

	// add mapping for GET /employees/{employeeId}

	@GetMapping("/orders/{orderId}")
	public XorderEntity findOrderbyId(@PathVariable int orderId) {

		XorderEntity theOrder = xorderService.findOrderbyId(orderId);

		if (theOrder == null) {
			throw new XorderCustomException("Order id not found - " + orderId);
		}

		return theOrder;
	}

	@GetMapping("/orders/item/{itemId}")
	public List<XorderEntity> findOrdersbyItem(@PathVariable String itemId) {

		return xorderService.findOrdersbyItem(itemId);
	}

	// add mapping for POST /employees - add new employee

	@PostMapping("/orders")
	public XorderEntity createOrder(@Valid @RequestBody XorderEntity theOrder) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		if(theOrder.getItems()==null)
			throw new XorderCustomException("Order has no items");

		theOrder.setOrderNo(0);

		xorderService.createOrder(theOrder);

		return theOrder;
	}
	
	@PutMapping("/orders")
	public XorderEntity updateOrder(@RequestBody XorderEntity theOrder) {

		xorderService.createOrder(theOrder);

		return theOrder;
	}

}
