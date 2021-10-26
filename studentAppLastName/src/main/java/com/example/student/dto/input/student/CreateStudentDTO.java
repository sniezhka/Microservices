package com.example.student.dto.input.student;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
@Getter
public class CreateStudentDTO {
	private String lastName;
}
