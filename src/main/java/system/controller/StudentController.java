package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import system.entity.Student;
import system.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/student/{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @GetMapping(value = "/student/search")
    public List<Student> getStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping(value = "/student/surname")
    public List<Student> getStudentsSortedBySurname() {
        return studentService.findAllStudentsSortedBySurname();
    }

    @GetMapping(value = "/student/name")
    public List<Student> getStudentsSortedByName() {
        return studentService.findAllStudentsSortedByName();
    }

    @GetMapping(value = "/student/age")
    public List<Student> getStudentsSortedByAge() {
        return studentService.findAllStudentsSortedByAge();
    }

    @GetMapping(value = "/student/surname/{surname}")
    public List<Student> getStudentsBySurname(@PathVariable String surname) {
        return studentService.findStudentsBySurname(surname);
    }

    @GetMapping(value = "/student/name/{name}")
    public List<Student> getStudentsByName(@PathVariable String name) {
        return studentService.findStudentsByName(name);
    }

    @GetMapping(value = "/student/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable Integer age) {
        return studentService.findStudentsByAge(age);
    }

    @GetMapping(value = "/student/groupId/{groupId}")
    public List<Student> getStudentsByGroupId(@PathVariable Long groupId) {
        return studentService.findStudentsByGroup(groupId);
    }

    @PostMapping(value = "/student/new")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PostMapping(value = "/student/new/group/{groupId}")
    public Student createStudentWithGroup(
            @PathVariable Long groupId,
            @Valid @RequestBody Student student
    ) {
        return studentService.saveStudentAndSetGroup(student, groupId);
    }

    @PutMapping(value = "/student/{studentId}/group/{groupId}")
    public Student addGroupToStudent(
            @PathVariable Long studentId,
            @PathVariable Long groupId
    ) {
        return studentService.updateStudentGroup(studentId, groupId);
    }

    @PutMapping(value = "/student/{studentId}/subject/{subjectId}")
    public Student addSubjectToStudent(
            @PathVariable Long studentId,
            @PathVariable Long subjectId
    ) {
        return studentService.updateStudentSubject(studentId, subjectId);
    }
}
