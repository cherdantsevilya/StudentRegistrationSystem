package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.entity.Group;
import system.entity.Student;
import system.entity.Subject;
import system.exception.GroupNotFoundException;
import system.exception.StudentNotFoundException;
import system.exception.SubjectNotFoundException;
import system.repository.GroupRepository;
import system.repository.StudentRepository;
import system.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
    }

    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty())
            throw new StudentNotFoundException();
        return student.get();
    }

    public List<Student> findAllStudents() {
        List<Student> list = studentRepository.findAll();
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findAllStudentsSortedBySurname() {
        List<Student> list = studentRepository.findAllByOrderBySurnameAsc();
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findAllStudentsSortedByName() {
        List<Student> list = studentRepository.findAllByOrderByNameAsc();
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findAllStudentsSortedByAge() {
        List<Student> list = studentRepository.findAllByOrderByAgeAsc();
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findStudentsBySurname(String surname) {
        List<Student> list = studentRepository.findAllBySurname(surname);
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findStudentsByName(String name) {
        List<Student> list = studentRepository.findAllByName(name);
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findStudentsByAge(Integer age) {
        List<Student> list = studentRepository.findAllByAge(age);
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public List<Student> findStudentsByGroup(Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty())
            throw new GroupNotFoundException();
        List<Student> list = studentRepository.findAllByGroup(group.get());
        if (list.isEmpty())
            throw new StudentNotFoundException();
        return list;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student saveStudentAndSetGroup(Student student, Long groupId) {
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty())
            throw new GroupNotFoundException();
        student.setGroup(group.get());
        return studentRepository.save(student);
    }

    public Student updateStudentGroup(Long studentId, Long groupId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty())
            throw new StudentNotFoundException();
        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isEmpty())
            throw new GroupNotFoundException();
        student.get().setGroup(group.get());
        return studentRepository.save(student.get());
    }

    public Student updateStudentSubject(Long studentId, Long subjectId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty())
            throw new StudentNotFoundException();
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isEmpty())
            throw new SubjectNotFoundException();
        student.get().addToSubjects(subject.get());
        return studentRepository.save(student.get());
    }
}
