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
        return studentsCoursesDTOS;
    }
    public ResponseEntity<List<StudentsCoursesDTO>> saveStudentCourse(StudentsCourses studentsCourse,Long studentId,Long courseId) {
        List<StudentsCoursesDTO> studentsCoursesDTOS = new ArrayList<>();
        if (studentsRepository.findById(studentId).isPresent() && coursesRepository.findById(courseId).isPresent()){
            Students student = studentsRepository.findById(studentId).get();
            Courses course = coursesRepository.findById(courseId).get();
            studentsCourse.setCourses(course);
            studentsCourse.setStudents(student);
            studentsCoursesRepository.save(studentsCourse);
            studentsCoursesDTOS.add(mapToDTO(studentsCourse));
            return new ResponseEntity<>(studentsCoursesDTOS, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(studentsCoursesDTOS, HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public void removeStudentCourse(Long studentCourseId){
        studentsCoursesRepository.deleteById(studentCourseId);
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
}
