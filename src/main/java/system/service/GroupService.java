package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.entity.Group;
import system.exception.GroupNotFoundException;
import system.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group findById(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isEmpty())
            throw new GroupNotFoundException();
        return group.get();
    }
    public List<Group> findAllGroups() {
        List<Group> list = groupRepository.findAll();
        if (list.isEmpty())
            throw new GroupNotFoundException();
        return list;
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }
}
