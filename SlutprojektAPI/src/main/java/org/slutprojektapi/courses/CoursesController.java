package org.slutprojektapi.courses;

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
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @GetMapping(value = "/courses/course/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<List<CoursesDTO>> getCoursesById(@PathVariable(value = "id")Long id) {
        List<CoursesDTO> course = coursesService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
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




}
