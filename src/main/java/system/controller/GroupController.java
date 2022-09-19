package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.entity.Group;
import system.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "/group/search")
    public List<Group> getGroups() {
        return groupService.findAllGroups();
    }

    @PostMapping(value = "/group/new")
    public Group postGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
}
