package am.hitech.service.impl;

import am.hitech.model.Task;
import am.hitech.repository.TaskRepository;
import am.hitech.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAllBy();
        return tasks;
    }
}
