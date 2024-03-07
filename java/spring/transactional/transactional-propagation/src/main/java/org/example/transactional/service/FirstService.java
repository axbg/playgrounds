package org.example.transactional.service;

import jakarta.transaction.Transactional;
import org.example.transactional.entity.FirstType;
import org.example.transactional.repository.FirstTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService {
    public static final String INTERCEPTED_ERROR_ON_SECOND_LEVEL = "Intercepted error on second level";

    @Autowired
    private FirstTypeRepository firstTypeRepository;
    @Autowired
    private SecondService service;

    public void createNonTransactionalToNonTransactional(int number) {
        createFirstType("nonTransactional-nonTransactional");

        try {
            service.createNonTransactional(number);
        } catch (RuntimeException ex) {
            System.out.println(INTERCEPTED_ERROR_ON_SECOND_LEVEL);
        }

        throwIfEven(number);
    }

    public void createNonTransactionalToTransactional(int number) {
        createFirstType("nonTransactional-nonTransactional");

        try {
            service.createTransactionalRequired(number);
        } catch (RuntimeException ex) {
            System.out.println(INTERCEPTED_ERROR_ON_SECOND_LEVEL);
        }

        throwIfEven(number);
    }

    @Transactional
    public void createTransactionalToNonTransactional(int number) {
        createFirstType("nonTransactional-nonTransactional");

        try {
            service.createNonTransactional(number);
        } catch (RuntimeException ex) {
            System.out.println(INTERCEPTED_ERROR_ON_SECOND_LEVEL);
        }

        throwIfEven(number);
    }

    @Transactional
    public void createTransactionalToTransactionalRequired(int number) {
        createFirstType("transactional-transactional-required");

        try {
            service.createTransactionalRequired(number);
        } catch (RuntimeException ex) {
            System.out.println(INTERCEPTED_ERROR_ON_SECOND_LEVEL);
        }

        throwIfEven(number);
    }

    @Transactional
    public void createTransactionalToTransactionalRequiresNew(int number) {
        createFirstType("transactional-transactional-requires-new");

        try {
            service.createTransactionalRequiresNew(number);
        } catch (RuntimeException ex) {
            System.out.println(INTERCEPTED_ERROR_ON_SECOND_LEVEL);
        }

        throwIfEven(number);
    }

    private void createFirstType(String name) {
        FirstType firstType = new FirstType();
        firstType.setName(name);
        firstType = firstTypeRepository.save(firstType);

        System.out.println("Stored firstType: " + firstType.getId());
    }

    private void throwIfEven(int number) {
        if (number % 2 == 0) {
            throw new RuntimeException("Something ugly happened on first level");
        }
    }
}
