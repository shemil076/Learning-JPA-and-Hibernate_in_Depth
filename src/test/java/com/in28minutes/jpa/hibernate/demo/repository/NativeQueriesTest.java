package com.in28minutes.jpa.hibernate.demo.repository;

import com.in28minutes.jpa.hibernate.demo.DemoApplication;
import com.in28minutes.jpa.hibernate.demo.entity.Course;
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
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    void NativeQueriesBasic() {
       Query query =  em.createNativeQuery("Select * from course", Course.class);
       List resultList = query.getResultList();
       logger.info("Select * from course -> {}", resultList);
    }

    @Test
    void NativeQueriesWithParameters() {
        Query query =  em.createNativeQuery("Select * from course where id = ?", Course.class);
        query.setParameter(1,10001L);
        List resultList = query.getResultList();
        logger.info("Select * from course -> {}", resultList);
    }

    @Test
    void NativeQueriesWithNamedParameters() {
        Query query =  em.createNativeQuery("Select * from course where id = :id", Course.class);
        query.setParameter("id",10001L);
        List resultList = query.getResultList();
        logger.info("Select * from course -> {}", resultList);
    }

    @Test
    @Transactional
    void NativeQueriesToUpdate() {
        Query query =  em.createNativeQuery("update course set last_updated_date=sysdate()", Course.class);
        int noOfRows= query.executeUpdate();
        logger.info("Number of rows updated-> {}", noOfRows);
    }

}