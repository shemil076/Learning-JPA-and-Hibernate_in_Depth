package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    public Course findById(Long id) {
       return em.find(Course.class,id);
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        }else {
            em.merge(course);
        }
        return course;
    }


    public void playWithEntityManager(){
        Course course1= new Course("Web Service in 100 steps");
        em.persist(course1);
        Course course2= findById(10001L);
        course2.setName("JPA in 50 steps- updated  ");


    }
}

