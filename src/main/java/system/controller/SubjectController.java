package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entity.Subject;
import system.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(value = "/subject/search")
    public List<Subject> getSubjects() {
        return subjectService.findAllSubjects();
    }

    @PostMapping(value = "/subject/new")
    public Subject postSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }
}
