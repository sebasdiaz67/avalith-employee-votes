package com.avalith.votes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.avalith.votes.model.Area;
import com.avalith.votes.repository.AreaRepository;

@SpringBootApplication
public class AvalithEmployeeVotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvalithEmployeeVotesApplication.class, args);
	}

//	@Bean
//    CommandLineRunner initDatabase(AreaRepository repository) {
//        return args -> {
//            repository.save(new Area("Team player", "avalith"));
//            repository.save(new Area("Technical referent,", "avalith"));
//            repository.save(new Area("Key Player", "avalith"));
//        };
//    }
}
