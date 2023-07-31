package am.hitech.controller;

import am.hitech.model.Role;
import am.hitech.model.User;
import am.hitech.service.RoleService;
import am.hitech.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<Role> getById(@RequestParam int id) throws NotFoundException {
        Role role = roleService.getById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllUsers() {
        List<Role> list = roleService.getAll();
        return ResponseEntity.ok(list);
    }
}
