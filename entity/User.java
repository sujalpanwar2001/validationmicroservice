package com.project.java.validationmicroservice.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    // Primary key for the User entity
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // User's name
    @Column(name = "name")
    String name;

    // User's age
    @Column(name = "age")
    Integer age;

    // User's gender
    @Column(name = "gender")
    String gender;

    // User's date of birth
    @Column(name = "DOB")
    String dob;

    // User's nationality
    @Column(name = "nationality")
    String nationality;

    // Verification status of the user
    @Column(name = "verification_status")
    String verificationStatus;

    // Timestamp for when the user was created
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", updatable = false)
    Date dateCreated;

    // Timestamp for when the user was last modified
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_modified")
    Date dateModified;

    public User() {
    }

    public User(Long id, String name, Integer age, String gender, String dob, String nationality, String verificationStatus, Date dateCreated, Date dateModified) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dob = dob;
        this.nationality = nationality;
        this.verificationStatus = verificationStatus;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    // Getter and setter methods for each attribute

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
}
