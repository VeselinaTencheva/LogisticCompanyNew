package com.example.demo.service;

import com.example.demo.entities.Company;
import com.example.demo.entities.Office;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	OfficeRepository officeRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Override
	public List<Office> findAllOffices() {
		return this.officeRepository.findAll();
	}

	@Override
	public Office findById(String id) {
		return this.officeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
	}

	@Override
	public Office createOffice(Office office) {
		Company company = this.companyRepository.findAll().get(0);
		office.setCompany(company);
		return this.officeRepository.saveAndFlush(office);
	}

	@Override
	public void deleteOffice(String id) {
		this.officeRepository.deleteById(id);
	}

	@Override
	public Office updateAddress(Office office, String address) {
		Office updatedOffice = this.findById(office.getId());
		updatedOffice.setAddress(address);
		return this.officeRepository.saveAndFlush(updatedOffice);
	}

    @Override
    public Office updateOffice(Office office) {
        return officeRepository.save(office);
    }

}
