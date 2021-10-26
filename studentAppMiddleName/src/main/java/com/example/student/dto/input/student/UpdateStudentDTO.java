package com.example.student.dto.input.student;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class UpdateStudentDTO {
	private String middleName;
	private String lastName;
}
