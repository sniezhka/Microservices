package com.example.student.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Setter;

@Document
@Setter
public class Student {
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String middleName;

	public Student() {
	}

	public Student(String id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}

	public Student(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
}