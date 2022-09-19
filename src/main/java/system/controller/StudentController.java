package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entity.Group;
import system.entity.Student;
import system.entity.Subject;
import system.service.GroupService;
import system.service.StudentService;
import system.service.SubjectService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    private final GroupService groupService;

    private final SubjectService subjectService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService, SubjectService subjectService) {
        this.studentService = studentService;
        this.groupService = groupService;
        this.subjectService = subjectService;
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
        Group group = groupService.findById(groupId);
        return studentService.findStudentsByGroup(group);
    }

    @PostMapping(value = "/student/new")
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping(value = "/student/{studentId}/group/{groupId}")
    public Student addGroupToStudent(
            @PathVariable Long studentId,
            @PathVariable Long groupId
    ) {
        Student student = studentService.findById(studentId);
        Group group = groupService.findById(groupId);
        student.setGroup(group);
        return studentService.saveStudent(student);
    }

    @PutMapping(value = "/student/{studentId}/subject/{subjectId}")
    public Student addSubjectToStudent(
            @PathVariable Long studentId,
            @PathVariable Long subjectId
    ) {
        Student student = studentService.findById(studentId);
        Subject subject = subjectService.findById(subjectId);
        student.addToSubjects(subject);
        return studentService.saveStudent(student);
    }

    @PostMapping(value = "/student/new/group/{groupId}")
    public Student createStudentWithGroup(
            @PathVariable Long groupId,
            @RequestBody Student student
    ) {
        Group group = groupService.findById(groupId);
        student.setGroup(group);
        return studentService.saveStudent(student);
    }
}
