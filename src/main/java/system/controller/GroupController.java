package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import system.entity.Group;
import system.service.GroupService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(value = "/group/{groupId}")
    public Group getGroup(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    @GetMapping(value = "/group/search")
    public List<Group> getGroups() {
        return groupService.findAllGroups();
    }

    @PostMapping(value = "/group/new")
    public Group postGroup(@Valid @RequestBody Group group) {
        return groupService.saveGroup(group);
    }
}
