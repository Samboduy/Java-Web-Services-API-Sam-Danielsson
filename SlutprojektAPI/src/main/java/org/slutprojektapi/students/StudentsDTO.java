package org.slutprojektapi.students;

import lombok.Data;
import org.slutprojektapi.courses.CoursesDTO;

import java.util.List;

@Data
public class StudentsDTO {
    private List<CoursesDTO> courses;
    private Long id;
    private String fName;
    private String lName;
    private String town;

}
