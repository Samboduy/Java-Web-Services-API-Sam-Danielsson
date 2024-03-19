package org.slutprojektapi.students;

import org.slutprojektapi.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (allStudents.isEmpty()){
            throw new EntityNotFoundException( HttpStatus.NOT_FOUND+ " Could not get all students");
        }
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping(value = "/students/student" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentById(@RequestParam(value = "id") Long id) {
        if (id<=0) {
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
        if (allStudents.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND + ". Could not find student with name: " + fName);
        } else {
            return new ResponseEntity<>(allStudents, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/students/lastname/{lName}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentByLName(@PathVariable(value = "lName") String lName) {
        List<StudentsDTO> allStudents = studentsService.getStudentsByLName(lName);
        if (allStudents.isEmpty()) {
            throw new EntityNotFoundException( HttpStatus.NOT_FOUND+" Could not find student with lastname: " + lName);
        } else {
            return new ResponseEntity<>(allStudents, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/students/town/{town}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<StudentsDTO>> getStudentsByTown(@PathVariable(value = "town") String town) {
        List<StudentsDTO> allStudents = studentsService.getStudentsByTown(town);
        if (allStudents.isEmpty()) {
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND + ". Could not find student from town: " + town);
        } else {
            return new ResponseEntity<>(allStudents, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/createStudent")
    public ResponseEntity<Students> createStudentFrom(@RequestParam(value = "fName")String fName,
                                                      @RequestParam(value = "lName") String lName,
                                                      @RequestParam(value = "town",required = false) String town, Students student){
        if (fName.trim().isEmpty() ||lName.trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            student.setFName(fName);
            student.setLName(lName);
            student.setTown(town);
            student = studentsService.saveStudent(student);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        }
    }
    @PostMapping(value = "/removeStudent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentsDTO>> removeStudent(
            @RequestParam(value = "id") Long id){
        if (id<=0) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (id.describeConstable().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(id);
            studentsService.removeStudentById(id);
            return new ResponseEntity<>(studentsService.getAllStudents(), HttpStatus.OK);
        }
    }

}
