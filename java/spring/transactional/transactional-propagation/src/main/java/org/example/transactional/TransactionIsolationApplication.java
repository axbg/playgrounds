package org.example.transactional;

import org.example.transactional.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionIsolationApplication implements CommandLineRunner {
    @Autowired
    private FirstService firstService;

    @Override
    public void run(String... args) {
        try {
            // throw at first level
//            firstService.createNonTransactionalToNonTransactional(0);
//            firstService.createNonTransactionalToTransactional(0);
//            firstService.createTransactionalToNonTransactional(0);
//            firstService.createTransactionalToTransactionalRequired(0);
//            firstService.createTransactionalToTransactionalRequiresNew(0);

            // throw at second level
//            firstService.createNonTransactionalToNonTransactional(1);
//            firstService.createNonTransactionalToTransactional(1);
//            firstService.createTransactionalToNonTransactional(1);
//            firstService.createTransactionalToTransactionalRequired(1); // silently rolled back sample
//            firstService.createTransactionalToTransactionalRequiresNew(1);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionIsolationApplication.class, args);
    }
}
