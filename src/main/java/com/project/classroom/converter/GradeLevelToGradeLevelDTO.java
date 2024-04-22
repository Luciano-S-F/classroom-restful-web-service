package com.project.classroom.converter;

import com.project.classroom.dto.GradeLevelDTO;
import com.project.classroom.model.GradeLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This component handles the conversion from GradeLevel entities to GradeLevelDTO objects.
 * It utilizes a LearnerToLearnerDTO converter to handle nested Learner objects.
 */
@Component
public class GradeLevelToGradeLevelDTO implements Converter<GradeLevel, GradeLevelDTO> {

  private LearnerToLearnerDTO learnerToLearnerDTO;

  @Autowired
  public void setLearnerToLearnerDTO(LearnerToLearnerDTO learnerToLearnerDTO) {
    this.learnerToLearnerDTO = learnerToLearnerDTO;
  }

  /**
   * Converts a single GradeLevel entity to a GradeLevelDTO.
   * @param element the GradeLevel to convert
   * @return the converted GradeLevelDTO
   */
  @Override
  public GradeLevelDTO convert(GradeLevel element) {
    GradeLevelDTO gradeLevelDTO = new GradeLevelDTO();

    gradeLevelDTO.setId(element.getId());
    gradeLevelDTO.setLocation(element.getLocation());
    gradeLevelDTO.setName(element.getName());
    gradeLevelDTO.setLearnerList(learnerToLearnerDTO.convertList(element.getLearners()));

    return gradeLevelDTO;
  }

  /**
   * Converts a list of GradeLevel entities to a list of GradeLevelDTOs.
   * If the input list is null, an empty list is returned to avoid null pointer exceptions.
   * @param gradeLevelList the list of GradeLevels to convert
   * @return a list of GradeLevelDTOs
   */
  public List<GradeLevelDTO> convertList(List<GradeLevel> gradeLevelList) {
    if (gradeLevelList == null) {
      return new ArrayList<>();
    }

    List<GradeLevelDTO> gradeLevelDTOList = new ArrayList<>();
    for (GradeLevel gradeLevel : gradeLevelList) {
      gradeLevelDTOList.add(convert(gradeLevel));
    }
    return gradeLevelDTOList;
  }
}

