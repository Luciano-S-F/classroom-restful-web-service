package com.project.classroom.controller.web;


import com.project.classroom.converter.GradeLevelToGradeLevelDTO;
import com.project.classroom.service.GradeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/gradelevel/page")
public class GradeLevelController {

    private GradeLevelToGradeLevelDTO gradeLevelToGradeLevelDTO;
    private GradeLevelService gradeLevelService;

    @Autowired
    public void setGradeLevelToGradeLevelDTO(GradeLevelToGradeLevelDTO gradeLevelToGradeLevelDTO) {
        this.gradeLevelToGradeLevelDTO = gradeLevelToGradeLevelDTO;
    }

    @Autowired
    public void setGradeLevelService(GradeLevelService gradeLevelService) {
        this.gradeLevelService = gradeLevelService;
    }

    @GetMapping("/list")
    public String listGradeLevel(Model model) {



        model.addAttribute("gradeLevels",  gradeLevelToGradeLevelDTO.convertList(gradeLevelService.list()));
        return "index";
    }
}
