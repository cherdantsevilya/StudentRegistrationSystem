package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import system.entity.Subject;
import system.service.SubjectService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(value = "/subject/{subjectId}")
    public Subject getSubject(@PathVariable Long subjectId) {
        return subjectService.findById(subjectId);
    }

    @GetMapping(value = "/subject/search")
    public List<Subject> getSubjects() {
        return subjectService.findAllSubjects();
    }

    @PostMapping(value = "/subject/new")
    public Subject postSubject(@Valid @RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }
}
