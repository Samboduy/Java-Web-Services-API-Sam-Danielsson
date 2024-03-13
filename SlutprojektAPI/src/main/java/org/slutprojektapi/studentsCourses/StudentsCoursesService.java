package org.slutprojektapi.studentsCourses;

import org.slutprojektapi.courses.Courses;
import org.slutprojektapi.courses.CoursesDTO;
import org.slutprojektapi.courses.CoursesRepository;
import org.slutprojektapi.courses.CoursesService;
import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.slutprojektapi.students.StudentsRepository;
import org.slutprojektapi.students.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentsCoursesService {
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private CoursesRepository coursesRepository;
    @Autowired
    private StudentsCoursesRepository studentsCoursesRepository;

    public List<StudentsCoursesDTO> getAllStudentsCourses() {
        List<StudentsCoursesDTO> studentsCoursesDTOS = new ArrayList<>();
        studentsCoursesRepository.findAll().forEach(studentsCourses -> studentsCoursesDTOS.add(this.mapToDTO(studentsCourses)));
        return studentsCoursesDTOS;
    }

    public StudentsCoursesDTO mapToDTO(StudentsCourses studentsCourses) {
        StudentsCoursesDTO dto = new StudentsCoursesDTO();
        dto.setId(studentsCourses.getId());
        Courses course = studentsCourses.getCourses();
        Students students = studentsCourses.getStudents();
        dto.setCoursesId(course.getId());
        dto.setCourseName(course.getName());
        dto.setStudentsId(students.getId());
        dto.setStudentsName(students.getFName() + " " + students.getLName());
        dto.setTown(students.getTown());
        return dto;
    }

    public List<StudentsDTO> getAllStudents() {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAll().forEach(students -> studentsDTO.add(this.mapToDTO(students)));
        return studentsDTO;
    }

    private CoursesDTO mapToDTO(Courses courses) {
        CoursesDTO dto = new CoursesDTO();
        dto.setId(courses.getId());
        dto.setName(courses.getName());
        dto.setDescription(courses.getDescription());
        return dto;
    }
    private StudentsDTO mapToDTO(Students students) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(students.getId());
        dto.setFName(students.getFName());
        dto.setLName(students.getLName());
        dto.setTown(students.getTown());
        dto.setCourses(students.getCourses().stream().map(this::mapToDTO).collect(Collectors.toList()));
        return dto;
    }


}
