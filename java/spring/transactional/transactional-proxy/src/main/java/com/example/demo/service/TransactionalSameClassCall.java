package com.example.demo.service;

import com.example.demo.entity.Subject;
import com.example.demo.helper.TransactionHandler;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionalSameClassCall implements RootService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TransactionHandler transactionHandler;

    public void saveData(Subject subject) {
        saveDataForReal(subject);
    }

    private void saveDataForReal(Subject subject) {
        subject = subjectRepository.save(subject);

        if (subject.getId() % 2 == 0) {
            throw new RuntimeException("An error occurred in transaction");
        }
    }
}
