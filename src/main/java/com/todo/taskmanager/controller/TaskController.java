package com.todo.taskmanager.controller;

import com.todo.taskmanager.dto.TaskDTO;
import com.todo.taskmanager.exception.TaskNotFoundException;
import com.todo.taskmanager.model.Task;
import com.todo.taskmanager.repository.TaskRepository;
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
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> list(@RequestParam(required = false) Boolean completed) {
        if (completed != null) {
            return taskRepository.findByCompleted(completed);
        }

        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping
    public TaskDTO save(@Valid @RequestBody TaskDTO task) {
        var convertedTask = toEntity(task);
        return toDTO(taskRepository.save(convertedTask));
    }

    @PutMapping("/{id}")
    public TaskDTO update(@Valid @PathVariable Long id, @RequestBody TaskDTO task) {
        if (taskRepository.existsById(id)) {
            task.setId(id);
            var convertedTask = toEntity(task);
            return toDTO(taskRepository.save(convertedTask));
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @GetMapping("/sort/asc")
    public List<TaskDTO> listSortAsc() {
        return taskRepository.findAllByOrderByTitleAsc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/sort/desc")
    public List<TaskDTO> listSortDesc() {
        return taskRepository.findAllByOrderByTitleDesc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<TaskDTO> searchList(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDate dueDate
            ) {
        return taskRepository.searchTask(completed, title, dueDate)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // TODO: convert from entity to dto when the data is leaving

    private TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.getCompleted());
        dto.setDueDate(task.getDueDate());
        return dto;
    }

    // TODO: convert com dto to entity when data is coming

    private Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.getCompleted());
        task.setDueDate(dto.getDueDate());
        return task;
    }
}
