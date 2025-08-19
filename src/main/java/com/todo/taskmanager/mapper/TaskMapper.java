package com.todo.taskmanager.mapper;

import com.todo.taskmanager.dto.TaskDTO;
import com.todo.taskmanager.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDTO(Task task);
    Task toEntity(TaskDTO taskDTO);
    List<TaskDTO> toDTOList(List<Task> tasks);
}
