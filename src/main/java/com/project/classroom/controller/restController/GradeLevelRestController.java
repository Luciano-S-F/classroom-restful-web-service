package com.project.classroom.controller.restController;

import com.project.classroom.converter.GradeLevelDTOtoGradeLevel;
import com.project.classroom.converter.GradeLevelToGradeLevelDTO;
import com.project.classroom.dto.GradeLevelDTO;
import com.project.classroom.model.GradeLevel;
import com.project.classroom.service.GradeLevelService;
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
 * <p>
 * Controller for handling API requests related to GradeLevel entities.
 */
@RestController
@RequestMapping("/gradelevel")
public class GradeLevelRestController {

    private GradeLevelToGradeLevelDTO gradeLevelToGradeLevelDTO;
    private GradeLevelDTOtoGradeLevel gradeLevelDTOtoGradeLevel;
    private GradeLevelService gradeLevelService;

    @Autowired
    public void setGradeLevelToGradeLevelDTO(GradeLevelToGradeLevelDTO gradeLevelToGradeLevelDTO) {
        this.gradeLevelToGradeLevelDTO = gradeLevelToGradeLevelDTO;
    }

    @Autowired
    public void setGradeLevelService(GradeLevelService gradeLevelService) {
        this.gradeLevelService = gradeLevelService;
    }

    @Autowired
    public void setGradeLevelDTOtoGradeLevel(GradeLevelDTOtoGradeLevel gradeLevelDTOtoGradeLevel) {
        this.gradeLevelDTOtoGradeLevel = gradeLevelDTOtoGradeLevel;
    }

    /**
     * Lists all GradeLevels.
     * Returns a NotFound status if no grade levels exist.
     *
     * @return ResponseEntity containing a list of all GradeLevelDTOs or a NotFound message
     */
    @GetMapping("/list")
    public ResponseEntity<?> listGradeLevels() {
        List<GradeLevelDTO> list = gradeLevelToGradeLevelDTO.convertList(gradeLevelService.list());
        return list.isEmpty() ? new ResponseEntity<>("No grade levels found.", HttpStatus.NOT_FOUND) : new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Retrieves a specific GradeLevel by ID.
     * Provides a clear error message if no GradeLevel is found.
     *
     * @param id the ID of the grade level
     * @return ResponseEntity containing the found GradeLevelDTO or error message
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getGradeLevelById(@PathVariable Integer id) {
        GradeLevel gradeLevel = gradeLevelService.findById(id);
        if (gradeLevel == null) {
            return new ResponseEntity<>("Grade level not found with ID: " + id, HttpStatus.NOT_FOUND);
        }

        GradeLevelDTO gradeLevelDto = gradeLevelToGradeLevelDTO.convert(gradeLevel);
        return new ResponseEntity<>(gradeLevelDto, HttpStatus.OK);
    }

    /**
     * Adds a new GradeLevel.
     * Returns detailed error messages if there are validation failures.
     * Upon successful creation, returns a ResponseEntity with the status CREATED and the link to the new GradeLevel resource.
     *
     * @param gradeLevelDTO the GradeLevel DTO containing the data for the new GradeLevel
     * @param bindingResult binding result for validation errors
     * @return ResponseEntity containing the status and created GradeLevelDTO or error message, along with the link to the new resource
     */
    @PostMapping("/add")
    public ResponseEntity<?> addGradeLevel(@Valid @RequestBody GradeLevelDTO gradeLevelDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        GradeLevel gradeLevel = gradeLevelDTOtoGradeLevel.convert(gradeLevelDTO);
        gradeLevelService.add(gradeLevel);

        Integer newGradeLevelId = gradeLevel.getId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/classroom/gradelevel/{id}")
                .buildAndExpand(newGradeLevelId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Updates a specific GradeLevel.
     * Returns error messages if the grade level does not exist or validation fails.
     *
     * @param id            the ID of the grade level to update
     * @param gradeLevelDTO the updated grade level DTO
     * @param bindingResult binding result for validation errors
     * @return ResponseEntity with the updated GradeLevelDTO or error message
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editGradeLevel(@PathVariable Integer id, @Valid @RequestBody GradeLevelDTO gradeLevelDTO, BindingResult bindingResult) {
        if (gradeLevelService.findById(id) == null) {
            return new ResponseEntity<>("Grade level not found with ID: " + id, HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        gradeLevelDTO.setId(id);
        GradeLevel gradeLevel = gradeLevelDTOtoGradeLevel.convert(gradeLevelDTO);
        gradeLevelService.update(gradeLevel);
        GradeLevelDTO updatedGradeLevelDto = gradeLevelToGradeLevelDTO.convert(gradeLevel);

        return new ResponseEntity<>(updatedGradeLevelDto, HttpStatus.OK);
    }

    /**
     * Deletes a GradeLevel by ID.
     * Validates if the grade level has associated learners before deletion.
     *
     * @param id the ID of the grade level to delete
     * @return ResponseEntity with the status of the operation or error message
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGradeLevel(@PathVariable Integer id) {
        GradeLevel gradeLevel = gradeLevelService.findById(id);
        if (gradeLevel == null) {
            return new ResponseEntity<>("Grade level not found with ID: " + id, HttpStatus.NOT_FOUND);
        }

        if (!gradeLevel.getLearners().isEmpty()) {
            return new ResponseEntity<>("Cannot delete grade level with associated learners.", HttpStatus.BAD_REQUEST);
        }

        gradeLevelService.delete(gradeLevel);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
