package com.lombok;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
public class PersonVO {
	
	private String firstName;
	private String lastName;
	private int age;

	public static List<PersonVO> getList(){
		List<PersonVO> list = new ArrayList<PersonVO>();
		list.add(PersonVO.builder().firstName("Sandeep").age(40).build());
		list.add(PersonVO.builder().firstName("Kusum").build());
		return list;
	}
}
