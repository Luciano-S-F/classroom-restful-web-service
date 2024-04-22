package com.project.classroom.model;

import javax.persistence.*;
import java.util.List;

/**
 * Represents a grade level within an educational institution.
 * Each grade level can have multiple learners associated with it.
 */
@Entity
@Table(name = "gradelevel")
public class GradeLevel extends AbstractModel {

    @Column(nullable = false, unique = true) // Adicionando unique = true para garantir que o nome seja único
    private String name;

    @Column(nullable = false)
    private String location;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "gradeLevel",
            fetch = FetchType.EAGER
    )
    private List<Learner> learners;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Learner> getLearners() {
        return learners;
    }

    public void setLearners(List<Learner> learners) {
        this.learners = learners;
    }

    @Override
    public String toString() {
        return "GradeLevel{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", learners=" + (learners != null ? learners.size() : 0) +
                '}';
    }
}
