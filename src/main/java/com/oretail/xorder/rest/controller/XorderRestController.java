package com.oretail.xorder.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oretail.xorder.entity.XorderEntity;
import com.oretail.xorder.rest.exeception.XorderCustomException;
import com.oretail.xorder.service.XorderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/xorder")
public class XorderRestController {

   @Autowired
   private XorderService xorderService;


	@GetMapping("/orders")
	@ApiOperation(value = "Get All The Orders",response = XorderEntity.class, responseContainer="List",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<XorderEntity> findAll(@RequestHeader HttpHeaders httpHeaders) {
		return xorderService.findAll();
	}

	@GetMapping(value= {"order","/order/{orderId}"})
	@ApiOperation(value = "Get An Order By OrderId - DEF ORD 1083", response = XorderEntity.class,produces = MediaType.APPLICATION_JSON_VALUE)
	public XorderEntity findOrderbyId(@PathVariable Optional<Integer> orderId,@RequestHeader HttpHeaders httpHeaders) {
		XorderEntity theOrder;
		if(orderId.isPresent()) {
			 theOrder = xorderService.findOrderbyId(orderId.get());
			
		}else {
			 theOrder = xorderService.findOrderbyId(1083);
		}

		if (theOrder == null) {
			throw new XorderCustomException("Order id not found - " + orderId);
		}

		return theOrder;
	}

	@GetMapping("/orders/item/{itemId}")
	@ApiOperation(value = "Get All Orders By ItemId", response = XorderEntity.class, responseContainer="List",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<XorderEntity> findOrdersbyItem(@PathVariable String itemId,@RequestHeader HttpHeaders httpHeaders) {

		return xorderService.findOrdersbyItem(itemId);
	}

	

	@PostMapping("/orders")
	@ApiOperation(value = "Sync Order Build", response = XorderEntity.class,consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public XorderEntity createOrder(@Valid @RequestBody XorderEntity theOrder,@RequestHeader HttpHeaders httpHeaders) {
		
		if(theOrder.getItems()==null)
			throw new XorderCustomException("Order has no items");

		theOrder.setOrderNo(0);

		xorderService.createOrder(theOrder);

		return theOrder;
	}
	
	@PutMapping("/orders")
	@ApiOperation(value = "Sync Order Build By PUT", response = XorderEntity.class,consumes = MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public XorderEntity updateOrder(@RequestBody XorderEntity theOrder,@RequestHeader HttpHeaders httpHeaders) {

		xorderService.createOrder(theOrder);

		return theOrder;
	}

}
