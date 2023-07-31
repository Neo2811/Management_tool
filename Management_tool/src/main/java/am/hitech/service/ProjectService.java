package am.hitech.service;

import am.hitech.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {
    List<Project> getAll();
}
