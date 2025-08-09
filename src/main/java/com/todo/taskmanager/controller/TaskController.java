package com.todo.taskmanager.controller;

import com.todo.taskmanager.dto.TaskDTO;
import com.todo.taskmanager.exception.TaskNotFoundException;
import com.todo.taskmanager.model.Task;
import com.todo.taskmanager.repository.TaskRepository;
import com.todo.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskDTO> list(@RequestParam(required = false) Boolean completed) {
        return taskService.list(completed);
    }

    @GetMapping("/{id}")
    public TaskDTO getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping
    public TaskDTO save(@Valid @RequestBody TaskDTO task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@Valid @PathVariable Long id, @RequestBody TaskDTO task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @GetMapping("/sort/asc")
    public List<TaskDTO> listSortAsc() {
        return taskService.listSortAsc();
    }

    @GetMapping("/sort/desc")
    public List<TaskDTO> listSortDesc() {
        return taskService.listSortDesc();
    }

    @GetMapping("/search")
    public List<TaskDTO> searchList(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate dueDate
            ) {
        return taskService.searchList(completed, title, dueDate);
    }
}
