package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Student;
import com.crud.repo.IStudentRepository;


@RestController
public class StudentController {

	@Autowired
	private IStudentRepository studentRepository;

	@PostMapping("/addstudent")
	public ResponseEntity<?> addStudent(@RequestBody Student student) {
		Student save = this.studentRepository.save(student);
		return ResponseEntity.ok(save);
	}

	@GetMapping("/getstudents")
	public ResponseEntity<?> getStudents() {

		return ResponseEntity.ok(this.studentRepository.findAll());
	}
	// my work
	@PutMapping("/update/{id}")
	ResponseEntity<Student> updateStudent(@PathVariable Integer id,@RequestBody Student student){
		Student exstudent=studentRepository.findById(id).get();
		exstudent.setCity(student.getCity());
		exstudent.setCollege(student.getCollege());
		exstudent.setName(student.getName());
		exstudent.setId(student.getId());
		studentRepository.save(exstudent);
		
		return ResponseEntity.ok(exstudent);
		
	}
	@DeleteMapping("/delstudent/{id}")
	void deleteStudent(@PathVariable Integer id) {
		studentRepository.deleteById(id);
	}

}
