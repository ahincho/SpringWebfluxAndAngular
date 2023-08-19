package com.bezkoder.spring.mongodb.reactive.service;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.bezkoder.spring.mongodb.reactive.model.Course;
import com.bezkoder.spring.mongodb.reactive.repository.CourseRepository;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {

    CourseRepository courseRepository;

    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    public Mono<Course> findById(String id) {
        return courseRepository.findById(id);
    }

    public Flux<Course> findByNameContaining(String name) {
        return courseRepository.findByNameContaining(name);
    }

    public Mono<Course> save(Course course) {
        return courseRepository.save(course);
    }

    public Mono<Course> update(String id, Course course) {
        return courseRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        course.setId(id);
                        return courseRepository.save(course);
                    }
                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(String id) {
        return courseRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return courseRepository.deleteAll();
    }

}
