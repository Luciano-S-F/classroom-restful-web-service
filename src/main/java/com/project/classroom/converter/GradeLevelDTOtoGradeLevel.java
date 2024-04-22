package com.project.classroom.converter;

import com.project.classroom.model.GradeLevel;
import com.project.classroom.dto.GradeLevelDTO;
import com.project.classroom.model.Learner;
import com.project.classroom.service.GradeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the Converter interface to convert a GradeLevelDTO object into a GradeLevel entity.
 * It leverages the GradeLevelService to fetch existing GradeLevel entities if necessary.
 */
@Component
public class GradeLevelDTOtoGradeLevel implements Converter<GradeLevelDTO, GradeLevel> {

  private GradeLevelService gradeLevelService;
  private LearnerDTOToLearner learnerDTOToLearner;

  @Autowired
  public void setGradeLevelService(GradeLevelService gradeLevelService) {
    this.gradeLevelService = gradeLevelService;
  }

  @Autowired
  public void setLearnerDTOToLearner(LearnerDTOToLearner learnerDTOToLearner) {
    this.learnerDTOToLearner = learnerDTOToLearner;
  }

  /**
   * Converts a GradeLevelDTO object to a GradeLevel entity.
   * If the DTO has an ID, attempts to fetch the existing GradeLevel from the database.
   * If no GradeLevel is found or the DTO does not have an ID, a new GradeLevel object is created.
   *
   * @param dto the GradeLevelDTO to convert
   * @return the converted GradeLevel entity
   */
  @Override
  public GradeLevel convert(GradeLevelDTO dto) {
    GradeLevel gradeLevel = new GradeLevel();

    if (dto.getId() != null) {
      gradeLevel = gradeLevelService.findById(dto.getId());
      if (gradeLevel == null) {
        gradeLevel = new GradeLevel();
      }
    }

    gradeLevel.setName(dto.getName());
    gradeLevel.setLocation(dto.getLocation());

    if (dto.getLearnerList() != null) {
      List<Learner> learners = dto.getLearnerList().stream()
              .map(learnerDto -> learnerDTOToLearner.convert(learnerDto))
              .collect(Collectors.toList());
      gradeLevel.setLearners(learners);
    }

    return gradeLevel;
  }
}