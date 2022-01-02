package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void jpqlBasic() {
       Query query =  em.createNamedQuery("query_get_all_courses");
       List resultList = query.getResultList();
       logger.info("Select c from Course c -> {}", resultList);

    }

    @Test
    void jpqlTyped() {
        TypedQuery<Course> query =  em.createNamedQuery("query_get_all_courses", Course.class);
        List <Course>resultList = query.getResultList();
        logger.info("Select c from Course c -> {}", resultList);

    }

    @Test
    public void jpqlCoursesWithoutStudents() {
        TypedQuery<Course> query =
                em.createQuery("select  c from Course c where c.students is empty", Course.class);

        List<Course> resultList = query.getResultList();

        logger.info("Results {}",resultList);
    }


    @Test
    public void jpqlCoursesWithAtLeastTwoStudents() {
        TypedQuery<Course> query =
                em.createQuery("select  c from Course c where size(c.students) >=2", Course.class);

        List<Course> resultList = query.getResultList();

        logger.info("Results {}",resultList);
    }

//    @Test
//    public void jpqlCoursesOrderedByNumberOfStudents() {
//        TypedQuery<Course> query =
//                em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
//
//        List<Course> resultList = query.getResultList();
//
//        logger.info("Results {}",resultList);
//    }

    @Test
    public void jpqlStudentsWherePassportPatternIs1234() {
        TypedQuery<Student> query =
                em.createQuery("select  s from Student s where s.passport.number like '%1234%' ", Student.class);

        List<Student> resultList = query.getResultList();

        logger.info("Results {}",resultList);
    }

    @Test
    public void testJoin(){ //normal join
        Query query = em.createQuery("select c,s from Course  c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();

        logger.info("Size of result list -> {}", resultList.size());

        for (Object[] result : resultList){
            logger.info("Course -> {} Student -> {}", result[0], result[1]);
        }

    }

    @Test
    public void testLeftJoin(){ //left join
        Query query = em.createQuery("select c,s from Course  c Left JOIN c.students s");
        List<Object[]> resultList = query.getResultList();

        logger.info("Size of result list -> {}", resultList.size());

        for (Object[] result : resultList){
            logger.info("Course -> {} Student -> {}", result[0], result[1]);
        }

    }


    @Test
    public void testCrossJoin(){ //cross join
        Query query = em.createQuery("select c,s from Course  c , Student s");
        List<Object[]> resultList = query.getResultList();

        logger.info("Size of result list -> {}", resultList.size());

        for (Object[] result : resultList){
            logger.info("Course -> {} Student -> {}", result[0], result[1]);
        }

    }
}