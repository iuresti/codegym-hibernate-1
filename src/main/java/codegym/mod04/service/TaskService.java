package codegym.mod04.service;

import codegym.mod04.model.EmployeeTask;
import codegym.mod04.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepository();
    }

    public List<EmployeeTask> getTasksByEmployeeName(String employeeName) {
        return taskRepository.getAllByEmployeeName(employeeName);
    }
}
