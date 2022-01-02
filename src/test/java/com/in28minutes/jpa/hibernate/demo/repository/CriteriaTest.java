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
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class CriteriaTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void criteriaBasic() {
        // "Select c From Course c"

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);// root of the query

        // 3. Define Predicates etc using Criteria Builder

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query


       TypedQuery query =  em.createQuery(cq.select(courseRoot));

       List<Course> resultList = query.getResultList();

       logger.info("Typed query -> {}", resultList);
    }


    @Test
    void criteriaWhereTest() {
        // "Select c From Course c where name like '%100'"

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);// root of the query

        // 3. Define Predicates etc using Criteria Builder

        Predicate like = cb.like(courseRoot.get("name"), "%100 Steps");


        // 4. Add Predicates etc to the Criteria Query

        cq.where(like);

        // 5. Build the TypedQuery using the entity manager and criteria query


        TypedQuery query =  em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("Typed query -> {}", resultList);
    }

    @Test
    void criteriaWithoutStudents() {
        // "Select c From Course c where c.student is empty "

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);// root of the query

        // 3. Define Predicates etc using Criteria Builder

        Predicate empty = cb.isEmpty(courseRoot.get("students"));


        // 4. Add Predicates etc to the Criteria Query

        cq.where(empty);

        // 5. Build the TypedQuery using the entity manager and criteria query


        TypedQuery query =  em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("Typed query -> {}", resultList);
    }


    @Test
    void criteriaJoin() {
        // "Select c From Course c Join c.student s"

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);// root of the query

        // 3. Define Predicates etc using Criteria Builder

        Join<Object, Object> join = courseRoot.join("students");

        // 4. Add Predicates etc to the Criteria Query

//        cq.where(join);

        // 5. Build the TypedQuery using the entity manager and criteria query


        TypedQuery query =  em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("Typed query -> {}", resultList);
    }

    @Test
    void criteriaLeftJoin() {
        // "Select c From Course c Left Join c.student s"

        // 1. Use Criteria Builder to create a Criteria Query returning the
        // expected result object

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);


        // 2. Define roots for tables which are involved in the query

        Root<Course> courseRoot = cq.from(Course.class);// root of the query

        // 3. Define Predicates etc using Criteria Builder

        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);


        // 4. Add Predicates etc to the Criteria Query

//        cq.where(join);

        // 5. Build the TypedQuery using the entity manager and criteria query


        TypedQuery query =  em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("Typed query -> {}", resultList);
    }




}