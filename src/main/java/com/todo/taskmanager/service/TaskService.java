package com.todo.taskmanager.service;

import com.todo.taskmanager.dto.TaskDTO;
import com.todo.taskmanager.exception.TaskNotFoundException;
import com.todo.taskmanager.mapper.TaskMapper;
import com.todo.taskmanager.model.Task;
import com.todo.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO getById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDTO(task);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toEntity(taskDTO);
        return taskMapper.toDTO(task);
    }

    public TaskDTO update(Long id, TaskDTO task) {
        if (taskRepository.existsById(id)) {
            task.setId(id);
            var convertedTask = taskMapper.toEntity(task);
            return taskMapper.toDTO(taskRepository.save(convertedTask));
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    public void delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    public List<TaskDTO> list(Boolean completed) {
        if (completed != null) {
            return taskMapper.toDTOList(taskRepository.findByCompleted(completed));
        }

        return taskMapper.toDTOList(taskRepository.findAll());
    }

    public List<TaskDTO> listSortAsc() {
        return taskMapper.toDTOList(taskRepository.findAllByOrderByTitleAsc());
    }

    public List<TaskDTO> listSortDesc() {
        return taskMapper.toDTOList(taskRepository.findAllByOrderByTitleDesc());
    }

    public List<TaskDTO> searchList(
            Boolean completed,
            String title,
            LocalDate dueDate
    ) {
        return taskMapper.toDTOList(taskRepository.searchTask(completed, title, dueDate));
    }
}
