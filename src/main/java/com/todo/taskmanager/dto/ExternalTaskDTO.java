package com.todo.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalTaskDTO {
    private Long id;
    private String title;
    private Boolean completed;
}
