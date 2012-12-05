package net.atos.camel.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvRecord(separator = "#", crlf = "WINDOWS")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	@DataField(pos = 1)	
	private long id;
	
	@XmlAttribute
	@DataField(pos = 2)	
	private String partName;
	
	@XmlAttribute
	@DataField(pos = 3)
	private int amount;

	@XmlAttribute
	@DataField(pos = 4)
	private String customerName;

	
	public Order() {
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



}