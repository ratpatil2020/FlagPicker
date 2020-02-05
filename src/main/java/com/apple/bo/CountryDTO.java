package com.apple.bo;

import io.swagger.annotations.ApiModelProperty;

public class CountryDTO {

	@ApiModelProperty(notes = "Hold the country name")
	private String name;
	
	@ApiModelProperty(notes = "Hold the corresponding country flag.")
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
