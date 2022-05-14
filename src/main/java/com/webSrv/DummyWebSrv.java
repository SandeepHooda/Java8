package com.webSrv;

import java.util.Optional;
import java.util.function.Function;

import com.lombok.PersonVO;

public class DummyWebSrv implements Function<Integer, String>{
	
	public String getStudentName(int rollNumber) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Shaurya Hooda";
	}

	public String apply(Integer t) {
		
		return getStudentName(t);
	}
	
	
	public PersonVO getPersonFromWS(String firstName, String lastName, int age) {
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PersonVO.builder().firstName(firstName+" Web Srv").build();
	}
	
	public PersonVO getPersonFromDB(String firstName, String lastName, int age) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return PersonVO.builder().firstName(firstName+" DB").build();
	}
	
	public Optional<PersonVO> getNullablePerson () {
		PersonVO vo =PersonVO.builder().firstName("Sandeep").build();
		if (1==1) {
			vo = null;
		}
		return Optional.ofNullable(vo);
	}

}
