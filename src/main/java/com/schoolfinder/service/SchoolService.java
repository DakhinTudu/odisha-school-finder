package com.schoolfinder.service;

import com.schoolfinder.entity.School;
import com.schoolfinder.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public List<School> getAllSchools() {
		return schoolRepository.findAll();
	}

	public Optional<School> getSchoolById(Long id) {
		return schoolRepository.findById(id);
	}

	public List<School> searchSchools(String name, String city, String board, String type) {
		return schoolRepository.searchSchools(name, city, board, type);
	}

	public School addSchool(School school) {
		return schoolRepository.save(school);
	}

	public School updateSchool(Long id, School schoolDetails) {
		Optional<School> optionalSchool = schoolRepository.findById(id);
		if (optionalSchool.isPresent()) {
			School school = optionalSchool.get();
			school.setName(schoolDetails.getName());
			school.setCity(schoolDetails.getCity());
			school.setDistrict(schoolDetails.getDistrict());
			school.setBoard(schoolDetails.getBoard());
			school.setType(schoolDetails.getType());
			school.setAddress(schoolDetails.getAddress());
			school.setWebsite(schoolDetails.getWebsite());
			school.setContact(schoolDetails.getContact());
			return schoolRepository.save(school);
		}
		return null;
	}

	public boolean deleteSchool(Long id) {
		if (schoolRepository.existsById(id)) {
			schoolRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<String> getAllCities() {
		return schoolRepository.findAllCities();
	}

	public List<String> getAllBoards() {
		return schoolRepository.findAllBoards();
	}

	public List<String> getAllTypes() {
		return schoolRepository.findAllTypes();
	}
}
