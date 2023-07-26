package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String name;

    @Column
    private String age;

    @Column
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "subjectId")
    private List<Detail> detail;
}
