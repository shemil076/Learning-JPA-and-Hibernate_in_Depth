package com.in28minutes.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {  //owning side of the relationship

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(name="STUDENT_COURSE",
            joinColumns = @JoinColumn(name ="STUDENT_ID"),
            inverseJoinColumns = @JoinColumn(name ="COURSE_ID"))
    private List<Course> courses = new ArrayList<Course>();


    @Embedded
    private Address address;


    public Student(){}

    public Student(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course courses) {
        this.courses.add(courses);
    }

    public Student(Long id, String name) {
        this.id = id;
        this.name = name;
    }



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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passport=" + passport +
                ", courses=" + courses +
                ", address=" + address +
                '}';
    }
}
