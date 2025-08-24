package com.todo.taskmanager.service;

import com.todo.taskmanager.dto.TaskDTO;
import com.todo.taskmanager.mapper.TaskMapper;
import com.todo.taskmanager.model.Task;
import com.todo.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private TaskDTO taskDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        task = new Task();
        task.setId(1L);
        task.setTitle("Test task");

        taskDTO = new TaskDTO();
        taskDTO.setId(1L);
        taskDTO.setTitle("Test task");
    }

    @Test
    void shouldReturnTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        TaskDTO result = taskService.getById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Test task");

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void shouldSaveTask() {
        when(taskMapper.toEntity(taskDTO)).thenReturn(task);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMapper.toDTO(task)).thenReturn(taskDTO);

        TaskDTO result = taskService.createTask(taskDTO);

        assertThat(result.getId()).isEqualTo(1L);
        verify(taskRepository, times(1)).save(task);
    }
}