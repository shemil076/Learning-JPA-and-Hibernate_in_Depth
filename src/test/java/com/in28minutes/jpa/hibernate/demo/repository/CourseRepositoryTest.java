package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
import com.in28minutes.jpa.hibernate.demo.entity.Review;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseRepository repository;

    @Autowired
    EntityManager em;

    @Test
    void findById() {
        logger.info("Test-> {}", repository.findById(10001L));
       assertEquals("JPA in 50 steps", repository.findById(10001L).getName());
    }


    @Test
    @Transactional
    void findById_firstLevelCachingDemo() {
        Course course = repository.findById(10001L);
        logger.info("First Course Retrieved {}", course);

        Course course1 = repository.findById(10001L);
        logger.info("First Course Retrieved - again {}", course1);


        assertEquals("JPA in 50 steps", course.getName());
        assertEquals("JPA in 50 steps", course1.getName());

    }

    @Test
    @DirtiesContext
    void deleteById() {
        repository.deleteById(10001L);
        assertNull(repository.findById(10001L));
    }

    @Test
    @DirtiesContext
    void save() {
        Course course = repository.findById(10001L);
        assertEquals("JPA in 50 steps",course.getName());

        course.setName("JPA in 50 steps - updates");
        repository.save(course);

        Course course1 = repository.findById(10001L);
        assertEquals("JPA in 50 steps - updates",course1.getName());
    }

    @Test
    @DirtiesContext
    public void playWithEntityManager(){
        repository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse(){
        Course course = repository.findById(10001L);
        logger.info("{}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseReviewsFor(){
        Review review = em.find(Review.class,50001L);
        logger.info("{}", review.getCourse());
    }

}