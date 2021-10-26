package com.example.student.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.student.dto.input.student.UpdateStudentDTO;
import com.example.student.model.Student;
import com.example.student.model.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentService {
	@Autowired
	private final StudentRepository studentsRepository;

	public Optional<Student> get(String id) {
		return studentsRepository.findById(id);
	}

	public Student update(String id, UpdateStudentDTO dto) {
		Student student = studentsRepository.findById(id).orElseThrow();
		
		student.setFirstName(dto.getFirstName());
		
		studentsRepository.save(student);
		
		return student;
	}

}
