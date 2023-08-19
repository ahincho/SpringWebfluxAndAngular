import { Component, Input, OnInit } from '@angular/core';
import { CourseService } from 'src/app/services/course.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/app/models/course.model';

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.css']
})
export class CourseDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentCourse: Course = {
    name: '',
    description: '',
    classroom: ''
  };
  
  message = '';

  constructor(
    private courseService: CourseService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getCourse(this.route.snapshot.params["id"]);
    }
  }

  getCourse(id: string): void {
    this.courseService.get(id)
      .subscribe({
        next: (data) => {
          this.currentCourse = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  updateCourse(course: Course): void {
    const data = {
      name: this.currentCourse.name,
      description: this.currentCourse.description,
      classroom: this.currentCourse.classroom
    };

    this.message = '';

    this.courseService.update(this.currentCourse.id, data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.currentCourse.name = course.name;
          this.currentCourse.description = course.description;
          this.currentCourse.classroom = course.classroom;
          this.message = res.message ? res.message : 'The status was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  updateTutorial(): void {
    this.message = '';

    this.courseService.update(this.currentCourse.id, this.currentCourse)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This tutorial was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteCourse(): void {
    this.courseService.delete(this.currentCourse.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/courses']);
        },
        error: (e) => console.error(e)
      });
  }

}