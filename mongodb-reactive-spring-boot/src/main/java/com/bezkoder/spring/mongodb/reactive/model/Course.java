package com.bezkoder.spring.mongodb.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import com.bezkoder.spring.mongodb.reactive.enums.Classroom;

@Document
@Setter @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private String id;
    private String name;
    private String description;
    private Classroom classroom;

}
