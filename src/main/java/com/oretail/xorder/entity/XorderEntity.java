package com.oretail.xorder.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Entity
@Table(name="xorder_stg")
@JacksonXmlRootElement(localName = "order")
public class XorderEntity {
	
	@Id
	@Column(name="order_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "ordstg_seq",allocationSize=1)
	private int orderNo;
	
	@Column(name="supplier")
	@Positive(message="{NotNull.xorderEntity.supplier}")
	private int supplier;
	
	@OneToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.REMOVE})
	@JoinColumn(name="order_no")
	@JacksonXmlProperty(localName = "item")
	@JacksonXmlElementWrapper(localName = "items")
	private List<@Valid XorderItemEntity> items;
	
	public XorderEntity() {
		
	}

	public XorderEntity(int orderNo, int supplier) {
		this.orderNo = orderNo;
		this.supplier = supplier;
	}


	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}
	
	

	public List<XorderItemEntity> getItems() {
		return items;
	}

	public void setItems(List<XorderItemEntity> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "XorderEntity [orderNo=" + orderNo + ", supplier=" + supplier + ", items=" + items + "]";
	}

	
	
		
}











