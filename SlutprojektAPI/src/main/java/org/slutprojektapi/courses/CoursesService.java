package org.slutprojektapi.courses;

import org.apache.logging.log4j.message.Message;
import org.slutprojektapi.exceptions.EntityExceptionAdvice;
import org.slutprojektapi.exceptions.EntityNotFoundException;
import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CoursesService {
    @Autowired
    CoursesRepository coursesRepository;

    public List<CoursesDTO> getCourses() {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAll().forEach(courses ->coursesDTOS.add(this.convertCoursesToDTO(courses)));
        return coursesDTOS;
    }
    public List<CoursesDTO> getCourseById(Long id) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findById(id).map(courses ->coursesDTOS.add(this.mapToDTO(courses)))
                .orElseThrow(()-> new EntityNotFoundException( HttpStatus.NOT_FOUND +" could not find course with id:" + id));
        return coursesDTOS;
    }
    public List<CoursesDTO> getCoursesByName(String name) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAllByNameEquals(name).forEach(courses ->coursesDTOS.add(this.mapToDTO(courses)));
        if (coursesDTOS.isEmpty()){
            throwExceptionNotFound(HttpStatus.NOT_FOUND + " Could not find course by name:" + name);
        }
        return coursesDTOS;
    }
    public List<CoursesDTO> getCoursesByNameContaining(String word) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAllByNameContaining(word).forEach(courses ->coursesDTOS.add(this.mapToDTO(courses)));
        if (coursesDTOS.isEmpty()) {
            throwExceptionNotFound(HttpStatus.NOT_FOUND + " Could not find course by word:" + word);
        }
        return coursesDTOS;
    }
    public List<CoursesDTO> getCoursesByDescriptionContaining(String word) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAllByDescriptionContaining(word).forEach(courses ->coursesDTOS.add(this.mapToDTO(courses)));
       if (coursesDTOS.isEmpty()){
           throwExceptionNotFound(HttpStatus.NOT_FOUND + " Could not find course containing:" + word);
       }
        return coursesDTOS;
    }

    private CoursesDTO convertCoursesToDTO(Courses course) {
        CoursesDTO coursesDTO = new CoursesDTO();
        coursesDTO.setId(course.getId());
        coursesDTO.setName(course.getName());
        coursesDTO.setDescription(course.getDescription());
        return coursesDTO;
    }

    private StudentsDTO mapToDTO(Students students) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFName(students.getFName());
        dto.setLName(students.getLName());
        dto.setTown(students.getTown());
        return dto;
    }
    private CoursesDTO mapToDTO(Courses courses) {
        CoursesDTO dto = new CoursesDTO();
        dto.setId(courses.getId());
        dto.setName(courses.getName());
        dto.setDescription(courses.getDescription());
        dto.setStudents(courses.getStudents().stream().map(this::mapToDTO).collect(Collectors.toList()));
        return dto;
    }
    private void throwExceptionNotFound(String message) {
        throw new EntityNotFoundException(message);
    }
    Courses saveCourse(Courses course) {
        return coursesRepository.save(course);
    }
     public void removeCourseById(Long id) {
        if (coursesRepository.existsById(id)) {
            throwExceptionNotFound(HttpStatus.NOT_FOUND + " Could not find and delete course with id:" + id);
        } else {
            coursesRepository.deleteById(id);
        }
    }
}
