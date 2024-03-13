package org.slutprojektapi.courses;

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

    private CoursesDTO convertCoursesToDTO(Courses course) {
        CoursesDTO coursesDTO = new CoursesDTO();
        coursesDTO.setId(course.getId());
        coursesDTO.setName(course.getName());
        coursesDTO.setDescription(course.getDescription());
        return coursesDTO;
    }



}
