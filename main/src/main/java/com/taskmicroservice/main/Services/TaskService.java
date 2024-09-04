package com.taskmicroservice.main.Services;

import java.util.List;

import org.hibernate.query.Page;

import com.taskmicroservice.main.DTO.TaskEntityPostBody;
import com.taskmicroservice.main.DTO.TaskStatusUpdate;
import com.taskmicroservice.main.Entities.TaskEntity;

public interface TaskService {
    TaskEntity createTask(TaskEntityPostBody body);
    org.springframework.data.domain.Page<TaskEntity> getTasks(Integer id);
    TaskEntity changeStatus(TaskStatusUpdate statusUpd);
}
