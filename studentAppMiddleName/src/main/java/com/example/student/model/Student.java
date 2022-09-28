package com.example.student.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Setter
@Getter
public class Student {
	@Id
	private String id;
	private String middleName;
	private String lastName;

	public Student() {
	}

	public Student(
			String id,
			String middleName
	) {
		this.id = id;
		this.middleName = middleName;
	}

	public Student(
			String middleName
	) {
		this.middleName = middleName;
	}
}