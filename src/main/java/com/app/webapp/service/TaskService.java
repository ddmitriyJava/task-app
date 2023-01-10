package com.app.webapp.service;

import com.app.webapp.models.Task;
import com.app.webapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task){
        taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    public void updateTask(Task task, int id){
        System.out.println(task.getId() + ", " + task.getPriority() + ", " + task.getDescription());
        Task newTask = taskRepository.findById(id).get();
        newTask.setPriority(task.getPriority());
        newTask.setDescription(task.getDescription());
        taskRepository.save(newTask);
    }
}
