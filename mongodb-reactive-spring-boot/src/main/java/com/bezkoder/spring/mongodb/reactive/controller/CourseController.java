package com.bezkoder.spring.mongodb.reactive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;
import com.bezkoder.spring.mongodb.reactive.model.Course;
import com.bezkoder.spring.mongodb.reactive.service.CourseService;

// @CrossOrigin("*")
@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Course> findAll(@RequestParam(required = false) String name) {
        if (name == null)
            return courseService.findAll();
        else
            return courseService.findByNameContaining(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Course> getCourseById(@PathVariable("id") String id) {
        return courseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Course> createCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Course> updateCourse(@PathVariable("id") String id, @RequestBody Course course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCourse(@PathVariable("id") String id) {
        return courseService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllCourses() {
        return courseService.deleteAll();
    }

}
