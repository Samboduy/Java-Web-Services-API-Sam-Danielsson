package org.slutprojektapi.students;

import lombok.Builder;
import org.slutprojektapi.courses.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    StudentsService studentsService;

    @GetMapping(value = "/students" ,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<StudentsDTO>> getStudents() {
        List<StudentsDTO> allStudents = studentsService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping(value = "/students/student/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentById(@PathVariable(value = "id") Long id) {
        if (id<0) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            List<StudentsDTO> student = studentsService.getStudentsById(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/students/firstname/{fName}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentsByFName(@PathVariable(value = "fName") String fName) {
        List<StudentsDTO> allStudents = studentsService.getStudentsByFName(fName);
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
    @GetMapping(value = "/students/lastname/{lName}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentByLName(@PathVariable(value = "lName") String lName) {
        List<StudentsDTO> allStudents = studentsService.getStudentsByLName(lName);
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
    @GetMapping(value = "/students/town/{town}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentsByTown(@PathVariable(value = "town") String town) {
        List<StudentsDTO> allStudents = studentsService.getStudentsByTown(town);
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }


}
