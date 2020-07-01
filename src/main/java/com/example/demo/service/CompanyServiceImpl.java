package com.example.demo.service;

import com.example.demo.entities.Company;
import com.example.demo.entities.Office;
import com.example.demo.entities.Shipment;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.OfficeRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.util.CalculateShipmentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	@Autowired
	private OfficeRepository officeRepository;

	@Override
	public Company createCompany(Company company) {
		return this.companyRepository.save(company);
	}

	@Override
	public double calculateIncome(LocalDate startDate, LocalDate endDate){
		List<Shipment> allShipments = this.shipmentRepository.findAll();
		double income = 0.0;
		for(Shipment shipment : allShipments) {
			if(shipment.getReceivedDate().isBefore(endDate) && shipment.getReceivedDate().isAfter(startDate)) {
				Optional<Office> office = this.officeRepository.findByAddress(shipment.getAddress());
				if(!office.isPresent()) {
					income += 2;
				}
				income += CalculateShipmentUtil.calculateShipmentPrice(shipment,officeRepository.findAll());
			}
		}
		return income;
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(String id) {
		return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

}
