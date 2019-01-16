package com.oretail.xorder.rest;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/xorder")
public class XorderRestController {

   @Autowired
   private XorderService xorderService;


	@GetMapping("/orders")
	public List<XorderEntity> findAll() {
		return xorderService.findAll();
	}

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

	

	@PostMapping("/orders")
	public XorderEntity createOrder(@Valid @RequestBody XorderEntity theOrder) {
		
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
