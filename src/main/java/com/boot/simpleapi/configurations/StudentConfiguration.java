package com.boot.simpleapi.configurations;

import com.boot.simpleapi.entities.StudentEntity;
import com.boot.simpleapi.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            StudentEntity st1 = new StudentEntity(
                    "Denis",
                    LocalDate.of(2005, Month.OCTOBER, 4),
                    "denisemail@gmail.com");

            StudentEntity st2 = new StudentEntity(
                    "Andrey",
                    LocalDate.of(2000, Month.JANUARY, 1),
                    "andreyemail@gmail.com");

            studentRepository.saveAll(List.of(st1, st2));
        };
    }
}