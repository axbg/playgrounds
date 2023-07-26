package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.DetailRepository;
import com.example.demo.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private DetailRepository detailRepository;

	@Override
	public void run(String... args) {
		Detail detail1 = new Detail();
		detail1.setVall("TET");
		detailRepository.save(detail1);

		Detail detail2 = new Detail();
		detail2.setVall("TET");
		detailRepository.save(detail2);

		Subject subject = new Subject();
		subject.setName("TEST");
		subject.setAge("15");
		subject.setDetail(Arrays.asList(detail1, detail2));
		subjectRepository.save(subject);

		Subject subject1 = new Subject();
		subject1.setName("TEST1");
		subject1.setAge("16");
		subjectRepository.save(subject1);

		// Dynamic projections using JPA name-based queries
		SubjectName subjName = subjectRepository.findFirstById(1, SubjectName.class);
		System.out.println(subjName.getName());

		SubjectAge subjAge = subjectRepository.findFirstById(1, SubjectAge.class);
		System.out.println(subjAge.getAge());

		// Dynamic projections using JPA Specifications
		// It's a new feature and for now does not support POJO-projections,
		// 	but it does support interfaces
		SubjectNameInterface namedSubjectInter = subjectRepository.findBy(((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), "TEST")),
				q -> q.as(SubjectNameInterface.class).firstValue());
		System.out.println(namedSubjectInter.getName());


		System.exit(0);
	}
}
