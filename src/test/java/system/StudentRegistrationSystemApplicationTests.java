package system;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import system.entity.Group;
import system.entity.Student;
import system.entity.Subject;
import system.exception.GroupNotFoundException;
import system.service.GroupService;
import system.service.StudentService;
import system.service.SubjectService;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentRegistrationSystemApplicationTests {
    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SubjectService subjectService;

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    @Order(1)
    void findAllStudents() {
        List<Student> list = studentService.findAllStudents();
        Assertions.assertEquals(list.size(),20);
    }


    @Test
    @Order(2)
    void findAllSubjects() {
        List<Subject> list = subjectService.findAllSubjects();
        Assertions.assertEquals(list.size(),12);
    }

    @Test
    @Order(3)
    void findAllGroups() {
        List<Group> list = groupService.findAllGroups();
        Assertions.assertEquals(list.size(),5);
    }

    @Test
    @Order(4)
    void findAllStudentByGroup1() {
        studentService.updateStudentGroup(1L, 1L);
        List<Student> list = studentService.findStudentsByGroup(1L);
        Assertions.assertEquals(list.size(),1);
    }

    @Test
    @Order(5)
    void findAllStudentByGroup2() {
        studentService.updateStudentGroup(3L, 2L);
        studentService.updateStudentGroup(4L, 2L);
        studentService.updateStudentGroup(5L, 2L);
        List<Student> list = studentService.findStudentsByGroup(2L);
        Assertions.assertNotEquals(list.size(),5);
    }

    @Test
    @Order(6)
    void saveStudentAndSetGroup1() {
        Student student = new Student(null, "Марк", "Петренко", 20, null, null);
        studentService.saveStudentAndSetGroup(student, 3L);
        List<Student> list = studentService.findStudentsByGroup(3L);
        Assertions.assertEquals(list.size(),1);
    }

    @Test
    @Order(7)
    void saveStudentAndSetGroup2() {
        Student student = new Student(null, "Марк", "Петренко", 20, null, null);
        Assertions.assertThrows(GroupNotFoundException.class, () -> studentService.saveStudentAndSetGroup(student, 100L));
    }
}
