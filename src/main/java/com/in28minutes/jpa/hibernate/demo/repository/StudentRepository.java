package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Passport;
import com.in28minutes.jpa.hibernate.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
       return em.find(Student.class,id);
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        }else {
            em.merge(student);
        }
        return student;
    }


    public void saveStudentWithPassport(){
        Passport passport = new Passport("z123456");
        em.persist(passport);

        Student student= new Student("MIKE");
        student.setPassport(passport);
        em.persist(student);
    }


    public void toUnderstandPersistenceContext(){
        Student student = em.find(Student.class, 20001L);

        Passport passport = student.getPassport();

        passport.setNumber("E1234567");

        student.setName("Ranga - updated");
    }



//    public void insertStudentAndCourse() {
//        Student student = new Student("Jack");
//        Course course = new Course("Microservices in 100 steps");
//        em.persist(student);
//        em.persist(course);
//        student.addCourses(course);
//        course.addStudents(student);
//
//        em.persist(student);
//    }

    public void insertStudentAndCourse(Student student, Course course) {
//        Student student = new Student("Jack");
//        Course course = new Course("Microservices in 100 steps");
        student.addCourses(course);
        course.addStudents(student);

        em.persist(student);
        em.persist(course);

    }




}

