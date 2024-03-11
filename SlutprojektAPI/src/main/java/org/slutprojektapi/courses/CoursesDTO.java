package org.slutprojektapi.courses;

import lombok.Data;
import org.slutprojektapi.students.StudentsDTO;

import java.util.List;

@Data
public class CoursesDTO {
    private Long id;
    private String name;
    private String description;
    private List<StudentsDTO> students;
}
