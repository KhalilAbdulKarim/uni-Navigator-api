package com.uninavigatorspring.controller;

import com.uninavigatorspring.model.Project;
import com.uninavigatorspring.model.ProjectDTO;
import com.uninavigatorspring.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/allProjects")
    public ResponseEntity<Iterable<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping
    public ResponseEntity<Project> saveOrUpdateProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.saveOrUpdateProject(project));
    }

    // Get a project by ID
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable("projectId") Long projectId) {
        return projectService.getProjectById(projectId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a user to a project
    @PostMapping("/{projectId}/addUser/{userId}")
    public ResponseEntity<?> addUserToProject(@PathVariable Long projectId, @PathVariable int userId) {
        if (projectService.addUserToProject(projectId, userId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    // Remove a user from a project
    @DeleteMapping("/{projectId}/removeUser/{userId}")
    public ResponseEntity<?> removeUserFromProject(@PathVariable Long projectId, @PathVariable int userId) {
        if (projectService.removeUserFromProject(projectId, userId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsForStudent(@PathVariable int studentId) {
        List<ProjectDTO> projects = projectService.getProjectsForStudent(studentId);
        if (projects.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projects);
    }

}
