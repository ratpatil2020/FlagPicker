package com.apple.bo;

public class CountryDTO {

	private String name;
	private String flag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Override
	public String toString() {
		return "CountryDTO [name=" + name + ", flag=" + flag + "]";
	}
}
