package com.project.classroom.model;

import javax.persistence.*;

/**
 * Represents a learner within an educational institution.
 * Each learner is associated with a specific grade level.
 */
@Entity
@Table(name = "learner")
public class Learner extends AbstractModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gradeLevel_id", nullable = false)
    private GradeLevel gradeLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gradeLevel=" + (gradeLevel != null ? gradeLevel.getName() : "None") +
                '}';
    }
}

