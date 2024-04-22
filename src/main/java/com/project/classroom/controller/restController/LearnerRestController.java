package com.project.classroom.controller.restController;

import com.project.classroom.converter.LearnerDTOToLearner;
import com.project.classroom.converter.LearnerToLearnerDTO;
import com.project.classroom.dto.LearnerDTO;
import com.project.classroom.model.Learner;
import com.project.classroom.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Luciano Filho
 * @version 1.0
 * GitHub: <a href="https://github.com/Luciano-S-F">...</a>
 *
 * Controller for handling API requests for Learner entities.
 */
@RestController
@RequestMapping("/learner")
public class LearnerRestController {

  private LearnerService learnerService;
  private LearnerToLearnerDTO learnerToLearnerDto;
  private LearnerDTOToLearner learnerDtoToLearner;

  @Autowired
  public void setLearnerService(LearnerService learnerService) {
    this.learnerService = learnerService;
  }

  @Autowired
  public void setLearnerToLearnerDto(LearnerToLearnerDTO learnerToLearnerDto) {
    this.learnerToLearnerDto = learnerToLearnerDto;
  }

  @Autowired
  public void setLearnerDtoToLearner(LearnerDTOToLearner learnerDtoToLearner) {
    this.learnerDtoToLearner = learnerDtoToLearner;
  }

  /**
   * Endpoint to list all learners.
   *
   * @return ResponseEntity containing a list of LearnerDTOs or a NotFound message
   */
  @GetMapping("/list")
  public ResponseEntity<?> listLearners() {
    List<LearnerDTO> list = learnerToLearnerDto.convertList(learnerService.list());
    if (list.isEmpty()) {
      return new ResponseEntity<>("No learners found.", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  /**
   * Endpoint to get a specific learner by ID.
   *
   * @param id the ID of the learner
   * @return ResponseEntity containing the found LearnerDTO or an error message
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> getLearnerById(@PathVariable Integer id) {
    Learner learner = learnerService.findById(id);
    if (learner == null) {
      return new ResponseEntity<>("Learner not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
    LearnerDTO learnerDto = learnerToLearnerDto.convert(learner);
    return new ResponseEntity<>(learnerDto, HttpStatus.OK);
  }

  /**
   * Endpoint to add a new learner.
   *
   * @param learnerDto the DTO of the learner to be added
   * @param bindingResult the result of DTO validation
   * @return ResponseEntity with the status of the operation and the link to the new resource
   */
  @PostMapping("/add")
  public ResponseEntity<?> addLearner(@Valid @RequestBody LearnerDTO learnerDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    Learner learner = learnerDtoToLearner.convert(learnerDto);
    learnerService.add(learner);

    Integer newLearnerId = learner.getId();

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .replacePath("/classroom/learner/{id}")
            .buildAndExpand(newLearnerId)
            .toUri();

    return ResponseEntity.created(location).build();
  }

  /**
   * Endpoint to edit an existing learner.
   *
   * @param id the ID of the learner to be edited
   * @param learnerDto the updated DTO of the learner
   * @param bindingResult the result of DTO validation
   * @return ResponseEntity with the updated LearnerDTO or an error message
   */
  @PutMapping("/edit/{id}")
  public ResponseEntity<?> editLearner(@PathVariable Integer id, @Valid @RequestBody LearnerDTO learnerDto, BindingResult bindingResult) {
    if (learnerService.findById(id) == null) {
      return new ResponseEntity<>("Learner not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
    learnerDto.setId(id);
    Learner updatedLearner = learnerDtoToLearner.convert(learnerDto);
    learnerService.update(updatedLearner);
    LearnerDTO updatedLearnerDto = learnerToLearnerDto.convert(updatedLearner);
    return new ResponseEntity<>(updatedLearnerDto, HttpStatus.OK);
  }

  /**
   * Endpoint to delete a learner by ID.
   *
   * @param id the ID of the learner to be deleted
   * @return ResponseEntity with the status of the operation or an error message
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteLearner(@PathVariable Integer id) {
    Learner learner = learnerService.findById(id);
    if (learner == null) {
      return new ResponseEntity<>("Learner not found with ID: " + id, HttpStatus.NOT_FOUND);
    }
    learnerService.deleteById(id);
    return new ResponseEntity<>("Learner deleted successfully.", HttpStatus.ACCEPTED);
  }
}
