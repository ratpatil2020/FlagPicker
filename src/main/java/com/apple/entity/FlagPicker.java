package com.apple.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import io.swagger.annotations.ApiModelProperty;

@DynamicUpdate
@Entity
@Table(name = "FlagPickerCount")
public class FlagPicker implements Serializable {

	private static final long serialVersionUID = 3211336258674830563L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(notes = "The database generated FlagPicker ID")
	private Long id;
	
	@Column(nullable = false,unique = true)
	@ApiModelProperty(notes = "Valid value type=[\"continent\",\"country\"].")
	private String type;
	
	@Column(nullable = false,unique = false)
	@ApiModelProperty(notes = "API search count by type=[\"continent\",\"country\"]")
	private Double count;
	
	public Long getId() {
		return id;
	}
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
}
