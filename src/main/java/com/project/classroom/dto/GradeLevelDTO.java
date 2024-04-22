package com.project.classroom.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Data Transfer Object (DTO) for GradeLevel entities, encapsulating data to be transferred between processes.
 */
public class GradeLevelDTO {

  private Integer id;

  @Size(min = 2, max = 60, message = "Grade level name must be between 2 and 60 characters.")
  @NotNull(message = "Grade level name is mandatory")
  @NotBlank(message = "Grade level name cannot be blank")
  private String name;

  private String location;
  private List<LearnerDTO> learnerList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public List<LearnerDTO> getLearnerList() {
    return learnerList;
  }

  public void setLearnerList(List<LearnerDTO> learnerList) {
    this.learnerList = learnerList;
  }

  @Override
  public String toString() {
    return "GradeLevelDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", location='" + location + '\'' +
            ", learnerList=" + learnerList +
            '}';
  }
}
