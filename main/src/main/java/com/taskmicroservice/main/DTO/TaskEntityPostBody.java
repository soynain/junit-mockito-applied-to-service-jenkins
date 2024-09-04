package com.taskmicroservice.main.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskEntityPostBody {
    private String title;

    private String description;
    
    private int user_id;
}
