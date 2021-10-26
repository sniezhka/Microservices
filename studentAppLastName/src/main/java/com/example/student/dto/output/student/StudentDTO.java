package com.example.student.dto.output.student;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class StudentDTO {
	private String id;
	private String lastName;
}
