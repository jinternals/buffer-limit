package com.jinternals.bufferlimit;

import com.jinternals.bufferlimit.domain.PersonCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	private WebClient.Builder builder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		WebClient client = builder.build();

		Mono<PersonCollection> personCollectionMono = client.get().uri("/persons").retrieve().bodyToMono(PersonCollection.class);

		PersonCollection personCollection = personCollectionMono.block();

		System.out.println("Total number of persons : " + personCollection.getPersons().size());
	}
}
