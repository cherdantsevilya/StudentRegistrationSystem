package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.entity.Subject;
import system.exception.GroupNotFoundException;
import system.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject findById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty())
            throw new GroupNotFoundException();
        return subject.get();
    }

    public List<Subject> findAllSubjects() {
        List<Subject> list = subjectRepository.findAll();
        if (list.isEmpty())
            throw new GroupNotFoundException();
        return list;
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
}
