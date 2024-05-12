package com.uninavigatorspring.service;

import com.uninavigatorspring.model.Project;
import com.uninavigatorspring.model.ProjectDTO;
import com.uninavigatorspring.model.User;
import com.uninavigatorspring.repository.ProjectRepository;
import com.uninavigatorspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    // Create or update a project
    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }

    // Get a project by ID
    public Optional<Project> getProjectById(Long projectId) {
        return projectRepository.findById(projectId);
    }

    // Add a user to a project
    public boolean addUserToProject(Long projectId, int userId) {
        Optional<Project> project = projectRepository.findById(projectId);
        Optional<User> user = userRepository.findById(userId);

        if (project.isPresent() && user.isPresent()) {
            Project p = project.get();
            p.getUsers().add(user.get());
            projectRepository.save(p);
            return true;
        }
        return false;
    }

    // Remove a user from a project
    public boolean removeUserFromProject(Long projectId, int userId) {
        Optional<Project> project = projectRepository.findById(projectId);
        Optional<User> user = userRepository.findById(userId);

        if (project.isPresent() && user.isPresent()) {
            Project p = project.get();
            p.getUsers().remove(user.get());
            projectRepository.save(p);
            return true;
        }
        return false;
    }

    // List all projects
    public Set<Project> getAllProjects() {
        return new HashSet<>(projectRepository.findAll());
    }

    public List<ProjectDTO> getProjectsForStudent(int studentId) {
        return projectRepository.findProjectsByUserId(studentId)
                .stream()
                .map(project -> new ProjectDTO(
                        project.getProjectName(),
                        project.getDescription(),
                        project.getInstructor() != null ? project.getInstructor().getFullName() : "No Instructor Assigned"
                ))
                .collect(Collectors.toList());
    }
}