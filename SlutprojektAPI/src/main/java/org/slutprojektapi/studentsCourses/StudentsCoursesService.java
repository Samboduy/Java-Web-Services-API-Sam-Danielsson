package org.slutprojektapi.studentsCourses;

import org.slutprojektapi.courses.Courses;
import org.slutprojektapi.courses.CoursesDTO;
import org.slutprojektapi.courses.CoursesRepository;
import org.slutprojektapi.courses.CoursesService;
import org.slutprojektapi.exceptions.EntityNotFoundException;
import org.slutprojektapi.students.Students;
import org.slutprojektapi.students.StudentsDTO;
import org.slutprojektapi.students.StudentsRepository;
import org.slutprojektapi.students.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
        throwExceptionNotFound(HttpStatus.NOT_FOUND + "Could not get students relationship with courses");
        return studentsCoursesDTOS;
    }
    public List<StudentsCoursesDTO> saveStudentCourse(StudentsCourses studentsCourse,Long studentId,Long courseId) {
        List<StudentsCoursesDTO> studentsCoursesDTOS = new ArrayList<>();
        if (studentsRepository.existsById(studentId) && coursesRepository.existsById(courseId)){
            Students student = studentsRepository.findById(studentId).get();
            Courses course = coursesRepository.findById(courseId).get();
            studentsCourse.setCourses(course);
            studentsCourse.setStudents(student);
            studentsCoursesRepository.save(studentsCourse);
            studentsCoursesDTOS.add(mapToDTO(studentsCourse));
            return studentsCoursesDTOS;
        }else {
           throwExceptionNotFound(HttpStatus.NOT_FOUND +"Could not find student with id:"
                   + studentId + " or could not find course with id:" + courseId);
           return studentsCoursesDTOS;
        }
    }
    public void removeStudentCourse(Long studentCourseId){
        if (studentsCoursesRepository.existsById(studentCourseId)) {
            studentsCoursesRepository.deleteById(studentCourseId);
        } else {
           throwExceptionNotFound( HttpStatus.NOT_FOUND + " Could not find studentcourse by id:" + studentCourseId + " to delete");
        }
    }
    public StudentsCoursesDTO mapToDTO(StudentsCourses studentsCourses) {
        StudentsCoursesDTO dto = new StudentsCoursesDTO();
        dto.setId(studentsCourses.getId());
        Courses course = studentsCourses.getCourses();
        if (course !=null) {
            dto.setCoursesId(course.getId());
            dto.setCourseName(course.getName());
        }
        Students students = studentsCourses.getStudents();
        if (students !=null) {
            dto.setStudentsId(students.getId());
            dto.setStudentsName(students.getFName() + " " + students.getLName());
            dto.setTown(students.getTown());
        }
        return dto;
    }

    private void throwExceptionNotFound(String message) {
        throw new EntityNotFoundException(message);
    }
}
