package org.slutprojektapi.students;

import org.slutprojektapi.courses.Courses;
import org.slutprojektapi.courses.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentsService {
    @Autowired
    StudentsRepository studentsRepository;
   List<StudentsDTO> getAllStudents() {
       List<StudentsDTO> studentsDTOS = new ArrayList<>();
       studentsRepository.findAll().forEach(student ->studentsDTOS.add(this.convertStudentsDTO(student)));
       return studentsDTOS;
   }
   List<StudentsDTO>getStudentsById(Long id) {
       List<StudentsDTO> studentsDTO = new ArrayList<>();
       studentsRepository.findById(id).map(student -> studentsDTO.add(this.mapToDTO(student)))
                       .orElseThrow(() -> new StudentNotFoundException("Could not find student with id:" + id));

       return studentsDTO;
   }
    List<StudentsDTO>getStudentsByFName(String fName) {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAllByfNameEquals(fName).forEach(student -> studentsDTO.add(this.mapToDTO(student)));
        return studentsDTO;
    }
    List<StudentsDTO>getStudentsByLName(String lName) {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAllBylNameEquals(lName).forEach(student -> studentsDTO.add(this.mapToDTO(student)));
        return studentsDTO;
    }
    List<StudentsDTO>getStudentsByTown(String town) {
        List<StudentsDTO> studentsDTO = new ArrayList<>();
        studentsRepository.findAllByTownEquals(town).forEach(student -> studentsDTO.add(this.mapToDTO(student)));
        return studentsDTO;
    }
    private StudentsDTO convertStudentsDTO(Students student) {
        StudentsDTO dto = new StudentsDTO();
        dto.setId(student.getId());
        dto.setFName(student.getFName());
        dto.setLName(student.getLName());
        dto.setTown(student.getTown());
        return dto;
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
