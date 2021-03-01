package com.cognizant.springlearn;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Skill {
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private int id;
	@NotNull
	@Size(min = 1, max = 30, message = "Name should contain between 1 and 30 characters")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", name=" + name + "]";
	}

}
