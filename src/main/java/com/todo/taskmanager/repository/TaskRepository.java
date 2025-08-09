package com.todo.taskmanager.repository;

import com.todo.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(Boolean completed);

    List<Task> findAllByOrderByTitleAsc();

    List<Task> findAllByOrderByTitleDesc();

    @Query("""
        SELECT t FROM Task t
        WHERE(:completed IS NULL OR t.completed = :completed)
        AND(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%')))
        AND(:dueDate IS NULL OR t.dueDate < :dueDate)
    """)
    List<Task> searchTask(
            @Param("completed") Boolean completed,
            @Param("title") String title,
            @Param("dueDate") LocalDate dueDate
    );
}
