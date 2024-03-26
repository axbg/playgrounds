package org.example.transactional.service;

import jakarta.transaction.Transactional;
import org.example.transactional.entity.SecondType;
import org.example.transactional.repository.SecondTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecondService {
    @Autowired
    private SecondTypeRepository secondTypeRepository;

    public void createNonTransactional(int number) {
        createSecondType("transactionalRequiresNew");
        throwIfOdd(number);
    }

    @Transactional
    public void createTransactionalRequired(int number) {
        createSecondType("transactionalRequired");
        throwIfOdd(number);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void createTransactionalRequiresNew(int number) {
        createSecondType("transactionalRequiresNew");
        throwIfOdd(number);
    }

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public void createTransactionalNotSupported(int number) {
        createSecondType("transactionalNotSupported");
        throwIfOdd(number);
    }

    private void createSecondType(String name) {
        SecondType secondType = new SecondType();
        secondType.setName(name);
        secondType = secondTypeRepository.save(secondType);

        System.out.println("Stored secondType: " + secondType.getId());
    }

    private void throwIfOdd(int number) {
        if (number % 2 == 1) {
            throw new RuntimeException("Something ugly happened on second level");
        }
    }
}
