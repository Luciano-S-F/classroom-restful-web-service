package com.project.classroom.converter;

import com.project.classroom.dto.LearnerDTO;
import com.project.classroom.model.Learner;
import com.project.classroom.service.GradeLevelService;
import com.project.classroom.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts LearnerDTO objects to Learner entities, enabling the transition of data from DTOs to database entities.
 */
@Component
public class LearnerDTOToLearner implements Converter<LearnerDTO, Learner> {

  private LearnerService learnerService;
  private GradeLevelService gradeLevelService;

  @Autowired
  public void setGradeLevelService(GradeLevelService gradeLevelService) {
    this.gradeLevelService = gradeLevelService;
  }

  @Autowired
  public void setLearnerService(LearnerService learnerService) {
    this.learnerService = learnerService;
  }

  /**
   * Converts a single LearnerDTO to a Learner entity.
   * @param element the LearnerDTO to convert
   * @return the converted Learner entity
   */
  @Override
  public Learner convert(LearnerDTO element) {
    Learner learner = (element.getId() != null) ? learnerService.findById(element.getId()) : new Learner();

    learner.setName(element.getName());
    learner.setPhone(element.getPhone());
    learner.setEmail(element.getEmail());
    learner.setGradeLevel(gradeLevelService.findByName(element.getGradeLevelName()));

    return learner;
  }

  /**
   * Converts a list of LearnerDTOs to a list of Learner entities.
   * @param learnerDTOList the list of LearnerDTOs to convert
   * @return a list of Learner entities
   */
  public List<Learner> convertList(List<LearnerDTO> learnerDTOList) {
    List<Learner> learners = new ArrayList<>();
    for (LearnerDTO dto : learnerDTOList) {
      learners.add(convert(dto));
    }
    return learners;
  }
}
