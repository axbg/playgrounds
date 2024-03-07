package com.example.demo;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private NonTransactionalService nonTransactionalService;
	@Autowired
	private TransactionalService transactionalService;
	@Autowired
	private TransactionalSameClassCallWithTransactionalHandler transactionalSameClassCallWithTransactionalHandler;
	@Autowired
	private TransactionalSameClassCall transactionalSameClassCall;

	@Override
	public void run(String... args) {
		Subject subject = new Subject();
		subject.setName("TEST");

		Subject subject1 = new Subject();
		subject1.setName("TEST1");

		List<Subject> subjects = Arrays.asList(subject, subject1);

		callService(subjects, nonTransactionalService);
		callService(subjects, transactionalService);
		callService(subjects, transactionalSameClassCall);
		callService(subjects, transactionalSameClassCallWithTransactionalHandler);

		System.exit(0);
	}

	private void callService(List<Subject> subjects, RootService rootService) {
		System.out.println("Calling " + rootService.getClass().getSimpleName());
		subjects.forEach(subj -> {
			try{
				rootService.saveData(subj);
			} catch (RuntimeException ex) {
				System.out.println(ex.getMessage());
			}
		});
		System.out.println("Entries in DB: " + subjectRepository.count());
		cleanDb();
	}

	private void cleanDb() {
		subjectRepository.deleteAll();
		System.out.println("----- cleaned database -----");
	}
}
