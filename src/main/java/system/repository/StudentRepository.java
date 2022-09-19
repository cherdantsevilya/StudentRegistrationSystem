package system.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system.entity.Group;
import system.entity.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAll();
    List<Student> findAllByOrderBySurnameAsc();
    List<Student> findAllByOrderByNameAsc();
    List<Student> findAllByOrderByAgeAsc();
    List<Student> findAllBySurname(String surname);
    List<Student> findAllByName(String name);
    List<Student> findAllByAge(Integer age);
    List<Student> findAllByGroup(Group group);
}
