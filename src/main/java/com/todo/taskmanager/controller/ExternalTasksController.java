package com.todo.taskmanager.controller;

import com.todo.taskmanager.dto.ExternalTaskDTO;
import com.todo.taskmanager.service.ExternalTasksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/external-tasks")
public class ExternalTasksController {
    private final ExternalTasksService externalTasksService;

    public ExternalTasksController(ExternalTasksService externalTasksService) {
        this.externalTasksService = externalTasksService;
    }

    @GetMapping
    public List<ExternalTaskDTO> getExternalTasks() {
        return externalTasksService.fetchExternalTasks();
    }

    @GetMapping("/{id}")
    public ExternalTaskDTO getExternalTaskById(@PathVariable Long id) {
        return externalTasksService.fetchExternalTaskById(id);
    }
}
