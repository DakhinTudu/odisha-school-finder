package com.schoolfinder.controller;

import com.schoolfinder.entity.School;
import com.schoolfinder.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schools")
@CrossOrigin(origins = "http://127.0.0.1:5500/index.html") // Allow frontend to access the API
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@GetMapping
	public List<School> getAllSchools() {
		return schoolService.getAllSchools();
	}

	@GetMapping("/{id}")
	public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
		Optional<School> school = schoolService.getSchoolById(id);
		return school.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/search")
	public List<School> searchSchools(@RequestParam(required = false) String name,
			@RequestParam(required = false) String city, @RequestParam(required = false) String board,
			@RequestParam(required = false) String type) {
		return schoolService.searchSchools(name, city, board, type);
	}

	@PostMapping
	public School addSchool(@RequestBody School school) {
		return schoolService.addSchool(school);
	}

	@PutMapping("/{id}")
	public ResponseEntity<School> updateSchool(@PathVariable Long id, @RequestBody School schoolDetails) {
		School updatedSchool = schoolService.updateSchool(id, schoolDetails);
		if (updatedSchool != null) {
			return ResponseEntity.ok(updatedSchool);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
		if (schoolService.deleteSchool(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/cities")
	public List<String> getAllCities() {
		return schoolService.getAllCities();
	}

	@GetMapping("/boards")
	public List<String> getAllBoards() {
		return schoolService.getAllBoards();
	}

	@GetMapping("/types")
	public List<String> getAllTypes() {
		return schoolService.getAllTypes();
	}
}
