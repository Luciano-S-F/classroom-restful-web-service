package com.project.classroom.dto;

import javax.validation.constraints.*;

/**
 * Data Transfer Object for Learner entities, facilitating data transfer across layers.
 */
public class LearnerDTO {

  private Integer id;

  @Size(min = 2, max = 60, message = "Learner name must be between 2 and 60 characters.")
  @NotNull(message = "Learner name is mandatory")
  @NotBlank(message = "Learner name is mandatory")
  private String name;

  @Email(message = "Invalid email format.")
  @Size(min = 2, max = 60, message = "Email must be between 2 and 60 characters.")
  @NotNull(message = "Email cannot be null.")
  @NotBlank(message = "Email cannot be blank.")
  private String email;

  @Size(min = 2, max = 15, message = "Phone size must be between 2 and 15 characters.")
  @NotNull(message = "Phone number cannot be null.")
  @NotBlank(message = "Phone number cannot be blank.")
  @Pattern(regexp = "^(\\+?351)?9\\d\\d{7}$", message = "Phone number must be a valid Portuguese mobile number, optionally prefixed with +351.")
  private String phone;

  @NotNull(message = "Grade level name cannot be null.")
  @NotBlank(message = "Grade level name cannot be blank.")
  @Size(min = 2, max = 60, message = "Grade level name must be between 2 and 60 characters.")
  private String gradeLevelName;

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

  public String getGradeLevelName() {
    return gradeLevelName;
  }

  public void setGradeLevelName(String gradeLevelName) {
    this.gradeLevelName = gradeLevelName;
  }

  @Override
  public String toString() {
    return "LearnerDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", gradeLevelName='" + gradeLevelName + '\'' +
            '}';
  }
}
