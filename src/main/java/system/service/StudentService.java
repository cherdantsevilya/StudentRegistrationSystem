package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.entity.Group;
import system.entity.Student;
import system.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id) { return studentRepository.findById(id).get(); }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> findAllStudentsSortedBySurname() {
        return studentRepository.findAllByOrderBySurnameAsc();
    }

    public List<Student> findAllStudentsSortedByName() {
        return studentRepository.findAllByOrderByNameAsc();
    }

    public List<Student> findAllStudentsSortedByAge() {
        return studentRepository.findAllByOrderByAgeAsc();
    }

    public List<Student> findStudentsBySurname(String surname) {
        return studentRepository.findAllBySurname(surname);
    }

    public List<Student> findStudentsByName(String name) {
        return studentRepository.findAllByName(name);
    }

    public List<Student> findStudentsByAge(Integer age) {
        return studentRepository.findAllByAge(age);
    }

    public List<Student> findStudentsByGroup(Group group) {
        return studentRepository.findAllByGroup(group);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
