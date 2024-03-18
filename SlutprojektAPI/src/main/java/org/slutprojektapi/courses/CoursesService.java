package org.slutprojektapi.courses;

import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
        coursesRepository.findById(id).map(courses ->coursesDTOS.add(this.mapToDTO(courses)));
        return coursesDTOS;
    }
    public List<CoursesDTO> getCoursesByName(String name) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAllByNameEquals(name).forEach(courses ->coursesDTOS.add(this.mapToDTO(courses)));
        return coursesDTOS;
    }
    public List<CoursesDTO> getCoursesByNameContaining(String word) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAllByNameContaining(word).forEach(courses ->coursesDTOS.add(this.mapToDTO(courses)));
        return coursesDTOS;
    }
    public List<CoursesDTO> getCoursesByDescriptionContaining(String word) {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAllByDescriptionContaining(word).forEach(courses ->coursesDTOS.add(this.mapToDTO(courses)));
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
    Courses saveCourse(Courses course) {
        return coursesRepository.save(course);
    }
     public void removeCourseById(Long id) {
        coursesRepository.deleteById(id);
    }
}
