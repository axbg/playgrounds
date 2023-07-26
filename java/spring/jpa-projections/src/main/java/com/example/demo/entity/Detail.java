package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Detail {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String vall;

    @Column
    private Integer subjectId;
}
