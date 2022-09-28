package com.example.student.controllers;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.student.dto.input.student.CreateStudentDTO;
import com.example.student.dto.input.student.UpdateStudentDTO;
import com.example.student.dto.output.student.StudentDTO;
import com.example.student.model.Student;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	@Autowired
	private final StudentService service;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@GetMapping("/{id}")
	public StudentDTO get(@PathVariable String id) {
		Optional<Student> student = service.get(id);

		if (student.isEmpty()) {
			logger.error("Student not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
		}
		
		StudentDTO studentDTO = new StudentDTO()
				.setId(student.get().getId())
				.setLastName(student.get().getLastName());
		
		return studentDTO;
	}

	@PostMapping()
	public StudentDTO create(@RequestBody CreateStudentDTO input) {
		Student student = service.create(input);
		
		if(student == null) {
			logger.error("The student is not saved in the database");
		}
		
		StudentDTO studentDTO = new StudentDTO()
				.setId(student.getId())
				.setLastName(student.getLastName());
		
		return studentDTO;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentDTO update(@PathVariable String id, @RequestBody UpdateStudentDTO input) {
		Student student = service.update(id, input);
		
		if(student == null) {
			logger.error("The student is not update in the database");
		}
		return new StudentDTO()
				.setLastName(student.getLastName())
				.setId(student.getId());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		service.remove(id);
		logger.info("Deleted item from id: {} ", id);
	}
}
