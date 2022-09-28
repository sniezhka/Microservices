package com.example.student.controllers;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.student.service.RestTemplateClient;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	@Autowired
	private final StudentService service;
	
	@Autowired
	private RestTemplateClient restTemplate;
	
	static final String LASTNAME_APP_URL = "http://app3:8083/students";
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);	


	@GetMapping("/{id}")
	public StudentDTO get(@PathVariable String id) {
		StudentDTO studentDTO = restTemplate.getForObject(LASTNAME_APP_URL + "/" + id, StudentDTO.class);
		
		Optional<Student> student = service.get(id);

		if (student.isEmpty()) {
			logger.error("Student not found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found.");
			
		}
	
		studentDTO.setMiddleName(student.get().getMiddleName());
		
		return studentDTO;
	}

	@PostMapping()
	public StudentDTO create(@RequestBody CreateStudentDTO input){
		StudentDTO studentDTO = restTemplate.postForObject(LASTNAME_APP_URL, input, StudentDTO.class);

		Student student = service.update(
				studentDTO.getId(),
				new UpdateStudentDTO().setMiddleName(input.getMiddleName())
			);
		
		if(student == null) {
			logger.error("The student is not created in the database");
		}
		
		studentDTO.setMiddleName(student.getMiddleName());
		
		return studentDTO;
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public StudentDTO update(@PathVariable String id, @RequestBody UpdateStudentDTO input) {
		HttpEntity<UpdateStudentDTO> request = new HttpEntity<UpdateStudentDTO>(input);
		
		ResponseEntity<StudentDTO> responseEntity = restTemplate.exchange(
				String.format("%s/%s", LASTNAME_APP_URL, id),
				HttpMethod.PUT,
				request,
				StudentDTO.class
			);

		service.update(id, input);
		
		StudentDTO studentDto = responseEntity.getBody();
		
		studentDto.setMiddleName(input.getMiddleName());
		
		return studentDto;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		restTemplate.delete(String.format("%s/%s", LASTNAME_APP_URL, id));
		logger.info("Deleted item from id: {} ", id);
	}
}
