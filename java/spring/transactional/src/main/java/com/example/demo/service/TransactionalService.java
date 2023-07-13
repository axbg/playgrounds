package com.example.demo.service;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionalService implements RootService{
    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional(rollbackFor = {Exception.class})
    public void saveData(Subject subject) {
        subject = subjectRepository.save(subject);

        if (subject.getId() % 2 == 0) {
            throw new RuntimeException("An error occurred in transaction");
        }
    }
}
