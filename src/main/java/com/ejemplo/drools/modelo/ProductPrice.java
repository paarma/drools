package com.ejemplo.drools.modelo;

public class ProductPrice {

	private Integer basePrice;

	public ProductPrice() {
		
	}
	
	public ProductPrice(Integer basePrice) {
		this.basePrice = basePrice;
	}

	
	public Integer getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}
	
	@Override
	public String toString() {
		return "Producto con precio " + this.getBasePrice();
	}
	
}
