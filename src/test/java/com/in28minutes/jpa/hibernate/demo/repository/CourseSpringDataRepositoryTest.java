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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository repository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_coursePresent() {
        Optional<Course> courseOptional = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void findById_courseNotPresent() {
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    public void playingAroundWithSpringDataRepository() {
//        Course course = new Course("Microservices in 100 steps");
//        repository.save(course);
//
//        course.setName("Microservices in 100 steps - updated");
//        repository.save(course);

        logger.info("{}", repository.findAll());
        logger.info("{}",repository.count());

    }

    @Test
    public void sort() {
//        Course course = new Course("Microservices in 100 steps");
//        repository.save(course);
//
//        course.setName("Microservices in 100 steps - updated");
//        repository.save(course);

//        Sort sort = new Sort(Sort.Direction.ASC, "name"); // default sort method is ASC
//        logger.info("Sorted Courses->{}", repository.findAll(sort));
        logger.info("{}",repository.count());

    }


    @Test
    public void pagination(){
        PageRequest pageRequest =  PageRequest.of(0,3);
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info(" first page {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info(" Second page {}", secondPage.getContent());

        Pageable thirdPageable = secondPage.nextPageable();
        Page<Course> thirdPage = repository.findAll(thirdPageable);
        logger.info(" Third  page {}", thirdPage.getContent());

    }

    @Test
    public void findByName(String name){
        logger.info("Find By Name -> {}", repository.findByName(name));
    }
}
