package com.example.student.dto.output.student;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors
@Getter
@Setter
public class StudentDTO {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
}
