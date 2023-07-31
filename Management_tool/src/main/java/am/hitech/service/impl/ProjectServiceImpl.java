package am.hitech.service.impl;

import am.hitech.model.Project;
import am.hitech.repository.ProjectRepository;
import am.hitech.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() {
        List<Project> projects = projectRepository.findAllBy();
        return projects;
    }
}
