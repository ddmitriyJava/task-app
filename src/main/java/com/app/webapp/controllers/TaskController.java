package com.app.webapp.controllers;

import com.app.webapp.models.Task;
import com.app.webapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getIndexPage(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("size", tasks.size());
        return "main";
    }

    @GetMapping("/create-task")
    public String createTaskForm(){
        return "create_task_form";
    }

    @PostMapping("/save-task")
    public String saveTask(Task task){
        taskService.createTask(task);

        return "redirect:/tasks";
    }

    @GetMapping("/update-task-form/{id}")
    public String updateTaskForm(@PathVariable int id, Model model){
        model.addAttribute("id", id);
        return "update_task_form";
    }

    @PostMapping("/update-task/{id}")
    public String updateTask(@Validated Task task, @PathVariable int id){
        taskService.updateTask(task, id);

        return "redirect:/tasks";
    }

    @RequestMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable int id){
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }
}
