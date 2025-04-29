package spaceCowboy.dc_news_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spaceCowboy.dc_news_management.persistence.entity.PreviewText;

//TODO aggiungere vincolo di unicità e not null sulle entity
//TODO aggiungere entity

@SpringBootApplication
public class DigitalChroniclesNewsManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalChroniclesNewsManagementApiApplication.class, args);
		}



}
