package com.project.classroom.converter;

import com.project.classroom.dto.LearnerDTO;
import com.project.classroom.model.Learner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts Learner entities into LearnerDTO objects to facilitate data transfer between the backend and frontend or external systems.
 */
@Component
public class LearnerToLearnerDTO implements Converter<Learner, LearnerDTO> {

  /**
   * Converts a Learner entity into a LearnerDTO.
   * @param element the Learner to convert
   * @return the converted LearnerDTO
   */
  @Override
  public LearnerDTO convert(Learner element) {
    LearnerDTO learnerDTO = new LearnerDTO();

    learnerDTO.setId(element.getId());
    learnerDTO.setName(element.getName());
    learnerDTO.setEmail(element.getEmail());
    learnerDTO.setPhone(element.getPhone());
    learnerDTO.setGradeLevelName(element.getGradeLevel().getName());

    return learnerDTO;
  }

  /**
   * Converts a list of Learner entities into a list of LearnerDTOs.
   * @param learnerList the list of Learners to convert
   * @return a list of LearnerDTOs
   */
  public List<LearnerDTO> convertList(List<Learner> learnerList) {
    List<LearnerDTO> learnerDTOs = new ArrayList<>();
    for (Learner learner : learnerList) {
      learnerDTOs.add(convert(learner));
    }
    return learnerDTOs;
  }
}
