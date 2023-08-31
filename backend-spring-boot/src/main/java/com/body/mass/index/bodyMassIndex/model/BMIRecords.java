package com.body.mass.index.bodyMassIndex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity 
@Table(name="bmirecord")
public class BMIRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false)
    private double height;
    @Column(nullable = false)
    private String bmiCategory;
    

    public BMIRecords() {
    }
    public BMIRecords(String fullName, int age, String gender, double weight, double height, String bmiCategory) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.bmiCategory = bmiCategory;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getBmiCategory() {
        return bmiCategory;
    }
    public void setBmiCategory(String bmiCategory) {
        this.bmiCategory = bmiCategory;
    }
}
