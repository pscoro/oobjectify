package com.oobjectify.oobjects;

import com.oobjectify.oobjects.OObject;

public class Ore extends OObject {
	
	private String type = "";
	private double purity;
	
	private double getPurity() {
		return purity;
	}
	
	private void setPurity(double purity) {
		this.purity = purity;
	}
	
	public Ore(String type, int purity) {
		this.type = type;
		
		this.purity = purity/100.0;
		
		System.out.println("constructor test");
		
		setName("ore_" + type);
		
	}
	
	private String getType() {
		return type;
	}
	
	private void setType(String type) {
		this.type = type;
	}
	
}