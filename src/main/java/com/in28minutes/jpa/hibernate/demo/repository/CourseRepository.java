package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


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

    public void addReviews(){
        // get the course 10003
        Course course= findById(10003L);
         logger.info("Course Review-> {}", course.getReviews());

         // add 2 reviews
        Review review1= new Review("5", "Great hands on stuff");
        Review review2= new Review("5", "hatsoff");

        // setting the relationship
        course.addReviews(review1);
        review1.setCourse(course);

        // setting the relationship
        course.addReviews(review2);
        review2.setCourse(course);

        // save it to the database
        em.persist(review1);
        em.persist(review2);

    }


}

