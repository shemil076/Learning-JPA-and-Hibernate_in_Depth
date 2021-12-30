package com.in28minutes.jpa.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Column(nullable = false)
    private String description;


    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Review(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Review(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Review[%s %s]", rating, description);
    }
}
