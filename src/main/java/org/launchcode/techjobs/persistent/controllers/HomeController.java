package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired
    EmployerRepository employerRepository;
    @Autowired
    JobRepository jobRepository;

    @Autowired
    SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {
        //add job list element to the homepage
//        Iterable <Job> jobs = jobRepository.findAll();
//        model.addAttribute("jobs", jobs);


        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        //HomeController part 2
        Iterable <Employer> employers = employerRepository.findAll();
        Iterable <Skill> skill =skillRepository.findAll();
        model.addAttribute("employers", employers);
        model.addAttribute("title", "Add Job");
        model.addAttribute("skills", skill);
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        //HomeController part 4
        Optional<Employer> result = employerRepository.findById(employerId);
        //homeController last part #3 end
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        if (result.isEmpty()){
            model.addAttribute("title", "Invalid Employer ID:" + employerId);
        } else {
            Employer employer = result.get();
            newJob.setEmployer(employer);

            //HomeController part 4
            jobRepository.save(newJob);
        }
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        return "view";
    }


}
