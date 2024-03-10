package org.slutprojektapi.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
    @Autowired
    CoursesRepository coursesRepository;

    public Iterable<Courses> getCourses() {
        return coursesRepository.findAll();
    }
}
