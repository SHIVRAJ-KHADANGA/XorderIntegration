package com.oretail.xorder.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oretail.xorder.constants.XorderConstants;
import com.oretail.xorder.entity.XorderEntity;
import com.oretail.xorder.response.XorderCustomResponse;
import com.oretail.xorder.rest.exeception.XorderCustomException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/async/xorder")
public class XorderRestAsyncController {
    
	@Autowired
	JmsTemplate jmsTemplate;
	
	@PostMapping("/orders")
	@ApiOperation(value = "Async Order Build", response = ResponseEntity.class,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<XorderCustomResponse> createOrder(@Valid @RequestBody XorderEntity theOrder) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		if(theOrder.getItems()==null)
			throw new XorderCustomException("Order has no items");

		theOrder.setOrderNo(0);
		jmsTemplate.convertAndSend(XorderConstants.destinationQueue, theOrder);
		XorderCustomResponse response = new XorderCustomResponse(HttpStatus.ACCEPTED.value(),
				"Request is being processed asynchronously",
				System.currentTimeMillis());
		return  new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
}
