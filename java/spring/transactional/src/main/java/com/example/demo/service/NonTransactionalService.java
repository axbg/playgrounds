package com.example.demo.service;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NonTransactionalService implements RootService {
    @Autowired
    private SubjectRepository subjectRepository;

    public void saveData(Subject subject) {
        subject = subjectRepository.save(subject);

        if (subject.getId() % 2 == 0) {
            throw new RuntimeException("An error occurred in transaction");
        }
    }
}
