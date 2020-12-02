package org.mattpayne.spring.visit.notes;

import org.mattpayne.spring.visit.notes.entity.Tag;
import org.mattpayne.spring.visit.notes.entity.Url;
import org.mattpayne.spring.visit.notes.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesApplication implements CommandLineRunner {

	@Autowired
	private TagRepository tagRepository;

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tag school = new Tag("school");
		Url mcc = new Url("http://MCCNeb.edu");
		school.addUrl(mcc);
		tagRepository.save(school); // TODO: Really should use a service...
	}
}
