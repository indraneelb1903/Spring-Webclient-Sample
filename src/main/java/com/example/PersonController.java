package com.example;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import reactor.core.publisher.Mono;

@RestController
public class PersonController {
	
    private WebClient webClient;
    	
	public PersonController(WebClient.Builder webBuilder) {
		this.webClient = webBuilder.baseUrl("http://localhost:8081").build();
	}
	
	@GetMapping("/firstname")
	public String getFirstName() throws InterruptedException {
		Thread.sleep(2000);
		return "john";
	}
	
	@GetMapping("/lastname")
	public String getLastName() throws InterruptedException {
		Thread.sleep(2000);
		return "Doe";
	}
	
	
	@GetMapping("/age")
	public String getAge() throws InterruptedException {
		Thread.sleep(2000);
		return "35";
	}
	
	@GetMapping("/gender")
	public String getGender() throws InterruptedException {
		Thread.sleep(2000);
		return "male";
	}
	
	@GetMapping("/birthyear")
	public String getBy() throws InterruptedException {
		Thread.sleep(2000);
		return "1985";
	}
		
	
	@GetMapping("/person")
	public Person getPerson(){
		Long before = System.currentTimeMillis();
		
		Person person = new Person();
	
		
	    Mono fnString = this.webClient.get().uri("/firstname").retrieve().bodyToMono(String.class)
	    		        .doOnNext(s -> person.setFirstName(s)).onErrorResume(throwable ->{
	    		        	//log error
	    		        	return Mono.justOrEmpty(null); //mono finishes without emitting.
	    		        });	
	    
	    Mono lnString = this.webClient.get().uri("/lastname").retrieve().bodyToMono(String.class)
		        .doOnNext(s -> person.setLastName(s)).onErrorResume(throwable ->{
		        	//log error
		        	return Mono.justOrEmpty(null); //mono finishes without emitting.
		        });	
	    
	    
	    Mono ageString = this.webClient.get().uri("/age").retrieve().bodyToMono(String.class)
		        .doOnNext(s -> person.setAge(s)).onErrorResume(throwable ->{
		        	//log error
		        	return Mono.justOrEmpty(null); //mono finishes without emitting.
		        });	
	    
	    
	    Mono byString = this.webClient.get().uri("/birthyear").retrieve().bodyToMono(String.class)
		        .doOnNext(s -> person.setBy(s)).onErrorResume(throwable ->{
		        	//log error
		        	return Mono.justOrEmpty(null); //mono finishes without emitting.
		        });	
	    
	    
	    Mono genderString = this.webClient.get().uri("/gender").retrieve().bodyToMono(String.class)
		        .doOnNext(s -> person.setGender(s)).onErrorResume(throwable ->{
		        	//log error
		        	return Mono.justOrEmpty(null); //mono finishes without emitting.
		        });	
	    
	    
	    
	    Mono.when(fnString,lnString,ageString,byString,genderString).block();
		
		return person;
	}
	
}
