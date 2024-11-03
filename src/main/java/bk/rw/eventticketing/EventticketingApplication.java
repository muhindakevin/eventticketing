package bk.rw.eventticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("bk.rw.eventticketing.repository")
public class EventticketingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventticketingApplication.class, args);
    }
}

