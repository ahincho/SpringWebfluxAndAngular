package com.bezkoder.spring.mongodb.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import com.bezkoder.spring.mongodb.reactive.model.Course;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, String>  {

    Flux<Course> findByNameContaining(String name);

}
