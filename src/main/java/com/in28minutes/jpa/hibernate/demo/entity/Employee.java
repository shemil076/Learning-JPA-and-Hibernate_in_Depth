package com.in28minutes.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@MappedSuperclass
//@Entity   // cannot use @Entity because @MappedSupperClass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default strategy
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // use when performance is concerned
//@Inheritance(strategy = InheritanceType.JOINED)           // use when data integrity is concerned
//@DiscriminatorColumn(name = "Employee_Type")
public abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;


    public Employee(String name) {
        this.name = name;
    }

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
