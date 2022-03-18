package com.brycen.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewDTO extends BaseDTO {
	private String title;
	private String content;
	private String description;

	
}
