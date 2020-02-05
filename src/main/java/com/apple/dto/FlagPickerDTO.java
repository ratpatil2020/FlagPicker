package com.apple.dto;

public class FlagPickerDTO {

	private String type;	
	private Double count;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "FlagPickerDTO [type=" + type + ", count=" + count + "]";
	}
}
