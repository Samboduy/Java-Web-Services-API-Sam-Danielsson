package org.slutprojektapi.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursesService {
    @Autowired
    CoursesRepository coursesRepository;

    public List<CoursesDTO> getCourses() {
        List<CoursesDTO> coursesDTOS = new ArrayList<>();
        coursesRepository.findAll().forEach(courses ->coursesDTOS.add(this.convertCoursesToDto(courses)));
        return coursesDTOS;
    }

    public CoursesDTO convertCoursesToDto(Courses course) {
        CoursesDTO coursesDTO = new CoursesDTO();
        coursesDTO.setId(course.getId());
        coursesDTO.setName(course.getName());
        coursesDTO.setDescription(course.getDescription());
        return coursesDTO;
    }
}
