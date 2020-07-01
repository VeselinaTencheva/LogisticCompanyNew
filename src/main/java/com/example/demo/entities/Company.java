package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Company extends BaseEntity {
	private String name;
	private List<Office> offices;

	public Company() {
	}

	@Column(name = "company_name", nullable = false, unique = true, updatable = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((offices == null) ? 0 : offices.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offices == null) {
			if (other.offices != null)
				return false;
		} else if (!offices.equals(other.offices))
			return false;
		return true;
	}

}
