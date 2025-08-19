package com.todo.taskmanager.feign;

import com.todo.taskmanager.dto.ExternalTaskDTO;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class JsonPlaceholderFallback implements JsonPlaceholderClient {
    @Override
    public List<ExternalTaskDTO> getTodos() {
        return List.of(new ExternalTaskDTO(1L, "Fallback task", false));
    }

    @Override
    public ExternalTaskDTO getTodoById(Long id) {
        return null;
    }
}
