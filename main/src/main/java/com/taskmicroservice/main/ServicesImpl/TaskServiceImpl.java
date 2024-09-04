package com.taskmicroservice.main.ServicesImpl;

import java.util.List;

import org.hibernate.query.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.taskmicroservice.main.DTO.TaskEntityPostBody;
import com.taskmicroservice.main.DTO.TaskStatusUpdate;
import com.taskmicroservice.main.Entities.TaskEntity;
import com.taskmicroservice.main.Repositories.TaskRepository;
import com.taskmicroservice.main.Services.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Override
    public TaskEntity createTask(TaskEntityPostBody entityToCreate) {
        try {
            return taskRepository.save(new TaskEntity(entityToCreate.getTitle(),entityToCreate.getDescription(),"PENDING",entityToCreate.getUser_id()));
        } catch (Exception e) {
            log.error("ERROR AL CREAR TASK",e);
            return null;
        }
    }

    @Override
    public org.springframework.data.domain.Page<TaskEntity> getTasks(Integer integer) {
        return integer != null ? taskRepository.findById(integer,PageRequest.of(0, 100) ) : taskRepository.findAll(PageRequest.of(0, 100));
    }

    @Override
    public TaskEntity changeStatus(TaskStatusUpdate params) {
        TaskEntity entityUpd = taskRepository.getReferenceById(params.getId());
        entityUpd.setStatus(params.getStatus());
        return taskRepository.save(entityUpd);
    }
    
}
