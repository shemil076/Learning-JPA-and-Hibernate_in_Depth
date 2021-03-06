package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Address;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository repository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void retrieveStudentAndPassport() {
        Student student = em.find(Student.class, 20001L);
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
    }

    @Test
    public void test(){
        repository.toUnderstandPersistenceContext();
    }


    @Test
    @Transactional
    void retrievePassportAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("Student -> {}", passport);
        logger.info("Passport -> {}", passport.getStudent());
    }


    @Test
    @Transactional
    void retrieveStudentAndCourse() {
      Student student = em.find(Student.class, 20001L);
      logger.info("Student -> {}", student);
      logger.info("Course -> {}", student.getCourses());
    }

    @Test
    @Transactional
    void retrieveCourseAndStudents() {
        Course course = em.find(Course.class, 10001L);
        logger.info("Course -> {}", course);
        logger.info("Student -> {}", course.getStudents());
    }


    @Test
    @Transactional
    void setAddressDetails() {
        Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("N0 1","Street","New York"));
        em.flush();
        logger.info("Student -> {}", student);
        logger.info("Passport -> {}", student.getPassport());
        logger.info("Address -> {}", student.getAddress());

    }

}
