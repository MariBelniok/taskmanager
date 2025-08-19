package com.todo.taskmanager.feign;

import com.todo.taskmanager.dto.ExternalTaskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {
    @GetMapping("/todos")
    List<ExternalTaskDTO> getTodos();

    @GetMapping("/todos/{id}")
    ExternalTaskDTO getTodoById(@PathVariable Long id);
}
