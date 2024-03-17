package org.slutprojektapi.studentsCourses;

import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsCoursesController {
    @Autowired
     private StudentsCoursesService studentsCoursesService;

    @GetMapping(value = "/studentscourses", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<StudentsCoursesDTO>> findAllStudents() {
        return new ResponseEntity<>(studentsCoursesService.getAllStudentsCourses(), HttpStatus.OK);
    }

}
