package am.hitech.controller;

import am.hitech.model.Project;
import am.hitech.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PreAuthorize("hasAuthority('ROOT_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.getAll();
        return ResponseEntity.ok(projects);
    }
}
