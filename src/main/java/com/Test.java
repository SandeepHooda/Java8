package com;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.Interface.Calculator;
import com.lombok.PersonVO;
import com.webSrv.DummyWebSrv;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		//optional,supplier stream (filter, map, aggregation), parallel stream, functional interface, function, method reference, 
		//Predicate, lamda, lombok, 
		//rest post - create. Put - update idempotent
		
		/***************************************
		 **************Lombok example **********
		 ***************************************/
		PersonVO vo = new PersonVO("sandeep", "hooda",40); //All args constructor
		vo = PersonVO.builder().firstName("Kusum").build();//Builder
		vo =vo.withFirstName("Shaurya");//With
		
		/**********************
		 ******* Function, method reference, Predicate, Supplier, Generics, Method reference
		 **********************/
		Function<Integer, String> srvToCall = new DummyWebSrv()::getStudentName;//This one accept Integer and returns String
		//Function - Just like we store primitive values/objects we can now store functions in a variable and pass it as argument
		System.out.println(srvToCall.apply(10));
		
		
		Predicate<Integer> noGreaterThan5 =  x -> x > 5; //Just like Function but always returns boolean
		System.out.println("10 > 5 ?"+noGreaterThan5.test(10));
		
		//Supplier: combination of "Function" + "All Arguments it needs". Pass this combo to some other method as parameter 
		//When we call the method "get" then the combo is fired
		Supplier<PersonVO> getPersonFrom_Web = () -> new DummyWebSrv().getPersonFromWS("Sandeep", "Hooda", 40);//Suppler don't except anything to it starts with () and then ->
		Supplier<PersonVO> getPersonFrom_DB = () -> new DummyWebSrv().getPersonFromDB("Kusum", "Hooda", 40);
		PersonVO personObj = test.timeItAndExecure(getPersonFrom_Web ,"getPersonFrom_Web");
		
		/************************
		 *********Optional - It clarifies the developers intent that if you call my method you may get a null value as so be prepared
		 ****************************/
		PersonVO dummy = PersonVO.builder().firstName("Dummy").build();
		PersonVO person = new DummyWebSrv().getNullablePerson().orElse(dummy);
		//System.out.println(person.getFirstName());
		
		/**********************
		 * *****FunctionalInterface - only one non-default method 
		 * Comparator interface in java
		 ***************/
		
		Calculator<Double> myCalc = ( a, b) -> a+b; 
		
		/************************
		 * *********Stream
		 *************************/

		List<PersonVO> list = PersonVO.getList();
		//Parallel Stream , Map & collect
		list= list.parallelStream().map(p -> { p.setFirstName( p.getFirstName().toUpperCase()); return p;  }).collect(Collectors.toList());
		
		//Filter with predicate & collect
		list = list.stream().filter(v -> v.getAge() <100).collect(Collectors.toList());
		
		//For Each
		list.stream().forEach( p -> System.out.println(p.getFirstName()) );
		
		//Find first
		Optional<PersonVO> firstPerson =  list.stream().filter(v -> v.getAge() <100).findFirst();
		person = firstPerson.orElse(dummy);
		
		//Any match
		list.stream().anyMatch(p -> p.getFirstName().equalsIgnoreCase("sandeep"));
		
		//IntSummaryStatistics
		List<Integer> integersList = Arrays.asList(1,2,13,4,15,6,17,8,19);
		IntSummaryStatistics summary= integersList.stream().mapToInt( x -> x).summaryStatistics();
		summary.getSum();
		
		
	
	}

	
	/* This defines R and T*/ 
	public <T, R> R timeItAndExecure(Supplier<R> methodToExecute, String methodName) {
		long startTime = System.currentTimeMillis();
		R result = methodToExecute.get();
		System.out.println(methodName+" time taken: "+(System.currentTimeMillis() - startTime));
		return result;
	}
	
	
	
	
}
