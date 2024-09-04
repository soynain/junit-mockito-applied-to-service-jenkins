package com.taskmicroservice.main.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmicroservice.main.DTO.TaskEntityPostBody;
import com.taskmicroservice.main.DTO.TaskStatusUpdate;
import com.taskmicroservice.main.ServicesImpl.TaskServiceImpl;

@RestController
@RequestMapping("/api/v1/mx/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    
    @Autowired
    private TaskServiceImpl taskService;
    
    @GetMapping({"/list/get/{id}","/list/get"})
    public ResponseEntity<?> getTasks(@PathVariable(required = false) Integer id){
        return ResponseEntity.ok().body(id != null ? taskService.getTasks(id) : taskService.getTasks(null));
    }

    @PostMapping("/create/new")
    public ResponseEntity<?> createTasks(@RequestBody TaskEntityPostBody request){
        log.info(request.toString()+" CUERPO DE LA SOLICITUD");
        return ResponseEntity.ok().body(taskService.createTask(request));
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity<?> updateStatusTask(@RequestBody TaskStatusUpdate request){
        return ResponseEntity.ok().body(taskService.changeStatus(request));
    }
}
