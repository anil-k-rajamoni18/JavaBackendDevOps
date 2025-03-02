package com.poc.bookingapi;

import com.poc.bookingapi.model.Book;
import com.poc.bookingapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class BookingApiApplication {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(BookingApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			Book book1 = new Book();
			book1.setBookName("Spring Boot in Action");
			book1.setPrice(45.99);
			book1.setDescription("A comprehensive guide to Spring Boot");
			book1.setAuthor("Craig Walls");
			book1.setPublicationDate(LocalDate.of(2024, 1, 1));

			Book book2 = new Book();
			book2.setBookName("Mystery in the Shadows");
			book2.setPrice(14.50);
			book2.setDescription("A gripping mystery thriller that keeps you at the edge of your seat.");
			book2.setPublicationDate(LocalDate.of(2021, 3, 15));
			book2.setAuthor("Jane Smith");

			Book book3 = new Book();
			book3.setBookName("Tech Innovations 2025");
			book3.setPrice(25.00);
			book3.setDescription("A look at the most promising tech innovations coming in 2025.");
			book3.setPublicationDate(LocalDate.of(2021, 12, 1));
			book3.setAuthor("Alan Brown");

			Book book4 = new Book();
			book4.setBookName("The History of Art");
			book4.setPrice(39.99);
			book4.setDescription("A comprehensive overview of the world's most famous artists and their works.");
			book4.setPublicationDate(LocalDate.of(2018, 7, 19));
			book4.setAuthor("Emily White");

			Book book5 = new Book();
			book5.setBookName("Cooking 101");
			book5.setPrice(12.99);
			book5.setDescription("A beginner's guide to cooking simple and delicious meals.");
			book5.setPublicationDate(LocalDate.of(2019, 4, 22));
			book5.setAuthor("Sarah Lee");

			Book book6 = new Book();
			book6.setBookName("Quantum Physics for Beginners");
			book6.setPrice(28.50);
			book6.setDescription("A simplified explanation of quantum physics for the curious mind.");
			book6.setPublicationDate(LocalDate.of(2022, 2, 11));
			book6.setAuthor("Richard Harris");

			Book book7 = new Book();
			book7.setBookName("The Silent Ocean");
			book7.setPrice(15.75);
			book7.setDescription("A novel about the mysteries of the deep ocean and the creatures that live within it.");
			book7.setPublicationDate(LocalDate.of(2020, 9, 30));
			book7.setAuthor("Olivia Brown");

			Book book8 = new Book();
			book8.setBookName("Understanding Psychology");
			book8.setPrice(21.30);
			book8.setDescription("An in-depth study of human behavior and mental processes.");
			book8.setPublicationDate(LocalDate.of(2021, 5, 12));
			book8.setAuthor("Peter Green");

			Book book9 = new Book();
			book9.setBookName("The Future of Space Travel");
			book9.setPrice(35.00);
			book9.setDescription("An exploration of the future of space exploration and interplanetary travel.");
			book9.setPublicationDate(LocalDate.of(2023, 7, 14));
			book9.setAuthor("David Williams");

			Book book10 = new Book();
			book10.setBookName("The Minimalist Lifestyle");
			book10.setPrice(16.00);
			book10.setDescription("A guide to living a minimalist life and decluttering your mind and home.");
			book10.setPublicationDate(LocalDate.of(2019, 11, 3));
			book10.setAuthor("Rachel Adams");
			bookRepository.saveAll(Arrays.asList(book1, book2, book3,book4, book5, book6, book7, book8, book9, book10));
		};
	}
}
