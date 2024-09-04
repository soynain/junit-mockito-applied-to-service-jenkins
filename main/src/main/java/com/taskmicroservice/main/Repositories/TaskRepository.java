package com.taskmicroservice.main.Repositories;

import org.hibernate.query.Page;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmicroservice.main.Entities.TaskEntity;

@Repository
public interface TaskRepository  extends JpaRepository<TaskEntity,Integer>{
    org.springframework.data.domain.Page<TaskEntity> findById(Integer userId, PageRequest pageable);
}
