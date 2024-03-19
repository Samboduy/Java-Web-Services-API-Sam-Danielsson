package org.slutprojektapi.studentsCourses;

import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsCoursesController {
    @Autowired
     private StudentsCoursesService studentsCoursesService;

    @GetMapping(value = "/studentscourses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<StudentsCoursesDTO>> findAllStudents() {
        return new ResponseEntity<>(studentsCoursesService.getAllStudentsCourses(), HttpStatus.OK);
    }
    @PostMapping(value = "/addStudentCourse")
    public ResponseEntity<List<StudentsCoursesDTO>> createStudentCourse(@RequestParam(value = "studentId",required = false)Long studentId,
                                                      @RequestParam(value = "courseId",required = false) Long courseId, StudentsCourses studentsCourse){
        List<StudentsCoursesDTO> studentsCoursesDTOS = studentsCoursesService.saveStudentCourse(studentsCourse,studentId,courseId);
        if (studentId<=0||courseId<=0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(studentsCoursesDTOS,HttpStatus.CREATED);
        }
    }
    @PostMapping(value = "/removeStudentCourse")
    public ResponseEntity<List<StudentsCoursesDTO>> removeStudentCourse(@RequestParam(value = "studentCourseId",required = false)Long studentCourseId){
        if (studentCourseId<=0) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (studentCourseId.describeConstable().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            studentsCoursesService.removeStudentCourse(studentCourseId);
            List<StudentsCoursesDTO> studentsCoursesDTOS = studentsCoursesService.getAllStudentsCourses();
            return new ResponseEntity<>(studentsCoursesDTOS,HttpStatus.OK);
        }
    }

}
