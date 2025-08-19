package com.todo.taskmanager.service;

import com.todo.taskmanager.dto.ExternalTaskDTO;
import com.todo.taskmanager.feign.JsonPlaceholderClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalTasksService {
    private final JsonPlaceholderClient jsonPlaceholderClient;

    public ExternalTasksService(JsonPlaceholderClient jsonPlaceholderClient) {
        this.jsonPlaceholderClient = jsonPlaceholderClient;
    }

    public List<ExternalTaskDTO> fetchExternalTasks() {
        return jsonPlaceholderClient.getTodos();
    }

    public ExternalTaskDTO fetchExternalTaskById(Long id) {
        return jsonPlaceholderClient.getTodoById(id);
    }
}
