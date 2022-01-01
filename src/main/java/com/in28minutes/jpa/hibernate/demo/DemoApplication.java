package com.in28minutes.jpa.hibernate.demo;

import com.in28minutes.jpa.hibernate.demo.entity.*;
import com.in28minutes.jpa.hibernate.demo.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.demo.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private Logger Logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository StudentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


//		Logger.info("Course -> {}", repository.findById(10001L));

//		repository.save(new Course("Microservice in 100 steps"));
//
//		repository.save(new Course(10001L,"java in 100 steps"));

//		repository.playWithEntityManager();

//		StudentRepository.saveStudentWithPassport();


//		List<Review> reviews = new ArrayList<Review>();
//		reviews.add(new Review("5", "Great hands on stuff"));
//		reviews.add(new Review("5", "hatsoff"));
//		courseRepository.addReviews(10003L,reviews);


//		StudentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 steps"));

		employeeRepository.insert(new PartTimeEmployee("Jill",new BigDecimal ("50")));


		employeeRepository.insert(new FullTimeEmployee("Jack",new BigDecimal ("10000")));


		Logger.info("All employees -> {}", employeeRepository.retrieveAllEmployees());
	}
}
