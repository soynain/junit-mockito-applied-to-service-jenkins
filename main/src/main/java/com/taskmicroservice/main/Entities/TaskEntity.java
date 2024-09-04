package com.taskmicroservice.main.Entities;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="tasks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskEntity {
    public TaskEntity(String title, String description, String status, int user_id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user_id = user_id;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String status;

    @Column
    private int user_id;

}
