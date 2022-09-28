package com.example.student.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Student {
	@Id
	private String id;
	private String lastName;

	public Student() {
	}

	public Student(String id, String lastName) {
		this.id = id;
		this.lastName = lastName;
	}

	public Student(String lastName) {
		this.lastName = lastName;
	}
}