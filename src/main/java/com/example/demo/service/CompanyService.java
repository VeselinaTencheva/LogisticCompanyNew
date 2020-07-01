package com.example.demo.service;

import com.example.demo.entities.Company;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {
	Company createCompany(Company company);
	double calculateIncome(LocalDate startDate, LocalDate endDate);
	List<Company> findAll();
	Company findById(String id);
}
