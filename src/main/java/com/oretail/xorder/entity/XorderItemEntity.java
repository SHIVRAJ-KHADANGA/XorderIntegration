package com.oretail.xorder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


@Entity
@Table(name = "xorder_item_stg")
@JacksonXmlRootElement(localName = "item")
public class XorderItemEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderitem_seq")
    @SequenceGenerator(name = "orderitem_seq", sequenceName = "ORDSTGITEM_SEQ",allocationSize=1)
	private int id;

	@Column(name = "item")
	@JacksonXmlProperty(localName = "item_id")
	private String item;

	@Column(name = "qty")
	@JacksonXmlProperty(localName = "units")
	@Positive(message="{Positive.xorderItemEntity.qty}")
	private int qty;
    

	public XorderItemEntity() {
   
	}

	public XorderItemEntity(String item, int qty) {
		this.item = item;
		this.qty = qty;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
   
	@Override
	public String toString() {
		return "XorderItem [id=" + id + ", item=" + item + ", qty=" + qty + "]";
	}
	
	

}
