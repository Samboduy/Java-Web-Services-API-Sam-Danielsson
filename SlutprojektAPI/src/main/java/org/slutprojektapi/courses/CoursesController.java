package org.slutprojektapi.courses;

import org.slutprojektapi.exceptions.EntityNotFoundException;
import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CoursesController {
    @Autowired
    CoursesService coursesService;

    @GetMapping(value = "/courses",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CoursesDTO>> getCourses() {
       List<CoursesDTO> allCourses = coursesService.getCourses();
       if (allCourses.isEmpty()){
           throw new EntityNotFoundException( HttpStatus.NOT_FOUND + " Could not get all Courses");
       }
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/course/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<CoursesDTO>> getCoursesById(@PathVariable(value = "id")Long id) {
        if (id<=0) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            List<CoursesDTO> course = coursesService.getCourseById(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/courses/name/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<CoursesDTO>> getCoursesByName(@PathVariable(value = "name")String name) {
        List<CoursesDTO> course = coursesService.getCoursesByName(name);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @GetMapping(value = "/courses/name/containing/{word}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<CoursesDTO>> getCoursesByNameContaining(@PathVariable(value = "word")String word) {
        List<CoursesDTO> course = coursesService.getCoursesByNameContaining(word);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @GetMapping(value = "/courses/description/containing/{word}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<CoursesDTO>> getCoursesByDescriptionContaining(@PathVariable(value = "word")String word) {
        List<CoursesDTO> course = coursesService.getCoursesByDescriptionContaining(word);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @PostMapping(value = "/createCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Courses> createCourse(@RequestParam(value = "courseName")String courseName,
                                                      @RequestParam(value = "description",required = false) String description, Courses course){
        if(courseName.trim().isEmpty()||description.trim().isEmpty()) {
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            course.setName(courseName);
            course.setDescription(description);
            course = coursesService.saveCourse(course);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        }
    }
    @PostMapping(value = "/removeCourse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CoursesDTO>> removeCourse(
            @RequestParam(value = "id",required = false) Long id) {
        if (id.describeConstable().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            coursesService.removeCourseById(id);
            return new ResponseEntity<>(coursesService.getCourses(), HttpStatus.OK);
        }
    }
}
