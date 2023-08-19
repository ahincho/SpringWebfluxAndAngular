import { Component } from '@angular/core';
import { Course } from 'src/app/models/course.model';
import { CourseService } from 'src/app/services/course.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent {

  course: Course = {
    name: '',
    description: '',
    classroom: ''
  };
  submitted = false;

  constructor(private courseService: CourseService) { }

  saveCourse(): void {
    const data = {
      name: this.course.name,
      description: this.course.description,
      classroom: this.course.classroom
    };

    this.courseService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newCourse(): void {
    this.submitted = false;
    this.course = {
      name: '',
      description: '',
      classroom: ''
    };
  }

}